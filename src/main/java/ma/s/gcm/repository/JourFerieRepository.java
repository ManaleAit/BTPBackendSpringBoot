package ma.s.gcm.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.JourFerie;
@Repository
public interface JourFerieRepository extends JpaRepository<JourFerie, Long> {

	
	  @Query(value = "select Max(J.dateJour) from JourFerie J")
	  Date getMaxDate();
	
}
