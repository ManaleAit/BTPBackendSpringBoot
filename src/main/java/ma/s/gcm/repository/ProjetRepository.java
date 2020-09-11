package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Projet;
@Repository
public interface ProjetRepository   extends JpaRepository<Projet, Long> {

}
