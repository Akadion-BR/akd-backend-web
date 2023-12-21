package br.akd.svc.akadion.web.modules.empresa.models.enums.fiscal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RegimeTributarioEnum {

    SIMPLES_NACIONAL (1, "Simples nacional"),
    SIMPLES_NACIONAL_EXCESSO_SUBLIME(2, "Simples nacional - Excesso de sublimite de receita bruta"),
    REGIME_NORMAL(3, "Regime normal");

    private final int code;
    private final String desc;

}
