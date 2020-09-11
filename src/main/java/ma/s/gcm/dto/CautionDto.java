package ma.s.gcm.dto;




import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.dto.views.UserView;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CautionDto {

	@JsonView(UserView.Basic.class)
    private Long id;
	@JsonView(UserView.Basic.class)
	private EtablissementFinancierDto etablissement;
	@JsonView(UserView.Basic.class)
	private Double montantCautionProvisoire;
	@JsonView(UserView.Basic.class)
	private Double montantCautionDefinitive;
}
