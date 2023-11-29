package br.akd.svc.akadion.web.modules.cliente.services.crud;

import br.akd.svc.akadion.web.globals.cpfcnpj.models.CpfRequest;
import br.akd.svc.akadion.web.modules.cliente.models.dto.request.atualizacao.AtualizaClienteSistemaRequest;
import br.akd.svc.akadion.web.modules.cliente.models.dto.request.criacao.ClienteSistemaRequest;
import br.akd.svc.akadion.web.modules.cliente.models.dto.response.ClienteSistemaResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface ClienteSistemaService {

    ClienteSistemaResponse cadastraNovoCliente(ClienteSistemaRequest clienteSistemaRequest) throws JsonProcessingException;

    List<ClienteSistemaResponse> buscaTodos();

    void realizaValidacaoCpf(CpfRequest cpf);

    ClienteSistemaResponse atualizaDadosCliente(UUID uuidClienteSistema,
                                                AtualizaClienteSistemaRequest atualizaClienteSistemaRequest) throws JsonProcessingException;


}
