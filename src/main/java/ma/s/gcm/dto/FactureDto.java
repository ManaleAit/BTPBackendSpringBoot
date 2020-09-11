package ma.s.gcm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.MaitreOuvrage;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureDto {

	@JsonView(UserView.Basic.class)
	private Long id;
	@JsonView(UserView.Basic.class)
	private Double montantGlobale;
	@JsonView(UserView.Basic.class)
	private MaitreOuvrageDto maitreOuvrage; 
    @JsonView(UserView.Basic.class)
	private List<PrestationDto> prestations;
}
