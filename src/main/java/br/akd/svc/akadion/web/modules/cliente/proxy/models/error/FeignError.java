package br.akd.svc.akadion.web.modules.cliente.proxy.models.error;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeignError {
    String code;
    String description;
}
