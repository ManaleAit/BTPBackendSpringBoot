package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Caution;


@Repository
public interface CautionRepository  extends JpaRepository<Caution, Long> {
}


