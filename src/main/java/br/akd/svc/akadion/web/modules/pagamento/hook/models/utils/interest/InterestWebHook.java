package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.interest;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InterestWebHook {
    private Double value;
    private String type;
}
