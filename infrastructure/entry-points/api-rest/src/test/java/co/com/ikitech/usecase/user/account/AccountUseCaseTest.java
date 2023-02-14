package co.com.ikitech.usecase.user.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.repository.Repository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

public class AccountUseCaseTest {
    @InjectMocks
    private AccountUseCase accountUseCase;

    @Mock
    private Repository<Account> accountRepository;

    @Test
    void create() {
        //Account account1 = Account.builder().id("1").build();
        Account account = Account.builder().id("1").build();

        //Mockito.when(accountRepository.findById(anyString())).thenReturn(Mono.just(account1));
        Mockito.when(accountRepository.save(any())).thenReturn(Mono.just(account));

        Mono<Account> accountMono = accountUseCase.create(Account.builder().id("2").build());

        Assertions.assertThat(accountMono.block().getId()).isEqualTo("1");
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}