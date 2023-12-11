package br.akd.svc.akadion.web.modules.empresa.models.dto.response.page;

import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaPageResponse {
    List<EmpresaResponse> content;
    Boolean empty;
    Boolean first;
    Boolean last;
    Integer number;
    Integer numberOfElements;
    Integer pageNumber;
    Integer pageSize;
    Boolean paged;
    Boolean unpaged;
    Integer size;
    Long totalElements;
    Integer totalPages;

    public EmpresaPageResponse buildPageResponse(Page<EmpresaEntity> empresasEntity) {
        log.info("Método de conversão de empresas do tipo Entity para empresas do tipo Response acessado");

        log.info("Criando lista vazia de objetos do tipo EmpresaResponse...");
        List<EmpresaResponse> empresasResponse = new ArrayList<>();

        log.info("Iniciando iteração da lista de EmpresaEntity obtida na busca para conversão para objetos do tipo " +
                "EmpresaResponse...");
        for (EmpresaEntity empresa : empresasEntity.getContent()) {
            EmpresaResponse empresaResponse = new EmpresaResponse().buildFromEntity(empresa);
            empresasResponse.add(empresaResponse);
        }
        log.info("Iteração finalizada com sucesso. Listagem de objetos do tipo EmpresaResponse preenchida");

        log.info("Iniciando criação de objeto do tipo EmpresaPageResponse, que possui todas as informações referentes " +
                "ao conteúdo da página e à paginação...");
        empresasEntity.getPageable();
        EmpresaPageResponse empresaPageResponse = EmpresaPageResponse.builder()
                .content(empresasResponse)
                .numberOfElements(empresasEntity.getNumberOfElements())
                .pageNumber(empresasEntity.getPageable().getPageNumber())
                .pageSize(empresasEntity.getPageable().getPageSize())
                .size(empresasEntity.getSize())
                .totalElements(empresasEntity.getTotalElements())
                .totalPages(empresasEntity.getTotalPages())
                .build();

        log.info("Objeto do tipo EmpresaPageResponse criado com sucesso. Retornando objeto...");
        return empresaPageResponse;
    }
}
