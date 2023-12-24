package br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.nfe;

import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.nfe.NfeConfigRequest;
import br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal.OrientacaoDanfeEnum;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_AKD_NFECONFIG")
public class NfeConfigEntity {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Comment("Chave primária da configuração de NFE - UUID")
    @Column(name = "COD_NFECONFIG_NFECFG", nullable = false, updatable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Comment("Próximo número de NFE em produção")
    @Column(name = "LNG_PROXIMONUMEROPRODUCAO_NFECFG")
    private Long proximoNumeroProducao;

    @Comment("Próximo número de NFE em homologação")
    @Column(name = "LNG_PROXIMONUMEROHOMOLOGACAO_NFECFG")
    private Long proximoNumeroHomologacao;

    @Comment("Próxima série de NFE em produção")
    @Column(name = "INT_SERIEPRODUCAO_NFECFG")
    private Integer serieProducao;

    @Comment("Próxima série de NFE em homologação")
    @Column(name = "INT_SERIEHOMOLOGACAO_NFECFG")
    private Integer serieHomologacao;

    @Comment("Exibir recibo na DANFE")
    @Column(name = "BOL_EXIBIRRECIBONADANFE_NFCECFG", nullable = false)
    private Boolean exibirReciboNaDanfe;

    @Comment("Imprimir colunas do IPI")
    @Column(name = "BOL_IMPRIMIRCOLUNASDOIPI_NFCECFG", nullable = false)
    private Boolean imprimirColunasDoIpi;

    @Comment("Mostrar dados do ISSQN")
    @Column(name = "BOL_MOSTRADADOSDOISSQN_NFCECFG", nullable = false)
    private Boolean mostraDadosDoIssqn;

    @Comment("Imprimir impostos adicionais na DANFE")
    @Column(name = "BOL_IMPRIMIRIMPOSTOSADICIONAISNADANFE_NFCECFG", nullable = false)
    private Boolean imprimirImpostosAdicionaisNaDanfe;

    @Comment("Sempre mostrar volumes na DANFE")
    @Column(name = "BOL_SEMPREMOSTRARVOLUMESNADANFE_NFCECFG", nullable = false)
    private Boolean sempreMostrarVolumesNaDanfe;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Comment("Orientação da DANFE: 0 - Retrato, 1 - Paisagem")
    @Column(name = "ENM_ORIENTACAODANFE_CFF", nullable = false)
    private OrientacaoDanfeEnum orientacaoDanfe = OrientacaoDanfeEnum.PORTRAIT;

    public NfeConfigEntity buildFromRequest(NfeConfigRequest nfeConfigRequest) {
        return nfeConfigRequest != null
                ? NfeConfigEntity.builder()
                .proximoNumeroProducao(nfeConfigRequest.getProximoNumeroProducao())
                .proximoNumeroHomologacao(null)
                .serieProducao(nfeConfigRequest.getSerieProducao())
                .serieHomologacao(null)
                .orientacaoDanfe(nfeConfigRequest.getOrientacaoDanfe())
                .build()
                : null;
    }
}
