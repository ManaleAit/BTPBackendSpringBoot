package ma.s.gcm.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.MarcheEtatCode;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarcheDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private String numMarche;

    @JsonView(UserView.Basic.class)
    private String objet;

    @JsonView(UserView.Basic.class)
    private MaitreOuvrageDto maitreOuvrage;

    @JsonView(UserView.Basic.class)
    private Date ordreServiceCommencer;

    @JsonView(UserView.Basic.class)
    private Date delai;

    @JsonView(UserView.Basic.class)
    private Date dateFinInitial;

    @JsonView(UserView.Basic.class)
    private Date dateFinPrevue;

    @JsonView(UserView.Basic.class)
    private Date delaiRestant;

    @JsonView(UserView.Basic.class)
    private List<DocumentDto> documents;

    @JsonView(UserView.Basic.class)
    private Double montantCaution;

    @JsonView(UserView.Basic.class)
    private Double mountantGlobal;

    @JsonView(UserView.Basic.class)
    private VilleDto lieuExecution;

    @JsonView(UserView.Basic.class)
    private Date ordreServiceArret;

    @JsonView(UserView.Basic.class)
    private Date ordreServiceReprise;

    @JsonView(UserView.Basic.class)
    private Date dateFinReelle;

    @JsonView(UserView.Basic.class)
    private MarcheEtatCode marcheEtatCode;
}
