package co.com.ikitech.api.credit;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class DepositDTO {

    private Long deposit;
}
