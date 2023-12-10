package br.akd.svc.akadion.web.modules.empresa.controller;

import br.akd.svc.akadion.web.config.UserSS;
import br.akd.svc.akadion.web.exceptions.InvalidRequestException;
import br.akd.svc.akadion.web.exceptions.ObjectNotFoundException;
import br.akd.svc.akadion.web.exceptions.UnauthorizedAccessException;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.services.crud.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.UUID;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/site/v1/empresa")
@Produces({MediaType.APPLICATION_JSON, "application/json"})
@Consumes({MediaType.APPLICATION_JSON, "application/json"})
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    /**
     * Cadastro de novo cliente
     * Este método tem como objetivo disponibilizar o endpoint de acionamento da lógica de criação de novo cliente
     *
     * @param userDetails    Dados do cliente sistêmico logado na sessão atual
     * @param empresaRequest Objeto contendo todos os atributos necessários para a criação de uma nova empresa
     * @return Retorna objeto Empresa criada convertida para o tipo CriaEmpresaResponse
     * @throws InvalidRequestException Exception lançada caso ocorra alguma falha interna na criação da empresa
     */
    @PostMapping
    @Tag(name = "Cadastro de nova empresa")
    @Operation(summary = "Essa requisição tem como objetivo realizar a persistência de uma nova empresa no banco " +
            "de dados", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Empresa criada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CriaEmpresaResponse.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Falha na criação da empresa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestException.class))}),
            @ApiResponse(responseCode = "401",
                    description = "Falha de autenticação",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnauthorizedAccessException.class))})
    })
    public ResponseEntity<CriaEmpresaResponse> criaEmpresa(@AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody EmpresaRequest empresaRequest) {
        log.info("Método controlador de criação de nova empresa acessado");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(empresaService.criaNovaEmpresa(
                        ((UserSS) userDetails).getClienteId(),
                        empresaRequest));
    }

    /**
     * Atualiza empresa
     * Este método tem como objetivo disponibilizar o endpoint de acionamento da lógica de atualização de empresa por id
     *
     * @param userDetails    Dados do cliente sistêmico logado na sessão atual
     * @param idEmpresa      Id da empresa a ser atualizada
     * @param empresaRequest objeto que deve conter todos os dados necessários para atualização da empresa
     * @return Retorna objeto Empresa encontrada convertida para o tipo EmpresaResponse
     */
    @PutMapping("/{idEmpresa}")
    @Tag(name = "Atualização de empresa")
    @Operation(summary = "Essa requisição tem como objetivo atualizar os dados de uma empresa já existente no " +
            "banco de dados", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmpresaResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Falha na atualização da empresa",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestException.class))}),
            @ApiResponse(responseCode = "401", description = "Falha de autenticação",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UnauthorizedAccessException.class))}),
    })
    public ResponseEntity<EmpresaResponse> atualizaEmpresa(@AuthenticationPrincipal UserDetails userDetails,
                                                           @PathVariable UUID idEmpresa,
                                                           @RequestBody EmpresaRequest empresaRequest) {
        log.info("Método controlador de atualização de empresa acessado");
        return ResponseEntity.ok().body(
                empresaService.atualizaEmpresa(
                        ((UserSS) userDetails).getClienteId(),
                        idEmpresa,
                        empresaRequest));
    }

    /**
     * Exclusão de empresa
     * Este método tem como objetivo disponibilizar o endpoint de acionamento da lógica de exclusão de empresa por id
     *
     * @param userDetails Dados do cliente sistêmico logado na sessão atual
     * @param idEmpresa   Id da empresa a ser removida
     * @return Retorna objeto Empresa removida convertida para o tipo EmpresaResponse
     */
    @DeleteMapping("/{idEmpresa}")
    @Tag(name = "Remoção de empresa")
    @Operation(summary = "Esse endpoint tem como objetivo realizar a exclusão de uma empresa", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Empresa excluída com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Nenhuma empresa foi encontrada com o uuid informado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ObjectNotFoundException.class))}),
            @ApiResponse(responseCode = "400",
                    description = "A empresa selecionada  já foi excluído",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestException.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Nenhuma empresa foi encontrada para remoção",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestException.class))}),
            @ApiResponse(responseCode = "400",
                    description = "Não é possível remover uma empresa que já foi excluída",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = InvalidRequestException.class))}),
    })
    public ResponseEntity<EmpresaResponse> removeEmpresa(@AuthenticationPrincipal UserDetails userDetails,
                                                         @PathVariable UUID idEmpresa) {
        log.info("Método controlador de remoção de empresa acessado");
        return ResponseEntity.ok().body(
                empresaService.removeEmpresa(
                        ((UserSS) userDetails).getClienteId(),
                        idEmpresa));
    }

}
