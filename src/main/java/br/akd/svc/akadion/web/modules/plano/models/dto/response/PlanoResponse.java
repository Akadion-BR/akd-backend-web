package br.akd.svc.akadion.web.modules.plano.models.dto.response;

import br.akd.svc.akadion.web.modules.plano.models.entity.PlanoEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlanoResponse {
    private UUID id;
    private String dataContratacao;
    private String horaContratacao;
    private String dataVencimento;
    private String dataAgendamentoRemocao;
    private String tipoPlano;
    private String statusPlano;
    private String formaPagamentoSistema;

    public PlanoResponse buildFromEntity(PlanoEntity planoEntity) {
        return planoEntity != null
                ? PlanoResponse.builder()
                .id(planoEntity.getId())
                .dataContratacao(planoEntity.getDataContratacao())
                .horaContratacao(planoEntity.getHoraContratacao())
                .dataVencimento(planoEntity.getDataVencimento())
                .dataAgendamentoRemocao(planoEntity.getDataAgendamentoRemocao())
                .tipoPlano(planoEntity.getTipoPlanoEnum().getDesc())
                .statusPlano(planoEntity.getStatusPlanoEnum().getDesc())
                .formaPagamentoSistema(planoEntity.getFormaPagamentoSistemaEnum().getDesc())
                .build()
                : null;
    }
}
