package br.akd.svc.akadion.web.modules.pagamento.hook.services.categorias.vencido.utils;

import br.akd.svc.akadion.web.modules.pagamento.models.entity.PagamentoSistemaEntity;
import br.akd.svc.akadion.web.modules.pagamento.models.enums.StatusPagamentoSistemaEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AtrasoPagamentoWebhookUtil {

    public void realizaAtualizacaoNoObjetoPagamentoParaAtraso(PagamentoSistemaEntity pagamentoEntity) {
        log.info("Setando status do pagamento como atrasado...");
        pagamentoEntity.setStatusPagamentoSistemaEnum(StatusPagamentoSistemaEnum.ATRASADO);

    }

}
