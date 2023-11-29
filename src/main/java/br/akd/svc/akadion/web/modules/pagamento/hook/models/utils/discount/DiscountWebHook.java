package br.akd.svc.akadion.web.modules.pagamento.hook.models.utils.discount;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DiscountWebHook {
    private Double value;
    private Integer dueDateLimitDays;
    private String type;
}
