package co.com.ikitech.usecase.user.account;

import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import reactor.core.publisher.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;


public class AccountUseCaseTest {
    
    @InjectMocks
    private AccountUseCase accountUseCase;

    @Mock
    private Repository<Account> accountRepository;

    @Mock
    private Repository<Credit> creditRepository;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void create() {
        Account account1 = Account.builder().id("1").build();

        Account account = Account.builder().id("1").build();
        Mockito.when(accountRepository.save(any())).thenReturn(Mono.just(account1));

        Mono<Account> accountMono = accountUseCase.create(account);
        assertThat(account.getId()).isEqualTo(accountMono.block().getId());

        Mockito.verify(accountRepository).save(any());
    }


    @Test
    void getById() {

        Account account = Account.builder().id("1").build();
        Account account1 = Account.builder().id("1").build();
        Mockito.when(accountRepository.findById(anyString())).thenReturn(Mono.just(account1));

        Mono<Account> monoAccount = accountUseCase.getById(account.getId());
        assertThat(account1.getId()).isEqualTo(monoAccount.block().getId());

        Mockito.verify(accountRepository).findById(anyString());
    }

    @Test
    void getAll() {

        List<Account> accountList = Arrays.asList(Account.builder().id("1").build(),
                Account.builder().id("2").build(),
                Account.builder().id("3").build());


        Mockito.when(accountRepository.findAll()).thenReturn(Flux.fromIterable(accountList).switchIfEmpty(Flux.error(new AppException("fallo"))));

        Flux<Account> fluxAccount = accountUseCase.getAll();
        assertThat(fluxAccount.toStream().count())
                .isEqualTo(accountList.size());

        assertThat(fluxAccount.toStream().findFirst().orElseThrow().getId())
                .isEqualTo(accountList.stream().findFirst().orElseThrow().getId());

        Mockito.verify(accountRepository).findAll();
    }

    @Test
    void update() {

        Account a1 = Account.builder().id("1").names("Cesar").build();
        Account a2 = Account.builder().id("1").names("Andres").build();
        Mockito.when(accountRepository.findById(anyString())).thenReturn(Mono.just(a1));
        Mockito.when(accountRepository.save(any())).thenReturn(Mono.just(a2));

        Mono<Account> monoAccount = accountUseCase.update(a1.getId(),a2);

        assertThat(a2.getNames()).isEqualTo(monoAccount.block().getNames());

        Mockito.verify(accountRepository).findById(anyString());
        Mockito.verify(accountRepository).save(any());
    }

    @Test
    void delete() {

        Account a1 = Account.builder().id("1").credit(Credit.builder().id("1").remainingDebt(0L).build()).build();

        Mockito.when(accountRepository.findById(anyString())).thenReturn(Mono.just(a1));
        Mockito.when(creditRepository.deleteById(anyString())).thenReturn(Mono.empty());
        Mockito.when(accountRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> accountMono = accountUseCase.delete("1");
        assertThat(accountMono.block()).isEqualTo(Mono.empty());

        Mockito.verify(accountRepository).findById(anyString());
        Mockito.verify(accountRepository).deleteById(anyString());
        Mockito.verify(creditRepository).deleteById(anyString());
    }
}