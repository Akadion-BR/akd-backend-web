package br.akd.svc.akadion.web.modules.plano.services.crud;

import br.akd.svc.akadion.web.modules.plano.models.dto.request.PlanoRequest;
import br.akd.svc.akadion.web.modules.plano.models.dto.response.PlanoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface PlanoService {
    @Transactional
    PlanoResponse atualizaPlanoDoClienteSistemico(UUID idClienteSistema,
                                                  PlanoRequest planoRequest) throws JsonProcessingException;

    @Transactional
    PlanoResponse cancelaPlanoDoClienteSistemico(UUID idClienteSistema) throws JsonProcessingException;
}
