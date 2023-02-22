package co.com.ikitech.usecase.user.account;
import co.com.ikitech.model.user.account.Account;
import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.exceptions.AppException;
import co.com.ikitech.model.user.repository.Repository;
import reactor.core.publisher.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
public class AccountUseCaseTest {
    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    @InjectMocks
    private AccountUseCase accountUseCase;
    @Mock
    private Repository<Account> accountRepository;
    @Mock
    private Repository<Credit> creditRepository;
    @Test
    void create() {
        Account account = Account.builder().id("1").email("a@s.c").build();

        when(accountRepository.save(any())).thenReturn(Mono.justOrEmpty(account));

        Mono<Account> accountMono = accountUseCase.create(account);
        assertThat(accountMono.block().getId()).isEqualTo("1");

        verify(accountRepository).save(any());
    }
    @Test
    void getById() {

        Account account1 = Account.builder().id("1").build();
        when(accountRepository.findById(anyString())).thenReturn(Mono.just(account1));

        Mono<Account> monoAccount = accountUseCase.getById("1");
        assertThat(monoAccount.block().getId()).isEqualTo("1");

        verify(accountRepository).findById(anyString());
    }
    @Test
    void getAll() {

        List<Account> accountList = Arrays.asList(Account.builder().id("1").build(),
                Account.builder().id("2").build(),
                Account.builder().id("3").build());

        when(accountRepository.findAll()).thenReturn(Flux.fromIterable(accountList)
                .switchIfEmpty(Flux.error(new AppException("fallo"))));

        Flux<Account> fluxAccount = accountUseCase.getAll();
        assertThat(fluxAccount.toStream().count())
                .isEqualTo(accountList.size());

        assertThat(fluxAccount.toStream().findFirst().orElseThrow().getId())
                .isEqualTo(accountList.stream().findFirst().orElseThrow().getId());

        verify(accountRepository).findAll();
    }
    @Test
    void update() {

        Account a1 = Account.builder().id("1").names("Cesar").build();
        Account a2 = Account.builder().id("1").names("Andres").build();
        when(accountRepository.findById(anyString())).thenReturn(Mono.just(a1));
        when(accountRepository.save(any())).thenReturn(Mono.just(a2));

        Mono<Account> monoAccount = accountUseCase.update(a1.getId(),a2);

        assertThat(monoAccount.block().getNames()).isEqualTo(a2.getNames());

        verify(accountRepository).findById(anyString());
        verify(accountRepository).save(any());
    }
    @Test
    void delete() {

        Account a1 = Account.builder().id("1").credit(Credit.builder().id("1").remainingDebt(0L).build()).build();

        when(accountRepository.findById(anyString())).thenReturn(Mono.just(a1)
                .switchIfEmpty(Mono.error(new AppException("fallo"))));
        when(creditRepository.deleteById(anyString())).thenReturn(Mono.empty());
        when(accountRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<String> accountMono = accountUseCase.delete("1");
        assertThat(accountMono.block()).isEqualToIgnoringCase("la cuenta se elimino con exito");
    }
    @Test
    void createCredit(){

        Account a1 = Account.builder().id("1").names("Cesar").build();
        Account a2 = Account.builder().id("1").names("Andres").build();
        Credit c1 = Credit.builder().id("1").valueDisbursed(200L).interestValuation(15L).remainingDebt(0L).build();
        when(accountRepository.findById(anyString())).thenReturn(Mono.just(a1));
        when(accountRepository.save(any())).thenReturn(Mono.just(a2));
        when(creditRepository.save(any())).thenReturn(Mono.just(c1));

        Mono<Account> monoAccount = accountUseCase.createCredit(c1, a1);

        assertThat(monoAccount.block().getCredit().getId()).isEqualTo(c1.getId());

    }
}