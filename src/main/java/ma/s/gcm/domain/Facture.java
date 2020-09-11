package ma.s.gcm.domain;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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

public class Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_FACTURE_SEQ")
	@SequenceGenerator(name = "ID_FACTURE_SEQ", sequenceName = "ID_FACTURE_SEQ")
	private Long id;
	
	private Double montantGlobale;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private MaitreOuvrage maitreOuvrage;
	
	@ManyToMany
	private List<Prestation> prestations;

}
