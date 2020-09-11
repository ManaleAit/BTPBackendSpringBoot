package ma.s.gcm.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MATRICULE_SEQ")
	@SequenceGenerator(name = "ID_MATRICULE_SEQ", sequenceName = "ID_MATRICULE_SEQ")
	private Long id;
	// @NonNull
	@Column(unique = true)
	private String matricule;

	// @NonNull
	private String nom;

	// @NonNull
	private String prenom;
	
	
	private String email;

	// @NonNull
	private String cin;

	private String cnss;

	// @NonNull
	private Date dateNaissance;

	// @NonNull
	private Date dateRecrutement;

	@ManyToOne
	private Fonction fonction;

	@ManyToOne(cascade = CascadeType.DETACH)
	private Employee responsable;

	@OneToMany(mappedBy = "emlpoyee", cascade = CascadeType.REMOVE)
	private List<Conge> conges;

}
