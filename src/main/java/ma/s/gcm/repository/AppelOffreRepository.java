package ma.s.gcm.repository;

import ma.s.gcm.domain.AppelOffre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppelOffreRepository extends JpaRepository<AppelOffre, Long> {
    AppelOffre findByNumOffre(String numAppel);
}


