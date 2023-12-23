package br.akd.svc.akadion.web.globals.proxy;

import br.akd.svc.akadion.web.exceptions.custom.InternalErrorException;
import br.akd.svc.akadion.web.exceptions.custom.InvalidRequestException;
import br.akd.svc.akadion.web.exceptions.models.proxy.focus.FocusErrorResponse;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyModuleEnum;
import br.akd.svc.akadion.web.globals.proxy.enums.ProxyOperationEnum;
import br.akd.svc.akadion.web.utils.Constantes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FocusProxyUtils {

    public void realizaValidacaoResponseFocusNfe(ResponseEntity<?> responseEntity,
                                                 ProxyModuleEnum proxyModuleEnum,
                                                 ProxyOperationEnum proxyOperationEnum) {
        if (responseEntity == null) {
            log.error("O valor retornado pela integradora na [{}] do(a) [{}] é nulo",
                    proxyOperationEnum.getDesc(), proxyModuleEnum.getDesc());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            log.error("Ocorreu um erro de status de resposta no processo de [{}] do(a) [{}] na integradora " +
                            "Focus NFE. Corpo da requisição: {}",
                    proxyOperationEnum.getDesc(), proxyModuleEnum.getDesc(), responseEntity.getBody());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }

        if (responseEntity.getBody() == null) {
            log.error("O valor retornado pela Focus NFE no processo de [{}] do(a) [{}}] é nulo",
                    proxyOperationEnum.getDesc(), proxyModuleEnum.getDesc());
            throw new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

    public RuntimeException realizaTratamentoRetornoErroFeignException(FeignException feignException,
                                                                       ProxyModuleEnum proxyModuleEnum,
                                                                       ProxyOperationEnum proxyOperationEnum) {
        log.info("Método de tratamento de erro retornado pelo Feign Client acessado");

        log.info("Iniciando tentativa de conversão de erro em JSON string para objeto do " +
                "tipo FeignErrorResponse...");
        try {
            ObjectMapper mapper = new ObjectMapper();
            FocusErrorResponse errorResponse = mapper.readValue(
                    feignException.contentUTF8(), FocusErrorResponse.class);
            return new InvalidRequestException(errorResponse.retornaListaDeErrosComoUnicaString());
        } catch (UnrecognizedPropertyException unrecognizedPropertyException) {
            log.error("Ocorreu um erro interno durante o processo de [{}}] do(a) [{}] na integração via feign " +
                    "client: {}", proxyOperationEnum.getDesc(), proxyModuleEnum.getDesc(), unrecognizedPropertyException.getMessage());
            return new InternalErrorException(Constantes.ERRO_INTERNO);
        } catch (Exception e) {
            log.error("Ocorreu um erro interno durante o processo de [{}}] do(a) [{}] na integração via feign " +
                    "client: {}", proxyOperationEnum.getDesc(), proxyModuleEnum.getDesc(), e.getMessage());
            return new InternalErrorException(Constantes.ERRO_INTERNO);
        }
    }

}
