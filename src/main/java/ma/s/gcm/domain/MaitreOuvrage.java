package ma.s.gcm.domain;

import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaitreOuvrage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_MAITRE_OUVRAGE_SEQ")
    @SequenceGenerator(name = "ID_MAITRE_OUVRAGE_SEQ", sequenceName = "ID_MAITRE_OUVRAGE_SEQ")
    private Long id;
    
    private String nom;
    
    @Column(unique = true)
    private String numeroPatente;
    

    @ManyToOne
    private Ville ville;
    
    private String activite;
    
    
    @NotNull
    private String designation;

	@OneToMany(mappedBy = "maitreOuvrage")
	private List<AppelOffre> appelOffres;
	
	@OneToMany(mappedBy = "maitreOuvrage")
	private List<Facture> facture;

	@OneToMany(mappedBy = "maitreOuvrage" ,cascade = CascadeType.REMOVE)
	private List<Marche> marches;
}

