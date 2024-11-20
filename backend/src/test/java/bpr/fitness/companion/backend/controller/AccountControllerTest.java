package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.Account;
import bpr.fitness.companion.backend.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void testCreateAccount() throws Exception {
        Account newAccount = new Account();
        newAccount.setId(1L);
        newAccount.setUserName("john_doe");
        newAccount.setPassword("password123");
        newAccount.setExerciseRecordIds(Arrays.asList(101L, 102L));
        newAccount.setWeekRoutineIds(Arrays.asList(201L, 202L));

        when(accountService.createAccount(Mockito.any(Account.class))).thenReturn(newAccount);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "userName": "john_doe",
                            "password": "password123",
                            "exerciseRecordIds": [101, 102],
                            "weekRoutineIds": [201, 202]
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("john_doe"))
                .andExpect(jsonPath("$.exerciseRecordIds[0]").value(101))
                .andExpect(jsonPath("$.weekRoutineIds[1]").value(202));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setUserName("john_doe");
        account1.setPassword("password123");
        account1.setExerciseRecordIds(Arrays.asList(101L, 102L));
        account1.setWeekRoutineIds(Arrays.asList(201L, 202L));

        Account account2 = new Account();
        account2.setId(2L);
        account2.setUserName("jane_doe");
        account2.setPassword("securepass");
        account2.setExerciseRecordIds(Arrays.asList(103L, 104L));
        account2.setWeekRoutineIds(Arrays.asList(203L, 204L));

        List<Account> accounts = Arrays.asList(account1, account2);
        when(accountService.getAllAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].userName").value("john_doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].userName").value("jane_doe"));
    }

    @Test
    public void testGetAccountById() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setUserName("john_doe");
        account.setPassword("password123");
        account.setExerciseRecordIds(Arrays.asList(101L, 102L));
        account.setWeekRoutineIds(Arrays.asList(201L, 202L));

        when(accountService.getAccountById(1L)).thenReturn(account);

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("john_doe"))
                .andExpect(jsonPath("$.exerciseRecordIds[1]").value(102));
    }

    @Test
    public void testGetAccountById_NotFound() throws Exception {
        when(accountService.getAccountById(99L)).thenReturn(null);

        mockMvc.perform(get("/accounts/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account updatedAccount = new Account();
        updatedAccount.setId(1L);
        updatedAccount.setUserName("updated_user");
        updatedAccount.setPassword("newpassword123");
        updatedAccount.setExerciseRecordIds(Arrays.asList(103L, 104L));
        updatedAccount.setWeekRoutineIds(Arrays.asList(203L, 204L));

        when(accountService.updateAccount(eq(1L), Mockito.any(Account.class))).thenReturn(updatedAccount);

        mockMvc.perform(put("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "userName": "updated_user",
                            "password": "newpassword123",
                            "exerciseRecordIds": [103, 104],
                            "weekRoutineIds": [203, 204]
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.userName").value("updated_user"))
                .andExpect(jsonPath("$.exerciseRecordIds[0]").value(103))
                .andExpect(jsonPath("$.weekRoutineIds[1]").value(204));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        doNothing().when(accountService).deleteAccount(1L);

        mockMvc.perform(delete("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Account with id 1 deleted"));
    }
}
