package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.split;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SplitWebHook {
    private String walletId;
    private Double fixedValue;
    private String status;
    private String refusalReason;
}
