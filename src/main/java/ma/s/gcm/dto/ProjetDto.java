package ma.s.gcm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ma.s.gcm.domain.Intervenant;
import ma.s.gcm.domain.type.StatutCode;
import ma.s.gcm.dto.views.UserView;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private String numInterneProjet;

    @JsonView(UserView.Basic.class)
    private String IntituleProjet;

    @JsonView(UserView.Basic.class)
    private StatutCode statut;

    @JsonView(UserView.Basic.class)
    private String EtapesProjet;

    @JsonView(UserView.Basic.class)
    private Double montant;

    @JsonView(UserView.Basic.class)
    private Date dateDebut;

    @JsonView(UserView.Basic.class)
    private Date dateFin;

    @JsonView(UserView.Basic.class)
    private List<DocumentDto> documents;

    @JsonView(UserView.Basic.class)
    private Boolean intervenantRemunere;

    @JsonView(UserView.Basic.class)
    private Long nombreUnite;
    @JsonView(UserView.Basic.class)
    private String lotIntervention;
    
    @JsonView(UserView.Basic.class)
    private Date delais;
    
    @JsonView(UserView.Basic.class)
    private Long quantite;
    
    @JsonView(UserView.Basic.class)
    private String unite;
    
    @JsonView(UserView.Basic.class)
    private Long prixUnitaire;
    
    @JsonView(UserView.Basic.class)
    private String suiviReglement;
    @JsonView(UserView.Basic.class)
	private List<IntervenantDto> intervenants;
}
