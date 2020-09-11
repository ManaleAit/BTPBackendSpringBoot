package ma.s.gcm.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class EtablissementFinancier {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ETANLISSEMENT_SEQ")
	@SequenceGenerator(name = "ID_ETANLISSEMENT_SEQ", sequenceName = "ID_ETANLISSEMENT_SEQ")
	private Long id;

	private String nom;

	private String abreviation;

	private String adresse;

	private String telephone;

	private String fixe;

	private String faxe;

	@OneToMany(mappedBy = "etablissement")
	private List<Caution> cautions;
}
