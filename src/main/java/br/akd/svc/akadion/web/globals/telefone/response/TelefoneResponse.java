package br.akd.svc.akadion.web.globals.telefone.response;

import br.akd.svc.akadion.web.globals.telefone.entity.TelefoneEntity;
import br.akd.svc.akadion.web.globals.telefone.enums.TipoTelefoneEnum;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneResponse {
    private Integer prefixo;
    private String numero;
    private TipoTelefoneEnum tipoTelefone;

    public TelefoneResponse buildFromEntity(TelefoneEntity telefoneEntity) {
        log.info("Método de conversão de objeto do tipo TelefoneEntity para objeto do tipo TelefoneResponse acessado");

        log.info("Iniciando construção do objeto TelefoneResponse...");
        TelefoneResponse telefoneResponse = telefoneEntity != null
                ? TelefoneResponse.builder()
                .prefixo(telefoneEntity.getPrefixo())
                .numero(telefoneEntity.getNumero())
                .tipoTelefone(telefoneEntity.getTipoTelefone())
                .build()
                : null;

        log.debug("Objeto TelefoneResponse buildado com sucesso. Retornando...");
        return telefoneResponse;
    }
}
