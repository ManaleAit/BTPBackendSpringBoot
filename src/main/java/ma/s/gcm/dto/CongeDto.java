package ma.s.gcm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.s.gcm.domain.type.EtatConge;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CongeDto {
	@JsonView(UserView.Basic.class)
	private Long id;
	@JsonView(UserView.Basic.class)
	private Date debutPeriode;
	@JsonView(UserView.Basic.class)
	private Date finPeriode;
	@JsonView(UserView.Basic.class)
	private EmployeeDto emlpoyee;
	@JsonView(UserView.Basic.class)
	private int duree;
	@JsonView(UserView.Basic.class)
	private EtatConge etat;
	@JsonView(UserView.Basic.class)
	private Boolean vue;
    @JsonView(UserView.Basic.class)
    private String matricule;
    @JsonView(UserView.Basic.class)
    private String nom;
    @JsonView(UserView.Basic.class)
    private String prenom;
    @JsonView(UserView.Basic.class)
    private Long idEmployee;
}

