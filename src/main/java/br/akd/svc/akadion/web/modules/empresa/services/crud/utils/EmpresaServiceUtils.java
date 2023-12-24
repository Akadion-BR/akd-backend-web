package br.akd.svc.akadion.web.modules.empresa.services.crud.utils;

import br.akd.svc.akadion.web.modules.cliente.models.entity.ClienteSistemaEntity;
import br.akd.svc.akadion.web.modules.cliente.repository.impl.ClienteSistemaRepositoryImpl;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.CriacaoColaboradorResponse;
import br.akd.svc.akadion.web.modules.external.erp.colaborador.proxy.impl.ColaboradorProxyImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class EmpresaServiceUtils {

    @Autowired
    ColaboradorProxyImpl colaboradorProxy;

    @Autowired
    ClienteSistemaRepositoryImpl clienteSistemaRepositoryImpl;

    @Transactional
    public CriaEmpresaResponse implementaLogicaDeCadastroEmpresa(EmpresaRequest empresaRequest,
                                                                 ClienteSistemaEntity clienteSistema) {
        log.info("Iniciando acesso ao método de implementação de persistência do cliente sistêmico...");
        ClienteSistemaEntity clienteSistemaEntity =
                clienteSistemaRepositoryImpl.implementaPersistencia(clienteSistema);
        log.info("Persistência do cliente sistêmico realizada com sucesso");

        log.info("Obtendo empresa criada da lista das empresas do cliente persistido...");
        EmpresaEntity empresaCriada = clienteSistemaEntity
                .obtemEmpresaPorRazaoSocial(empresaRequest.getRazaoSocial());
        log.info("Empresa persistida obtida com sucesso");

        log.info("Iniciando acesso ao método de criação de novo colaborador admin para empresa...");
        CriacaoColaboradorResponse criacaoColaboradorResponse =
                colaboradorProxy.realizaCriacaoDeColaboradorNoErp(new EmpresaId(empresaCriada.getClienteSistema().getId(), empresaCriada.getId()));
        log.info("Colaborador criado com sucesso para nova empresa");

        log.info("Iniciando objeto do tipo CriaEmpresaResponse...");
        CriaEmpresaResponse criaEmpresaResponse = new CriaEmpresaResponse(
                clienteSistemaEntity.getId(),
                criacaoColaboradorResponse,
                new EmpresaResponse().buildFromEntity(empresaCriada));
        log.info("Objeto criado com sucesso");

        log.info("Método responsável por realizar a criação da empresa executado com sucesso. Retornando response...");
        return criaEmpresaResponse;
    }

}
