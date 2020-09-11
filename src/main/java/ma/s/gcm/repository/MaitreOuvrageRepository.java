package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.MaitreOuvrage;

@Repository
public interface  MaitreOuvrageRepository extends JpaRepository<MaitreOuvrage, Long> {
	
	
	public MaitreOuvrage findByNumeroPatente(String numeroPatente);
}


