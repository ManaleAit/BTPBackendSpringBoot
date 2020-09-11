package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Marche;

@Repository
public interface MarcheRepository  extends JpaRepository<Marche, Long> {
    public Marche findByNumMarche(String numMarche);
}
