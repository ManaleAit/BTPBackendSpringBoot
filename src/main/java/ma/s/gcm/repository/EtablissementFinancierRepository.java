package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.EtablissementFinancier;

@Repository
public interface EtablissementFinancierRepository extends JpaRepository< EtablissementFinancier , Long> {

}
