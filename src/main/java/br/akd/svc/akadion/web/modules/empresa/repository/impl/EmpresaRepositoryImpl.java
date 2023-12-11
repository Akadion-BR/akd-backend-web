package br.akd.svc.akadion.web.modules.empresa.repository.impl;

import br.akd.svc.akadion.web.exceptions.ObjectNotFoundException;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.empresa.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EmpresaRepositoryImpl {

    @Autowired
    EmpresaRepository empresaRepository;

    public EmpresaEntity implementaPersistencia(EmpresaEntity empresaEntity) {
        log.info("Método que implementa persistência de nova empresa acessado. Empresa: {}", empresaEntity);
        return empresaRepository.save(empresaEntity);
    }

    public Page<EmpresaEntity> implementaBuscaPaginadaPorEmpresas(Pageable pageable,
                                                                  UUID idClienteSistemaSessao,
                                                                  String campoBusca,
                                                                  Boolean somenteEmpresasAtivas) {
        log.info("Iniciando query de busca de empresas pelos parâmetros informados...");
        Page<EmpresaEntity> empresaPage = empresaRepository.buscaPaginadaPorEmpresas(
                pageable, idClienteSistemaSessao, campoBusca, somenteEmpresasAtivas);
        log.info("Busca paginada por empresas realizada com sucesso. Retornando lista obtida...");
        return empresaPage;
    }

    public Optional<EmpresaEntity> implementaBuscaPorCnpj(String cnpj) {
        log.info("Método que implementa busca de empresa por cnpj acessado. Cnpj: {}", cnpj);
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaEntity> implementaBuscaPorEndpoint(String endpoint) {
        log.info("Método que implementa busca de empresa por endpoint acessado. Endpoint: {}", endpoint);
        return empresaRepository.findByEndpoint(endpoint);
    }

    public Optional<EmpresaEntity> implementaBuscaPorRazaoSocial(String razaoSocial) {
        log.info("Método que implementa busca de empresa por razão social acessado. Razão social: {}", razaoSocial);
        return empresaRepository.findByRazaoSocial(razaoSocial);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoEstadual(String inscricaoEstadual) {
        log.info("Método que implementa busca de empresa por inscrição estadual acessado. Inscrição estaudal: {}", inscricaoEstadual);
        return empresaRepository.findByInscricaoEstadual(inscricaoEstadual);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoMunicipal(String inscricaoMunicipal) {
        log.info("Método que implementa busca de empresa por inscrição municipal acessado. Inscrição municipal: {}", inscricaoMunicipal);
        return empresaRepository.findByInscricaoMunicipal(inscricaoMunicipal);
    }

    public EmpresaEntity implementaBuscaPorId(EmpresaId empresaId) {

        log.info("Método que implementa busca de empresa por id acessado. Id: {}", empresaId);

        Optional<EmpresaEntity> empresaEntity = empresaRepository.findById(empresaId);

        EmpresaEntity empresa;
        if (empresaEntity.isPresent()) {
            empresa = empresaEntity.get();
            log.info("Empresa encontrada: {}", empresa);
        } else {
            log.warn("Nenhuma empresa foi encontrada com o id {}", empresaId);
            throw new ObjectNotFoundException("Nenhuma empresa foi encontrada com o id informado");
        }

        log.info("Retornando a empresa encontrada...");
        return empresa;
    }
}
