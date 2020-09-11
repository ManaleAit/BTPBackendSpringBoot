package ma.s.gcm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fonction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_FONCTION_SEQ")
    @SequenceGenerator(name = "ID_FONCTION_SEQ", sequenceName = "ID_FONCTION_SEQ")
    private Long id;    
    @NotNull
    @Column(unique = true)
    private String libelle;
}
