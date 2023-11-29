package br.akd.svc.akadion.web.modules.email.services;

import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.pagamento.models.entity.PagamentoSistemaEntity;
import br.akd.svc.akadion.web.modules.plano.models.entity.PlanoEntity;

public interface EmailService {

    void enviarEmailCobranca(PagamentoSistemaEntity pagamento,
                             PlanoEntity planoEntity,
                             ClienteSistemaEntity clienteEntity);

    void enviarEmailAtrasoPagamento(PagamentoSistemaEntity pagamento,
                                    PlanoEntity planoEntity,
                                    ClienteSistemaEntity clienteEntity);

    void enviarEmailSucessoPagamento(PagamentoSistemaEntity pagamento,
                                     PlanoEntity planoEntity,
                                     ClienteSistemaEntity clienteEntity,
                                     EmpresaEntity empresaEntity);

    void enviarEmailBoasVindasNovoCliente(ClienteSistemaEntity clienteSistema);

    void enviarEmailVencimentoProximo(ClienteSistemaEntity clienteSistema);

    void enviarEmailAssinaturaEncerrada(ClienteSistemaEntity clienteSistema);

}