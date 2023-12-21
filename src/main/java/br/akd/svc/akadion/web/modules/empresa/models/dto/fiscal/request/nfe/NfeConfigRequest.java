package br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfe;

import br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal.OrientacaoDanfeEnum;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NfeConfigRequest {
    private Long proximoNumeroProducao;
    private Integer serieProducao;
    private Boolean exibirReciboNaDanfe;
    private Boolean imprimirColunasDoIpi;
    private Boolean mostraDadosDoIssqn;
    private Boolean imprimirImpostosAdicionaisNaDanfe;
    private Boolean sempreMostrarVolumesNaDanfe;
    @NotNull(message = "O campo 'orientação DANFE' não pode ser nulo")
    private OrientacaoDanfeEnum orientacaoDanfe;
}
