package ma.s.gcm.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_DOCUMENT_SEQ")
	@SequenceGenerator(name = "ID_DOCUMENT_SEQ", sequenceName = "ID_DOCUMENT_SEQ")
	private Long id;

	@ManyToOne
	private TypeDocument typeDocument;

	private String path;

	private String description;

	@ManyToOne
	private AppelOffre appelOffre;

	@ManyToOne
	private Marche marche;

	@ManyToOne
	private Projet projet;

}
