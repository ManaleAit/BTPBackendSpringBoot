package ma.s.gcm.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.TypeJourFerieCode;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JourFerie {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_JOURFERIE_SEQ")
	@SequenceGenerator(name = "ID_JOURFERIE_SEQ", sequenceName = "ID_JOURFERIE_SEQ")
	private Long id;
	
	
	@NotNull
	private String libelle;
	
	private Date dateJour;
	
	@Enumerated(EnumType.STRING)
	private TypeJourFerieCode type;
	
	private int nbJours;
	
}
