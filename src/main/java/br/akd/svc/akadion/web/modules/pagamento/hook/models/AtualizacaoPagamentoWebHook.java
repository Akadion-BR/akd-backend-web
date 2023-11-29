package br.akd.svc.akadion.web.modules.pagamento.hook.models;

import br.akd.svc.akadion.web.modules.pagamento.hook.models.enums.EventoCobrancaEnum;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoPagamentoWebHook {
    private EventoCobrancaEnum event;
    private PagamentoWebHookRequest payment;
}
