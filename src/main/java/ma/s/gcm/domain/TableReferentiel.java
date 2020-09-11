package ma.s.gcm.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
public class TableReferentiel {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TABLEREF_SEQ")
	    @SequenceGenerator(name = "ID_TABLEREF_SEQ", sequenceName = "ID_TABLEREF_SEQ")
	    private Long id;
	    
	    private String intituleJourFerie;
	    
		private Date dateJour;
		
		@Enumerated(EnumType.STRING)
		private TypeJourFerieCode type;
		
		private int nbJours;
}
