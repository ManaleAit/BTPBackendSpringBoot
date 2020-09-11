package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Intervenant;
@Repository
public interface IntervenantRepository extends JpaRepository<Intervenant, Long> {
	public Intervenant findByNumCIN(String NumCIN);

}
