package ma.s.gcm.domain;

import lombok.*;
import ma.s.gcm.domain.type.StatutCode;

import javax.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_PROJET_SEQ")
    @SequenceGenerator(name = "ID_PROJET_SEQ", sequenceName = "ID_PROJET_SEQ")
    private Long id;

    private String numInterneProjet;

    private String IntituleProjet;

    private StatutCode statut;

    private String EtapesProjet;

    private Double montant;

    private Date dateDebut;

    private Date dateFin;

	@OneToMany(mappedBy = "projet")
	private List<Document> documents;
    
    private Boolean intervenantRemunere;
    
    private String lotIntervention;
    
    private Date delais;
    
    private Long quantite;
    
    private Long prixUnitaire;
    
    private String unite;
    
    private String suiviReglement;
    

    private Long nombreUnite;

    @ManyToMany
/*	@JoinTable(
	  name = "intervenant", 
	  joinColumns = @JoinColumn(name = "projet_id"), 
	  inverseJoinColumns = @JoinColumn(name = "intervenant"))*/
	private List<Intervenant> intervenants;
}
