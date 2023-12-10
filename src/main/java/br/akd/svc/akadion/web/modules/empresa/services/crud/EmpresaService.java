package br.akd.svc.akadion.web.modules.empresa.services.crud;

import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;

import java.util.UUID;

public interface EmpresaService {

    CriaEmpresaResponse criaNovaEmpresa(UUID idClienteSistemaSessao,
                                        EmpresaRequest empresaRequest);

    EmpresaResponse atualizaEmpresa(UUID idClienteSistemaSessao,
                                    UUID uuidEmpresa,
                                    EmpresaRequest empresaRequest);

    EmpresaResponse removeEmpresa(UUID idClienteSistemaSessao,
                                  UUID uuidEmpresa);

}
