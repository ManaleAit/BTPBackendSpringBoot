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
public class EtablissementFinancierDto {
    @JsonView(UserView.Basic.class)
	private Long id;
    @JsonView(UserView.Basic.class)
	private String nom;
    @JsonView(UserView.Basic.class)
	private String abreviation;
    @JsonView(UserView.Basic.class)
	private String adresse;
    @JsonView(UserView.Basic.class)
	private String telephone;
    @JsonView(UserView.Basic.class)
	private String fixe;
    @JsonView(UserView.Basic.class)
	private String faxe;
    @JsonView(UserView.Basic.class)
    private List<CautionDto> cautions;
}
