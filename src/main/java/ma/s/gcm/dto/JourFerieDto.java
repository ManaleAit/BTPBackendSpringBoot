package ma.s.gcm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.TypeJourFerieCode;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JourFerieDto {

	@JsonView(UserView.Basic.class)
	private Long id;
	@JsonView(UserView.Basic.class)
	private String libelle;
	@JsonView(UserView.Basic.class)
	private Date dateJour;
	@JsonView(UserView.Basic.class)
	private TypeJourFerieCode type;
	@JsonView(UserView.Basic.class)
	private int nbJours;
}
