package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfe;

import br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal.OrientacaoDanfeEnum;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfeConfigRequest {

    @Min(message = "O valor mínimo para o próximo número da NFe é {min}", value = 1)
    @Max(message = "O valor máximo para o próximo número da NFe é {max}", value = 9999999)
    private Long proximoNumeroProducao;

    @Min(message = "O valor mínimo para o número de série da NFe é {min}", value = 1)
    @Max(message = "O valor máximo para o número de série da NFe é {max}", value = 999)
    private Integer serieProducao;

    @NotNull(message = "O campo 'exibir recibo na DANFE' das configurações de emissão de NFe não pode ser nulo")
    private Boolean exibirReciboNaDanfe;

    @NotNull(message = "O campo 'imprimir colunas do IPI' das configurações de emissão de NFe não pode ser nulo")
    private Boolean imprimirColunasDoIpi;

    @NotNull(message = "O campo 'mostrar dados do ISSQN' das configurações de emissão de NFe não pode ser nulo")
    private Boolean mostraDadosDoIssqn;

    @NotNull(message = "O campo 'imprimir impostos adicionais na DANFE' das configurações de emissão de NFe não pode ser nulo")
    private Boolean imprimirImpostosAdicionaisNaDanfe;

    @NotNull(message = "O campo 'sempre mostrar volumes na DANFE' das configurações de emissão de NFe não pode ser nulo")
    private Boolean sempreMostrarVolumesNaDanfe;

    @NotNull(message = "O campo 'orientação DANFE' das configurações de emissão de NFe não pode ser nulo")
    private OrientacaoDanfeEnum orientacaoDanfe;
}
