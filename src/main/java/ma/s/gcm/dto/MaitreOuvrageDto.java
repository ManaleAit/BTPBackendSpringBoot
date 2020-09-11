package ma.s.gcm.dto;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.Facture;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaitreOuvrageDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    @JsonView(UserView.Basic.class)
    private String designation;
    @JsonView(UserView.Basic.class)
    private Collection<AppelOffreDto> AppelOffres;
    @JsonView(UserView.Basic.class)
    private Collection<MarcheDto> Marches;
    @JsonView(UserView.Basic.class)
    private String nom;
    @JsonView(UserView.Basic.class)
    private String numeroPatente;
    @JsonView(UserView.Basic.class)
    private VilleDto ville;
    @JsonView(UserView.Basic.class)
    private String activite;
    @JsonView(UserView.Basic.class)
	private List<FactureDto> facture;
}
