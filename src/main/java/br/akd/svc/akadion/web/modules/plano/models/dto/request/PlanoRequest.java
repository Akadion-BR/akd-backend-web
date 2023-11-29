package br.akd.svc.akadion.web.modules.plano.models.dto.request;

import br.akd.svc.akadion.web.modules.pagamento.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadion.web.modules.plano.models.enums.TipoPlanoEnum;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanoRequest {
    @NotNull(message = "O tipo do plano não pode ser vazio")
    private TipoPlanoEnum tipoPlano;
    @NotNull(message = "A forma de pagamento do plano não pode ser vazia")
    private FormaPagamentoSistemaEnum formaPagamentoSistema;
}
