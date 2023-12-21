package br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal;

import br.akd.svc.akadion.web.modules.empresa.models.dto.fiscal.request.ConfigFiscalEmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.certificado.CertificadoDigitalEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.nfce.NfceConfigEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.nfe.NfeConfigEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.nfse.NfseConfigEntity;
import br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal.RegimeTributarioEnum;
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
@Table(name = "TB_AKD_CFGFISCAL")
public class ConfigFiscalEmpresaEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Comment("Chave primária da configuração fiscal - UUID")
    @Column(name = "COD_CONFIGFISCAL_CFF")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Builder.Default
    @Comment("Impostos discriminados na nota fiscal")
    @Column(name = "BOL_DISCRIMINAIMPOSTOS_CFF", nullable = false)
    private Boolean discriminaImpostos = false;

    @Builder.Default
    @Comment("NFE Habilitada")
    @Column(name = "BOL_HABILITANFE_CFF", nullable = false)
    private Boolean habilitaNfe = false;

    @Builder.Default
    @Comment("NFCE Habilitada")
    @Column(name = "BOL_HABILITANFCE_CFF", nullable = false)
    private Boolean habilitaNfce = false;

    @Builder.Default
    @Comment("NFSE Habilitada")
    @Column(name = "BOL_HABILITANFSE_CFF", nullable = false)
    private Boolean habilitaNfse = false;

    @Comment("Envio de e-mail com nota fiscal ao destinatário habilitado")
    @Column(name = "BOL_HABILITAENVIOEMAILDESTINATARIO_CFF", nullable = false)
    private Boolean habilitaEnvioEmailDestinatario = false;

    @Comment("CNPJ da contabilidade")
    @Column(name = "STR_CNPJCONTABILIDADE_CFF", length = 18)
    private String cnpjContabilidade;

    @Comment("CNPJ da contabilidade")
    @Column(name = "STR_SENHACERTIFICADODIGITAL_CFF", length = 50)
    private String senhaCertificadoDigital;

    @Comment("Token de homologação")
    @Column(name = "STR_TOKENHOMOLOGACAO_NFECFG")
    private String tokenHomologacao;

    @Comment("Token de produção")
    @Column(name = "STR_TOKENPRODUCAO_NFECFG")
    private String tokenProducao;

    @Enumerated(EnumType.STRING)
    @Comment("Regime tributário: " +
            "0 - Simples nacional, " +
            "1 - Simples nacional - Excesso de sublimite de receita bruta, " +
            "2 - Regime normal")
    @Column(name = "ENM_REGIMETRIBUTARIO_CFF", nullable = false)
    private RegimeTributarioEnum regimeTributarioEnum;

    @ToString.Exclude
    @Comment("Código do certificado digital")
    @OneToOne(targetEntity = CertificadoDigitalEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_CERTIFICADODIGITAL_CRD",
            referencedColumnName = "COD_CERTIFICADODIGITAL_CRD",
            foreignKey = @ForeignKey(name = "FK_CERTIFICADODIGITAL_CONFIGFISCAL"))
    private CertificadoDigitalEntity certificadoDigital;

    @ToString.Exclude
    @Comment("Código da configuração de NFE")
    @OneToOne(targetEntity = NfeConfigEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_NFECONFIG_CFF",
            referencedColumnName = "COD_NFECONFIG_NFECFG",
            foreignKey = @ForeignKey(name = "FK_NFECONFIG_CONFIGFISCAL"))
    private NfeConfigEntity nfeConfig;

    @ToString.Exclude
    @Comment("Código da configuração de NFCE")
    @OneToOne(targetEntity = NfceConfigEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_NFCECONFIG_CFF",
            referencedColumnName = "COD_NFCECONFIG_NFCECFG",
            foreignKey = @ForeignKey(name = "FK_NFCECONFIG_CONFIGFISCAL"))
    private NfceConfigEntity nfceConfig;

    @ToString.Exclude
    @Comment("Código da configuração de NFSE")
    @OneToOne(targetEntity = NfseConfigEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_NFSECONFIG_CFF",
            referencedColumnName = "COD_NFSECONFIG_NFSECFG",
            foreignKey = @ForeignKey(name = "FK_NFSECONFIG_CONFIGFISCAL"))
    private NfseConfigEntity nfseConfig;

    public ConfigFiscalEmpresaEntity buildFromRequest(ConfigFiscalEmpresaRequest configFiscalEmpresaRequest,
                                                      CertificadoDigitalEntity certificadoDigitalEntity) {

        return configFiscalEmpresaRequest != null
                ? ConfigFiscalEmpresaEntity.builder()
                .discriminaImpostos(configFiscalEmpresaRequest.getDiscriminaImpostos())
                .habilitaNfe(configFiscalEmpresaRequest.getHabilitaNfe())
                .habilitaNfce(configFiscalEmpresaRequest.getHabilitaNfce())
                .habilitaNfse(configFiscalEmpresaRequest.getHabilitaNfse())
                .habilitaEnvioEmailDestinatario(configFiscalEmpresaRequest.getHabilitaEnvioEmailDestinatario())
                .cnpjContabilidade(configFiscalEmpresaRequest.getCnpjContabilidade())
                .senhaCertificadoDigital(configFiscalEmpresaRequest.getSenhaCertificadoDigital())
                .regimeTributarioEnum(configFiscalEmpresaRequest.getRegimeTributario())
                .certificadoDigital(certificadoDigitalEntity)
                .nfeConfig(new NfeConfigEntity().buildFromRequest(configFiscalEmpresaRequest.getNfeConfig()))
                .nfceConfig(new NfceConfigEntity().buildFromRequest(configFiscalEmpresaRequest.getNfceConfig()))
                .nfseConfig(new NfseConfigEntity().buildFromRequest(configFiscalEmpresaRequest.getNfseConfig()))
                .build()
                : null;
    }
}
