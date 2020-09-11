package ma.s.gcm.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.NatureCode;
import ma.s.gcm.domain.type.StatutCode;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppelOffreDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private String numOffre;

    @JsonView(UserView.Basic.class)
    private String objet;

    @JsonView(UserView.Basic.class)
    private MaitreOuvrageDto maitreOuvrage;

    @JsonView(UserView.Basic.class)
    private NatureCode natureCode;

    @JsonView(UserView.Basic.class)
    private List<DocumentDto> documents;

    @JsonView(UserView.Basic.class)
    private String organismeCaution;

    @JsonView(UserView.Basic.class)
    private Date datePublication;

    @JsonView(UserView.Basic.class)
    private Date dateOuverture;

    @JsonView(UserView.Basic.class)
    private String heureOuverture;

    @JsonView(UserView.Basic.class)
    private StatutCode statut;

    @JsonView(UserView.Basic.class)
    private Double caution;
    
    @JsonView(UserView.Basic.class)
	private List<PrestationDto> prestations;
    
    @JsonView(UserView.Basic.class)
    private Boolean adjudicataire;
}