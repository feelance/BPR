import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.fitnesscompanion_front.ui.theme.ThemedScaffold
import com.example.fitnesscompanion_front.viewmodel.DayRoutineViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditDayRoutineScreen(
    navController: NavController,
    dayRoutineId: Int,
    viewModel: DayRoutineViewModel = viewModel()
) {
    // States for user input
    var routineName by remember { mutableStateOf("") }
    var routineNotes by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val coroutineScope = rememberCoroutineScope()

    // Fetch the routine details on screen load
    LaunchedEffect(dayRoutineId) {
        isLoading = true
        try {
            val routine = viewModel.getDayRoutineById(dayRoutineId)
            routineName = routine.name
            routineNotes = routine.notes.orEmpty()
        } catch (e: Exception) {
            errorMessage = e.localizedMessage ?: "Failed to load routine details"
        } finally {
            isLoading = false
        }
    }

    ThemedScaffold(
        title = "Edit Day Routine",
        navController = navController,
        showBackButton = true,
        floatingActionButton = null
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Edit Day Routine Details", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            } else {
                // Text field for routine name
                OutlinedTextField(
                    value = routineName,
                    onValueChange = { routineName = it },
                    label = { Text("Routine Name") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Gray,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = Color.White,
                        errorCursorColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Text field for routine notes
                OutlinedTextField(
                    value = routineNotes,
                    onValueChange = { routineNotes = it },
                    label = { Text("Routine Notes") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 5,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Gray,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.Gray,
                        cursorColor = Color.White,
                        errorCursorColor = Color.Red
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Save button
                Button(
                    onClick = {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                viewModel.updateDayRoutine(
                                    dayRoutineId = dayRoutineId,
                                    name = routineName,
                                    routineNotes = routineNotes
                                )
                                navController.popBackStack()
                            } catch (e: Exception) {
                                errorMessage = e.localizedMessage ?: "Failed to save changes"
                            } finally {
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor  = Color.Blue, // Change button background color
                        contentColor = Color.White // Change text color on the button
                    ),
                    shape = MaterialTheme.shapes.medium // Customize the button shape
                ) {
                    Text("Save Changes")
                }
            }

            // Show error message
            errorMessage?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

fun String?.orEmpty(): String {
    return this ?: ""
}
