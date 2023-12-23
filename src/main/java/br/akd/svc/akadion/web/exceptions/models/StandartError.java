package br.akd.svc.akadion.web.exceptions.models;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandartError {
    private String localDateTime;
    private Integer status;
    private String error;
    private String path;
}
