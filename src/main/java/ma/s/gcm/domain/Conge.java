package ma.s.gcm.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.EtatConge;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Conge {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CONGE_SEQ")
	@SequenceGenerator(name = "ID_CONGE_SEQ", sequenceName = "ID_CONGE_SEQ")
	private Long id;
	private Date debutPeriode;
	private Date finPeriode;
	@ManyToOne
	private Employee emlpoyee;
	private int duree;
	private EtatConge etat;
	private Boolean vue;
}
