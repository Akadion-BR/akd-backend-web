package br.akd.svc.akadion.web.modules.empresa.repository;

import br.akd.svc.akadion.web.modules.empresa.models.entity.EmpresaEntity;
import br.akd.svc.akadion.web.modules.empresa.models.entity.id.EmpresaId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, EmpresaId> {

    Optional<EmpresaEntity> findByCnpj(String cnpj);

    Optional<EmpresaEntity> findByEndpoint(String endpoint);

    Optional<EmpresaEntity> findByRazaoSocial(String razaoSocial);

    Optional<EmpresaEntity> findByInscricaoEstadual(String inscricaoEstadual);

    Optional<EmpresaEntity> findByInscricaoMunicipal(String inscricaoMunicipal);

    @Query(value = "SELECT e FROM EmpresaEntity e " +
            "WHERE e.clienteSistema.id = :idClienteSistemaSessao " +
            "AND ((:campoBusca IS NULL AND (:somenteEmpresasAtivas IS NULL OR e.ativa = :somenteEmpresasAtivas)) " +
            "OR (upper(e.nome) LIKE %:campoBusca% and (:somenteEmpresasAtivas IS NULL OR e.ativa = :somenteEmpresasAtivas) " +
            "OR upper(e.razaoSocial) LIKE %:campoBusca% and (:somenteEmpresasAtivas IS NULL OR e.ativa = :somenteEmpresasAtivas) " +
            "OR lower(e.email) LIKE %:campoBusca% and (:somenteEmpresasAtivas IS NULL OR e.ativa = :somenteEmpresasAtivas) " +
            "OR upper(e.cnpj) LIKE %:campoBusca% and (:somenteEmpresasAtivas IS NULL OR e.ativa = :somenteEmpresasAtivas)))")
    Page<EmpresaEntity> buscaPaginadaPorEmpresas(Pageable pageable,
                                                 @Param("idClienteSistemaSessao") UUID idClienteSistemaSessao,
                                                 @Param("campoBusca") String campoBusca,
                                                 @Param("somenteEmpresasAtivas") Boolean somenteEmpresasAtivas);
}
