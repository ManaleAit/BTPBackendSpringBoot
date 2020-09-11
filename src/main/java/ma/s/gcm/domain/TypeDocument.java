package ma.s.gcm.domain;

import lombok.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TypeDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TYPE_DOCUMENT_SEQ")
    @SequenceGenerator(name = "ID_TYPE_DOCUMENT_SEQ", sequenceName = "ID_TYPE_DOCUMENT_SEQ")
    private Long id;

    @NotNull
    private String libelle;
    
    @OneToMany(mappedBy = "typeDocument",cascade = CascadeType.REMOVE)
    private List<Document> documents;
}
