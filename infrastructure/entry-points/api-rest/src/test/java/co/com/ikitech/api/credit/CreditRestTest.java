package co.com.ikitech.api.credit;

import co.com.ikitech.model.user.credit.Credit;
import co.com.ikitech.model.user.credit.Deposit;
import co.com.ikitech.model.user.repository.Repository;
import co.com.ikitech.usecase.user.credit.CreditUseCase;
import org.junit.jupiter.api.*;
import org.mockito.*;
import reactor.core.publisher.*;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.Mockito.*;

class CreditRestTest {


    @InjectMocks
    private CreditUseCase creditUseCase;
    @Mock
    private Repository<Credit> creditRepository;

    @BeforeEach
    void init(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void getCreditById() {
        Credit credit1 = Credit.builder().id("1").build();

        when(creditRepository.findById(anyString())).thenReturn(Mono.just(credit1));

        Mono<Credit> creditMono = creditUseCase.getById("1");

        assertThat(creditMono.block().getId()).isEqualTo(credit1.getId());
    }

    @Test
    void delete() {
        Credit credit1 = Credit.builder().id("1").remainingDebt(0L).build();

        when(creditRepository.findById(anyString())).thenReturn(Mono.just(credit1));
        when(creditRepository.deleteById(anyString())).thenReturn(Mono.empty());

        Mono<Void> creditMono = creditUseCase.deleteCredit("1");

        assertThat(creditMono.block()).isEqualTo(null);
    }

    @Test
    void deposit() {
        Credit credit1 = Credit.builder().id("1").remainingDebt(20L).status("created").valueDisbursed(10L).interestValuation(100L).build();
        Deposit deposit1 = Deposit.builder().deposit(10L).build();

        when(creditRepository.findById(anyString())).thenReturn(Mono.just(credit1));
        when(creditRepository.save(any())).thenReturn(Mono.just(credit1));

        Mono<Credit> creditMono = creditUseCase.update("1", deposit1);
        assertThat(creditMono.block()).isEqualTo(null);
    }

}