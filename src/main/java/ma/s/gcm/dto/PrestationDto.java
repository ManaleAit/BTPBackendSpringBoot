package ma.s.gcm.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrestationDto {
	@JsonView(UserView.Basic.class)
	private Long id;
	
	@JsonView(UserView.Basic.class)
	private String naturePrestation;
	
	@JsonView(UserView.Basic.class)
	private Double prixPrestations;
	
	@JsonView(UserView.Basic.class)	
	private AppelOffreDto appelOffre;
	
	@JsonView(UserView.Basic.class)	
	private List<FactureDto> factures;
	
	@JsonView(UserView.Basic.class)	
	private Long idAppelOffre;
	
	@JsonView(UserView.Basic.class)	
	private Long idFacture;

}
