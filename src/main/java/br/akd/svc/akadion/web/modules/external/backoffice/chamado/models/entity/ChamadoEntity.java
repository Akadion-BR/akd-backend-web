package br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.entity;

import br.akd.svc.akadion.web.modules.external.backoffice.chamado.avaliacao.entity.AvaliacaoEntity;
import br.akd.svc.akadion.web.modules.external.backoffice.chamado.mensagem.entity.MensagemEntity;
import br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.enums.CategoriaChamadoEnum;
import br.akd.svc.akadion.web.modules.external.backoffice.chamado.models.enums.StatusChamadoEnum;
import br.akd.svc.akadion.web.modules.external.backoffice.colaborador.entity.ColaboradorInternoEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_AKD_CHAMADO")
public class ChamadoEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @Comment("Chave primária do chamado - UUID")
    @Column(name = "COD_CHAMADO_CHMD", nullable = false, updatable = false)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Comment("Data em que o cadastro do chamado foi realizado")
    @Column(name = "DT_DATACADASTRO_CHMD", nullable = false, updatable = false, length = 10)
    private String dataCadastro;

    @Comment("Hora em que o cadastro do chamado foi realizado")
    @Column(name = "HR_HORACADASTRO_CHMD", nullable = false, updatable = false, length = 18)
    private String horaCadastro;

    @Comment("Código único do ticket de atendimento do chamado")
    @Column(name = "LNG_TICKET_CHMD", nullable = false, updatable = false, unique = true)
    private Long ticket;

    @Comment("Título do chamado")
    @Column(name = "STR_TITULO_CHMD", nullable = false, updatable = false, length = 50)
    private String titulo;

    @Comment("Descrição do chamado")
    @Column(name = "STR_DESCRICAO_CHMD", nullable = false, updatable = false, length = 500)
    private String descricao;

    @Comment("Data em que a baixa do chamado foi realizada")
    @Column(name = "DT_DATABAIXA_CHMD", nullable = false, updatable = false, length = 10)
    private String dataBaixa;

    @Comment("Hora em que a baixa do chamado foi realizada")
    @Column(name = "HR_HORABAIXA_CHMD", nullable = false, updatable = false, length = 18)
    private String horaBaixa;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENM_CATEGORIA_CHMD", nullable = false)
    @Comment("Categoria do chamado: " +
            "0 - Dúvida técnica, " +
            "1 - Dúvida financeira, " +
            "2 - Problema técnico, " +
            "3 - Problema financeiro, " +
            "4 - Sugestões")
    private CategoriaChamadoEnum categoriaChamadoEnum;

    @Enumerated(EnumType.STRING)
    @Column(name = "ENM_STATUS_CHMD", nullable = false)
    @Comment("Status do chamado: " +
            "0 - Em aberto, " +
            "1 - Em atendimento, " +
            "2 - Finalizado")
    private StatusChamadoEnum statusChamadoEnum;

    @ToString.Exclude
    @ManyToOne(targetEntity = ColaboradorInternoEntity.class, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Include
    @Comment("ID do atendente responsável pelo chamado")
    private ColaboradorInternoEntity atendenteResponsavel;

    @ToString.Exclude
    @Comment("Avaliação do chamado")
    @OneToOne(targetEntity = AvaliacaoEntity.class,
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private AvaliacaoEntity avaliacao;

    @ToString.Exclude
    @Builder.Default
    @Comment("Lista de mensagens do chamado")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(targetEntity = MensagemEntity.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MensagemEntity> mensagens = new ArrayList<>();

}
