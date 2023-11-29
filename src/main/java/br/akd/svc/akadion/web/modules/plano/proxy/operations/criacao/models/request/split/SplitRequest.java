package br.akd.svc.akadion.web.modules.plano.proxy.operations.criacao.models.request.split;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SplitRequest {
    private String walletId;
    private Double fixedValue;
    private Double percentualValue;
}
