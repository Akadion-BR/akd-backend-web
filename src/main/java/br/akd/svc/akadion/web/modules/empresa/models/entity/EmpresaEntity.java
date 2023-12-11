package br.akd.svc.akadion.web.modules.empresa.models.entity;

import br.akd.svc.akadion.web.globals.endereco.entity.EnderecoEntity;
import br.akd.svc.akadion.web.globals.exclusao.entity.ExclusaoEntity;
import br.akd.svc.akadion.web.globals.imagem.entity.ImagemEntity;
import br.akd.svc.akadion.web.globals.telefone.entity.TelefoneEntity;
import br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.entity.ChamadoEntity;
import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.entity.fiscal.ConfigFiscalEmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.empresa.models.enums.SegmentoEmpresaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(EmpresaId.class)
@Table(name = "TB_AKD_EMPRESA",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_RAZAOSOCIAL_EMPRESA", columnNames = {"STR_RAZAOSOCIAL_EMP"}),
                @UniqueConstraint(name = "UK_CNPJ_EMPRESA", columnNames = {"STR_CNPJ_EMP"}),
                @UniqueConstraint(name = "UK_ENDPOINT_EMPRESA", columnNames = {"STR_ENDPOINT_EMP"}),
        })
public class EmpresaEntity {

    @Id
    @JsonIgnore
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Comment("Chave primária da empresa - UUID")
    @Column(name = "COD_EMPRESA_EMP", nullable = false, updatable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Id
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("Chave primária da empresa - ID da empresa ao qual o cliente faz parte")
    @JoinColumn(name = "COD_CLIENTESISTEMA_EMP", referencedColumnName = "COD_CLIENTESISTEMA_CLS", nullable = false, updatable = false)
    private ClienteSistemaEntity clienteSistema;

    @JsonIgnore
    @Comment("Data em que o cadastro da empresa foi realizado")
    @Column(name = "DT_DATACADASTRO_EMP", nullable = false, updatable = false, length = 10)
    private String dataCadastro;

    @JsonIgnore
    @Comment("Hora em que o cadastro da empresa foi realizado")
    @Column(name = "HR_HORACADASTRO_EMP", nullable = false, updatable = false, length = 18)
    private String horaCadastro;

    @JsonIgnore
    @Comment("Nome da empresa")
    @Column(name = "STR_NOME_EMP", nullable = false, length = 70)
    private String nome;

    @JsonIgnore
    @Comment("Razão social da empresa")
    @Column(name = "STR_RAZAOSOCIAL_EMP", nullable = false, updatable = false, length = 70)
    private String razaoSocial;

    @JsonIgnore
    @Comment("CNPJ da empresa")
    @Column(name = "STR_CNPJ_EMP", nullable = false, updatable = false, length = 18)
    private String cnpj;

    @JsonIgnore
    @Comment("Endpoint da empresa")
    @Column(name = "STR_ENDPOINT_EMP", nullable = false, length = 30)
    private String endpoint;

    @JsonIgnore
    @Comment("E-mail da empresa")
    @Column(name = "EML_EMAIL_EMP", nullable = false, length = 70)
    private String email;

    @JsonIgnore
    @Comment("Nome fantasia da empresa")
    @Column(name = "STR_NOMEFANTASIA_EMP", nullable = false, length = 70)
    private String nomeFantasia;

    @JsonIgnore
    @Comment("Inscrição estadual da empresa")
    @Column(name = "STR_INSCRICAOESTADUAL_EMP", length = 12)
    private String inscricaoEstadual;

    @JsonIgnore
    @Comment("Inscrição municipal da empresa")
    @Column(name = "STR_INSCRICAOMUNICIPAL_EMP", length = 12)
    private String inscricaoMunicipal;

    @JsonIgnore
    @Comment("Empresa está ativa")
    @Column(name = "BOL_ATIVA_EMP")
    private Boolean ativa;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Comment("Tipo de segmento da empresa: 0 - Baterias automotivas")
    @Column(name = "ENM_SEGMENTOEMPRESA_EMP", nullable = false)
    private SegmentoEmpresaEnum segmentoEmpresaEnum;

    @JsonIgnore
    @ToString.Exclude
    @Comment("Código de exclusão da empresa")
    @OneToOne(targetEntity = ExclusaoEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private ExclusaoEntity exclusao;

    @JsonIgnore
    @ToString.Exclude
    @Comment("Código da imagem de perfil da empresa")
    @OneToOne(targetEntity = ImagemEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private ImagemEntity logo;

    @JsonIgnore
    @ToString.Exclude
    @Comment("Código do telefone da empresa")
    @OneToOne(targetEntity = TelefoneEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private TelefoneEntity telefone;

    @JsonIgnore
    @ToString.Exclude
    @Comment("Código do endereço da empresa")
    @OneToOne(targetEntity = EnderecoEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private EnderecoEntity endereco;

    @JsonIgnore
    @ToString.Exclude
    @Comment("Código da configuração fiscal da empresa")
    @OneToOne(targetEntity = ConfigFiscalEmpresaEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private ConfigFiscalEmpresaEntity configFiscalEmpresa;

    @JsonIgnore
    @Builder.Default
    @ToString.Exclude
    @Comment("Chamados de suporte técnico da empresa")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(targetEntity = ChamadoEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ChamadoEntity> chamados = new ArrayList<>();

    public EmpresaEntity buildFromRequest(ClienteSistemaEntity clienteSistema,
                                          EmpresaRequest empresaRequest) {
        return EmpresaEntity.builder()
                .clienteSistema(clienteSistema)
                .dataCadastro(LocalDate.now().toString())
                .horaCadastro(LocalTime.now().toString())
                .nome(empresaRequest.getNome())
                .razaoSocial(empresaRequest.getRazaoSocial())
                .cnpj(empresaRequest.getCnpj())
                .endpoint(empresaRequest.getEndpoint().toLowerCase())
                .email(empresaRequest.getEmail())
                .nomeFantasia(empresaRequest.getNomeFantasia())
                .inscricaoEstadual(empresaRequest.getInscricaoEstadual())
                .inscricaoMunicipal(empresaRequest.getInscricaoMunicipal())
                .ativa(true)
                .segmentoEmpresaEnum(empresaRequest.getSegmentoEmpresa())
                .exclusao(null)
                .logo(null)
                .telefone(new TelefoneEntity()
                        .buildFromRequest(empresaRequest.getTelefone()))
                .endereco(new EnderecoEntity()
                        .buildFromRequest(empresaRequest.getEndereco()))
                .configFiscalEmpresa(null)
                .chamados(new ArrayList<>())
                .build();
    }

    public EmpresaEntity updateFromRequest(EmpresaEntity empresaPreAtualizacao,
                                           EmpresaRequest empresaRequest) {
        return EmpresaEntity.builder()
                .id(empresaPreAtualizacao.getId())
                .clienteSistema(empresaPreAtualizacao.getClienteSistema())
                .dataCadastro(empresaPreAtualizacao.getDataCadastro())
                .horaCadastro(empresaPreAtualizacao.getHoraCadastro())
                .nome(empresaRequest.getNome())
                .razaoSocial(empresaPreAtualizacao.getRazaoSocial())
                .cnpj(empresaPreAtualizacao.getCnpj())
                .endpoint(empresaRequest.getEndpoint().toLowerCase())
                .email(empresaRequest.getEmail())
                .nomeFantasia(empresaRequest.getNomeFantasia())
                .inscricaoEstadual(empresaRequest.getInscricaoEstadual())
                .inscricaoMunicipal(empresaRequest.getInscricaoMunicipal())
                .ativa(empresaPreAtualizacao.getAtiva())
                .exclusao(empresaPreAtualizacao.getExclusao())
                .logo(empresaPreAtualizacao.getLogo())
                .segmentoEmpresaEnum(empresaRequest.getSegmentoEmpresa())
                .telefone(new TelefoneEntity()
                        .buildFromRequest(empresaRequest.getTelefone()))
                .endereco(new EnderecoEntity()
                        .buildFromRequest(empresaRequest.getEndereco()))
                .configFiscalEmpresa(empresaPreAtualizacao.getConfigFiscalEmpresa())
                .chamados(new ArrayList<>())
                .build();
    }
}


