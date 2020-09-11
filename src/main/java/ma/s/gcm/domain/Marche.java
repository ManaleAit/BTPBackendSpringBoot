package ma.s.gcm.domain;

import lombok.*;
import ma.s.gcm.domain.type.MarcheEtatCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Marche {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MARCHE_SEQ")
    @SequenceGenerator(name = "ID_MARCHE_SEQ", sequenceName = "ID_MARCHE_SEQ")
    private Long id;

    @Column(unique = true)
    private String numMarche;

    private String objet;

    @ManyToOne
    private MaitreOuvrage maitreOuvrage;

    private Date ordreServiceCommencer;

    private Date delai;

    private Date dateFinInitial;

    private Date dateFinPrevue;

    private Date delaiRestant;
   
   // @OneToOne(cascade = CascadeType.REMOVE)
	@OneToMany(mappedBy = "marche")
	private List<Document> documents;
    
    private Double montantCaution;

    private Double mountantGlobal;

    @ManyToOne
    private Ville lieuExecution;

    private Date ordreServiceArret;

    private Date ordreServiceReprise;

    private Date dateFinReelle;

    @Enumerated(EnumType.STRING)
    private MarcheEtatCode marcheEtatCode;
}
