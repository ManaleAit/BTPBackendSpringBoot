package ma.s.gcm.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.NatureCode;
import ma.s.gcm.domain.type.StatutCode;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppelOffre {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_OFFRE_SEQ")
	@SequenceGenerator(name = "ID_OFFRE_SEQ", sequenceName = "ID_OFFRE_SEQ")
	private Long id;

	@Column(unique = true)
	private String numOffre;

	private String objet;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private MaitreOuvrage maitreOuvrage;

	@Enumerated(EnumType.STRING)
	private NatureCode natureCode;

	@OneToMany(mappedBy = "appelOffre")
	private List<Document> documents;

	private String organismeCaution;

	private Date datePublication;

	private Date dateOuverture;

	private String heureOuverture;

	@Enumerated(EnumType.STRING)
	private StatutCode statut;

	private Double caution;

	@OneToMany(mappedBy = "appelOffre")
	private List<Prestation> prestations;

	private Boolean adjudicataire;
}