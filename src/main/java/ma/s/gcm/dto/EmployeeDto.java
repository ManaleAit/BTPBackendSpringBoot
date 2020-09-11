package ma.s.gcm.dto;

import java.util.Date;
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
public class EmployeeDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private String matricule;

    @JsonView(UserView.Basic.class)
    private String nom;

    @JsonView(UserView.Basic.class)
    private String prenom;
    
    @JsonView(UserView.Basic.class)
    private String email;
    
    @JsonView(UserView.Basic.class)
    private String cin;
    
    @JsonView(UserView.Basic.class)
    private String cnss;

    @JsonView(UserView.Basic.class)
    private Date dateNaissance;

    @JsonView(UserView.Basic.class)
    private Date dateRecrutement;

    @JsonView(UserView.Basic.class)
    private FonctionDto fonction;

    @JsonView(UserView.Basic.class)
    private EmployeeDto responsable;
    
    @JsonView(UserView.Basic.class)
    private List<CongeDto> conges;
}
