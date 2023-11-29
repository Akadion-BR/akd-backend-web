package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.creditcard;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardWebHook {
    private String creditCardNumber;
    private String creditCardBrand;
    private String creditCardToken;
}
