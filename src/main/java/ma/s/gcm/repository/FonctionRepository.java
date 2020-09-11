package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Fonction;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Long> {
	public Fonction findByLibelle(String nom);
}



