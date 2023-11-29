package br.akd.svc.akadion.web.modules.pagamento.models.dto;

import br.akd.svc.akadion.web.modules.pagamento.models.enums.FormaPagamentoSistemaEnum;
import br.akd.svc.akadion.web.modules.pagamento.models.enums.StatusPagamentoSistemaEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoSistemaResponse {
    private UUID id;
    private String dataCadastro;
    private String horaCadastro;
    private String dataPagamento;
    private String horaPagamento;
    private String dataVencimento;
    private Double valor;
    private Double valorLiquido;
    private String descricao;
    private String linkCobranca;
    private String linkBoleto;
    private String linkComprovante;
    private FormaPagamentoSistemaEnum formaPagamentoSistemaEnum;
    private StatusPagamentoSistemaEnum statusPagamentoSistemaEnum;
}
