package ma.s.gcm.dto;

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
public class DocumentDto {
    @JsonView(UserView.Basic.class)
    private Long id;

    @JsonView(UserView.Basic.class)
    private TypeDocumentDto typeDocument;

    @JsonView(UserView.Basic.class)
    private String path;
    
    @JsonView(UserView.Basic.class)
    private AppelOffreDto appelOffre;

    @JsonView(UserView.Basic.class)
    private String description;
    
    @JsonView(UserView.Basic.class)
    private MarcheDto marche;

    @JsonView(UserView.Basic.class)
    private ProjetDto projet;
}
