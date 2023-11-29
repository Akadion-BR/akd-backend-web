package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.charge;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChargeBackWebHook {
    private String status;
    private String reason;
}
