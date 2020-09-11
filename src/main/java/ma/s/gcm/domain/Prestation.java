package ma.s.gcm.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prestation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PRESTATION_SEQ")
    @SequenceGenerator(name = "ID_PRESTATION_SEQ", sequenceName = "ID_PRESTATION_SEQ")
    private Long id;

    private String naturePrestation;
    
    private Double prixPrestations;
    

	@ManyToOne
	private AppelOffre appelOffre;
   
	@ManyToMany
	private List<Facture> factures;
    
}
