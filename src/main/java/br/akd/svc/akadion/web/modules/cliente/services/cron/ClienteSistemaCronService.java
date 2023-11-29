package br.akd.svc.akadion.web.modules.cliente.services.cron;

import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadion.web.modules.plano.models.enums.StatusPlanoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Scheduled
 * Observação técnica sobre o @Scheduled
 * 1: segundo (preenchido de 0 a 59)
 * 2: minuto (preenchido de 0 a 59)
 * 3: hora (preenchido de 0 a 23)
 * 4: dia (preenchido de 0 a 31)
 * 5: mês (preenchido de 1 a 12)
 */
@Slf4j
@Service
public class ClienteSistemaCronService {

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    //TODO CRIAR MÉTODO AVISANDO CLIENTES QUE IRÁ VENCER EM TRÊS DIAS

    @Scheduled(cron = "0 0 12 * * *", zone = "America/Sao_Paulo")
    public void atualizaStatusDePlanosComPagamentoVencido() {
        log.info("Método de serviço responsável por realizar a atualização de status de planos com " +
                "pagamento vencido acessado");

        //TODO IMPLEMENTAR ENVIO ASSINCRONO DE E-MAILS PARA PLANOS VENCIDOS
        log.info("Iniciando acesso à implementação do repositório de busca de clientes com pagamentos atrasados...");
        List<ClienteSistemaEntity> clientesComPagamentoAtrasado =
                clienteSistemaRepositoryImpl.implementaBuscaPorPlanosVencidosAtivos();
        log.info("Clientes com pagamentos atrasados obtidos");

        log.info("Iniciando iteração pela listagem de clientes com pagamentos atrasados...");
        for (ClienteSistemaEntity clienteSistema : clientesComPagamentoAtrasado) {
            log.info("Setando plano do cliente de id {} como INATIVO...", clienteSistema.getId());
            clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.INATIVO);
            log.info("Atualização de status do plano do cliente realizada com sucesso");
            //TODO CANCELAR NO ASAAS
        }

        //TODO VALIDAR SE PERSISTÊNCIA ESTÁ FUNCIONANDO
        log.info("Iniciando persistência da lista de clientes com status de planos atualizados no banco de dados...");
        clienteSistemaRepositoryImpl.implementaPersistenciaEmMassa(clientesComPagamentoAtrasado);
        log.info("Persistência realizada com sucesso");
    }

    @Scheduled(cron = "0 0 13 * * *", zone = "America/Sao_Paulo")
    public void atualizaStatusDePlanosComAgendamentoRemocaoVencido() {
        log.info("Método de serviço responsável por realizar a atualização de status de planos agendamento " +
                "de remoção para hoje");

        //TODO IMPLEMENTAR ENVIO ASSINCRONO DE E-MAILS PARA PLANOS VENCIDOS
        log.info("Iniciando acesso à implementação do repositório de busca de clientes sistêmicos com " +
                "remoção agendada vencida...");
        List<ClienteSistemaEntity> clientesComRemocaoAgendadaVencida =
                clienteSistemaRepositoryImpl.implementaBuscaPorPlanosComCancelamentoAgendadoVencido();
        log.info("Clientes com remoção agendada vencida obtidos");

        log.info("Iniciando iteração pela listagem de clientes sistêmicos...");
        for (ClienteSistemaEntity clienteSistema : clientesComRemocaoAgendadaVencida) {
            log.info("Setando plano do cliente sistêmico de id {} como INATIVO...", clienteSistema.getId());
            clienteSistema.getPlano().setStatusPlanoEnum(StatusPlanoEnum.INATIVO);
            log.info("Atualização de status do plano do cliente sistêmico realizada com sucesso");
        }

        //TODO VALIDAR SE PERSISTÊNCIA ESTÁ FUNCIONANDO
        log.info("Iniciando persistência da lista de clientes sistêmicos com status de planos atualizados no " +
                "banco de dados...");
        clienteSistemaRepositoryImpl.implementaPersistenciaEmMassa(clientesComRemocaoAgendadaVencida);
        log.info("Persistência realizada com sucesso");
    }
}
