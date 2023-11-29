package br.akd.svc.akadion.web.modules.empresa.repository.impl;

import br.akd.svc.akadion.web.exceptions.ObjectNotFoundException;
import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import br.akd.svc.akadion.web.modules.empresa.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmpresaRepositoryImpl {

    @Autowired
    EmpresaRepository empresaRepository;

    public List<EmpresaEntity> buscaTodasEmpresas() {
        log.debug("Método que implementa busca por todas as empresas cadastradas acessado");
        return empresaRepository.findAll();
    }

    public EmpresaEntity implementaPersistencia(EmpresaEntity empresaEntity) {
        log.debug("Método que implementa persistência de nova empresa acessado. Empresa: {}", empresaEntity);
        return empresaRepository.save(empresaEntity);
    }

    public Optional<EmpresaEntity> implementaBuscaPorCnpj(String cnpj) {
        log.debug("Método que implementa busca de empresa por cnpj acessado. Cnpj: {}", cnpj);
        return empresaRepository.findByCnpj(cnpj);
    }

    public Optional<EmpresaEntity> implementaBuscaPorEndpoint(String endpoint) {
        log.debug("Método que implementa busca de empresa por endpoint acessado. Endpoint: {}", endpoint);
        return empresaRepository.findByEndpoint(endpoint);
    }

    public Optional<EmpresaEntity> implementaBuscaPorRazaoSocial(String razaoSocial) {
        log.debug("Método que implementa busca de empresa por razão social acessado. Razão social: {}", razaoSocial);
        return empresaRepository.findByRazaoSocial(razaoSocial);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoEstadual(String inscricaoEstadual) {
        log.debug("Método que implementa busca de empresa por inscrição estadual acessado. Inscrição estaudal: {}", inscricaoEstadual);
        return empresaRepository.findByInscricaoEstadual(inscricaoEstadual);
    }

    public Optional<EmpresaEntity> implementaBuscaPorInscricaoMunicipal(String inscricaoMunicipal) {
        log.debug("Método que implementa busca de empresa por inscrição municipal acessado. Inscrição municipal: {}", inscricaoMunicipal);
        return empresaRepository.findByInscricaoMunicipal(inscricaoMunicipal);
    }

    public EmpresaEntity implementaBuscaPorId(EmpresaId empresaId) {

        log.debug("Método que implementa busca de empresa por id acessado. Id: {}", empresaId);

        Optional<EmpresaEntity> empresaEntity = empresaRepository.findById(empresaId);

        EmpresaEntity empresa;
        if (empresaEntity.isPresent()) {
            empresa = empresaEntity.get();
            log.debug("Empresa encontrada: {}", empresa);
        } else {
            log.warn("Nenhuma empresa foi encontrada com o id {}", empresaId);
            throw new ObjectNotFoundException("Nenhuma empresa foi encontrada com o id informado");
        }

        log.debug("Retornando a empresa encontrada...");
        return empresa;
    }

}
