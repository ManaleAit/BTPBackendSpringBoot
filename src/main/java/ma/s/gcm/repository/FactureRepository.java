package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Facture;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {

}



