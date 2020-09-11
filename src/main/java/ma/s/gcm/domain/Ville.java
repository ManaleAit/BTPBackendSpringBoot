package ma.s.gcm.domain;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_VILLE_SEQ")
    @SequenceGenerator(name = "ID_VILLE_SEQ", sequenceName = "ID_VILLE_SEQ")
    private Long id;

    @NotNull
    private String libelle;
}
