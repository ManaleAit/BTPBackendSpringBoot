package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Prestation;


@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long> {

}

