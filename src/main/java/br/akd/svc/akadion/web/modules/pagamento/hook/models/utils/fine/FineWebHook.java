package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.fine;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FineWebHook {
    private Double value;
    private String type;
}
