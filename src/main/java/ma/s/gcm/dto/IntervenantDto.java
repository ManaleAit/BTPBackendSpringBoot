package ma.s.gcm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.Projet;
import ma.s.gcm.domain.type.NatureActiviteCode;
import ma.s.gcm.domain.type.TypeIntervenant;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntervenantDto {
	@JsonView(UserView.Basic.class)
	private Long id;
	@JsonView(UserView.Basic.class)
	private NatureActiviteCode natureActivite;
	@JsonView(UserView.Basic.class)
	private TypeIntervenant type;
	@JsonView(UserView.Basic.class)
	private String nom;
	@JsonView(UserView.Basic.class)
	private String prenom;
	@JsonView(UserView.Basic.class)
	private String numCIN;
	@JsonView(UserView.Basic.class)
	private String telephone;
	@JsonView(UserView.Basic.class)
	private String email;
	@JsonView(UserView.Basic.class)
	private List<ProjetDto> projets;
}
