package ma.s.gcm.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.NatureActiviteCode;
import ma.s.gcm.domain.type.TypeIntervenant;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Intervenant {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_INTERVENANT_SEQ")
	@SequenceGenerator(name = "ID_INTERVENANT_SEQ", sequenceName = "ID_INTERVENANT_SEQ")
	private Long id;
	private NatureActiviteCode natureActivite;
	private TypeIntervenant type;
	private String nom;
	private String prenom;
	@Column(unique = true)
	private String numCIN;
	private String telephone;
	private String email;
	@ManyToMany
	private List<Projet> projets;

}
