package br.akd.svc.akadion.web.modules.cliente.repository.impl;

import br.akd.svc.akadion.web.exceptions.custom.InternalErrorException;
import br.akd.svc.akadion.web.exceptions.custom.ObjectNotFoundException;
import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.ClienteSistemaRepository;
import br.akd.svc.akadion.web.utils.Constantes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class ClienteSistemaRepositoryImpl {

    @Autowired
    ClienteSistemaRepository clienteSistemaRepository;

    public List<ClienteSistemaEntity> buscaTodosClientes() {
        log.info("Método que implementa busca de todos os clientes do Akadion acessado");
        return clienteSistemaRepository.findAll();
    }

    public ClienteSistemaEntity implementaPersistencia(ClienteSistemaEntity clienteSistema) {
        log.info("Método que implementa persistência do objeto ClienteSistemaEntity acessado. Cliente a ser " +
                "persistido: {}", clienteSistema);
        return clienteSistemaRepository.save(clienteSistema);
    }

    public List<ClienteSistemaEntity> implementaPersistenciaEmMassa(List<ClienteSistemaEntity> clientesSistema) {
        log.info("Método que implementa persistência de lista de objetos ClienteSistemaEntity acessado");
        try {
            log.info("Iniciando persistência da lista de clientes no banco de dados...");
            List<ClienteSistemaEntity> clientesPersistidos = clienteSistemaRepository.saveAll(clientesSistema);
            log.info("Persistência da lista de clientes realizada com sucesso");
            return clientesPersistidos;
        } catch (Exception e) {
            log.error("Ocorreu um erro interno durante a persistência em massa dos clientes: {}", e.getMessage());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

    public ClienteSistemaEntity implementaBuscaPorId(UUID id) {
        log.info("Método de implementação de busca de clienteSistemaEntity por id ({}) acessado...", id);

        Optional<ClienteSistemaEntity> clienteOptional =
                clienteSistemaRepository.findById(id);

        ClienteSistemaEntity clienteSistema;
        if (clienteOptional.isPresent()) {
            clienteSistema = clienteOptional.get();
            log.info("Cliente encontrado: {}", clienteSistema);
        } else {
            log.warn("Nenhum cliente foi encontrado com o id informado: {}", id);
            throw new ObjectNotFoundException("Nenhum cliente foi encontrado. Favor tentar novamente em alguns minutos");
        }

        return clienteSistema;
    }

    public List<ClienteSistemaEntity> implementaBuscaPorPlanosVencidosAtivos() {
        log.info("Método que implementa busca por planos vencidos ativos acessado");
        try {
            log.info("Iniciando busca de clientes com planos vencidos que estão ativos ou em período de testes...");
            List<ClienteSistemaEntity> clientesAtrasados = clienteSistemaRepository
                    .buscaPorClientesComPlanosVencidosAtivos(LocalDate.now().toString());

            if (clientesAtrasados.isEmpty()) {
                log.warn("Nenhum cliente com o pagamento da assinatura atrasado foi encontrado");
                throw new ObjectNotFoundException("Nenhum cliente com o pagamento da assinatura atrasado foi encontrado");
            }

            log.info("Retornando clientes encontrados...");
            return clientesAtrasados;
        } catch (Exception e) {
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

    public List<ClienteSistemaEntity> implementaBuscaPorPlanosComCancelamentoAgendadoVencido() {
        log.info("Método que implementa busca por planos com cancelamento agendado vencido acessado");
        try {
            log.info("Iniciando busca de clientes sistêmicos com plano que possui agendamento de " +
                    "cancelamento vencido...");
            List<ClienteSistemaEntity> clientesAtrasados = clienteSistemaRepository
                    .buscaPlanosComAgendamentosDeRemocaoPendentes(LocalDate.now().toString());

            if (clientesAtrasados.isEmpty()) {
                log.warn("Nenhum cliente sistêmico com o pagamento da assinatura atrasado foi encontrado");
                throw new ObjectNotFoundException("Nenhum cliente sistêmico com o pagamento da assinatura " +
                        "atrasado foi encontrado");
            }

            log.info("Retornando clientes sistêmicos encontrados...");
            return clientesAtrasados;
        } catch (Exception e) {
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

}
