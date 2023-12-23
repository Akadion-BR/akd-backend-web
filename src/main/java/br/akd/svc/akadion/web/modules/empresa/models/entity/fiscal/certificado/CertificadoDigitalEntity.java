package br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.certificado;

import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.proxy.operations.criacao.models.response.CriaEmpresaFocusResponse;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_AKD_CERTIFICADODIGITAL")
public class CertificadoDigitalEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Comment("Chave primária do certificado digital - UUID")
    @Column(name = "COD_CERTIFICADODIGITAL_CRD")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Builder.Default
    @Comment("Certificado digital válido")
    @Column(name = "BOL_VALIDO_CRD", nullable = false)
    private Boolean valido = false;

    @Lob
    @Comment("Arquivo do certificado")
    @Column(name = "ARQ_CERTIFICADODIGITAL_CRD", nullable = false)
    private byte[] arquivo;

    @Comment("Nome do arquivo")
    @Column(name = "STR_NOMEARQUIVO_CRD", length = 255, nullable = false)
    private String nomeArquivo;

    @Comment("Nome do arquivo")
    @Column(name = "STR_TIPOARQUIVO_CRD", length = 50, nullable = false)
    private String tipoArquivo;

    @Comment("Tamanho do arquivo")
    @Column(name = "LNG_TAMANHOARQUIVO_CRD", nullable = false)
    private Long tamanhoArquivo;

    @Comment("Data de validade do certificado digital")
    @Column(name = "DT_VALIDADE_CRD", nullable = false)
    private String dataValidade;

    public CertificadoDigitalEntity buildFromFocusResponse(EmpresaRequest empresaRequest,
                                                           CriaEmpresaFocusResponse criaEmpresaFocusResponse) {

        return CertificadoDigitalEntity.builder()
                .id(null)
                .valido(true)
                .arquivo(empresaRequest.getConfigFiscal().getCertificadoDigital().getBase64().getBytes())
                .nomeArquivo(empresaRequest.getConfigFiscal().getCertificadoDigital().getNomeArquivo())
                .tipoArquivo(empresaRequest.getConfigFiscal().getCertificadoDigital().getTipoArquivo())
                .tamanhoArquivo(empresaRequest.getConfigFiscal().getCertificadoDigital().getTamanhoArquivo())
                .dataValidade(criaEmpresaFocusResponse.getCertificadoValidoAte())
                .build();

    }
}
