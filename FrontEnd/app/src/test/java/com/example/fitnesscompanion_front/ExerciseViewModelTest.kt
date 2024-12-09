package com.example.fitnesscompanion_front

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import com.example.fitnesscompanion_front.retrofit.ExerciseApi
import com.example.fitnesscompanion_front.viewmodel.ExerciseViewModel
import com.example.fitnesscompanion_front.model.Exercise
import com.example.fitnesscompanion_front.model.ExerciseRecord
import com.example.fitnesscompanion_front.retrofit.RetrofitInstance
import com.example.fitnesscompanion_front.retrofit.RetrofitTest
import kotlinx.coroutines.runBlocking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After

@ExperimentalCoroutinesApi
class ExerciseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExerciseViewModel

    @Mock
    private lateinit var mockApi: ExerciseApi

    private val dummyExercises = listOf(
        Exercise(id = 2, name = "Push Up", description = "Chest exercise", category = "Strength"),
        Exercise(id = 3, name = "Squat", description = "Leg exercise", category = "Strength")
    )

    private val dummyRecords = listOf(
        ExerciseRecord(exerciseId = 1, repetitions = 10, weight = 20.0f),
        ExerciseRecord(exerciseId = 1, repetitions = 12, weight = 25.0f)
    )

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() = runTest {
        // Initialize Mock API
        MockitoAnnotations.openMocks(this)
        mockApi = mock(ExerciseApi::class.java)

        // Mock API responses within a coroutine scope
        `when`(mockApi.getExercisesByDayRoutineId(anyInt())).thenReturn(dummyExercises)
        `when`(mockApi.getExerciseRecordsByExerciseId(anyInt())).thenReturn(dummyRecords)

        // Set Main Dispatcher to Test Dispatcher
        Dispatchers.setMain(testDispatcher)

        // Initialize ViewModel with the mocked API
        viewModel = ExerciseViewModel(dayRoutineId = 1)
    }


    @Test
    fun `fetchExercises updates exercises and selectedExercise`() = runTest {
        `when`(mockApi.getExercisesByDayRoutineId(anyInt())).thenReturn(dummyExercises)

        viewModel.fetchExercises()

        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(dummyExercises, viewModel.exercises.value)
        assertEquals(dummyExercises.first(), viewModel.selectedExercise.value)
    }

    @After
    fun tearDown() {
        // Reset the Main Dispatcher after the test
        Dispatchers.resetMain()
    }


    // Add more tests...
}
