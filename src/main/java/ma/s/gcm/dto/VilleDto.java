package ma.s.gcm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ma.s.gcm.dto.views.UserView;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VilleDto {
    @JsonView(UserView.Basic.class)
    private Long id;
    
    @JsonView(UserView.Basic.class)
    private String libelle;
}
