package br.akd.svc.akadion.web.modules.empresa.services.crud;

import br.akd.svc.akadion.web.globals.cpfcnpj.models.CnpjRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.request.EmpresaRequest;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.CriaEmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.EmpresaResponse;
import br.akd.svc.akadion.web.modules.empresa.models.dto.response.page.EmpresaPageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EmpresaService {

    CriaEmpresaResponse criaNovaEmpresa(UUID idClienteSistemaSessao,
                                        EmpresaRequest empresaRequest);

    EmpresaPageResponse obtemEmpresasClienteSistemico(Pageable pageable,
                                                      UUID idClienteSistemaSessao,
                                                      String campoBusca,
                                                      Boolean somenteEmpresasAtivas);

    void realizaValidacaoCnpj(CnpjRequest cnpjRequest);

    EmpresaResponse atualizaEmpresa(UUID idClienteSistemaSessao,
                                    UUID uuidEmpresa,
                                    EmpresaRequest empresaRequest);

    EmpresaResponse removeEmpresa(UUID idClienteSistemaSessao,
                                  UUID uuidEmpresa);

}
