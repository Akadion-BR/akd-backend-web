package br.akd.svc.akadion.web.modules.cliente.proxy.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoPessoaEnum {
    FISICA(0, "Pessoa física"),
    JURIDICA(1, "Pessoa jurídica");

    private final int code;
    private final String desc;
}
