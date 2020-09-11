package ma.s.gcm.domain;

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

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Caution {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CAUTION_SEQ")
	@SequenceGenerator(name = "ID_CAUTION_SEQ", sequenceName = "ID_CAUTION_SEQ")
	private Long id;

	@ManyToOne
	private EtablissementFinancier etablissement;
	
	private Double montantCautionProvisoire;

	private Double montantCautionDefinitive;

}
