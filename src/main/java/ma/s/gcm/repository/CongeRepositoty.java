package ma.s.gcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Conge;
@Repository
public interface CongeRepositoty extends JpaRepository<Conge, Long> {

	@Query("SELECT t FROM Conge t WHERE t.vue=false")
    List<Conge> findCongeDemande();
}

