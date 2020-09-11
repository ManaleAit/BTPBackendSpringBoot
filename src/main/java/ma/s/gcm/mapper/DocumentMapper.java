package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.Document;
import ma.s.gcm.dto.DocumentDto;
public class DocumentMapper {
    private DocumentMapper() {
    }

    public static DocumentDto toDto(Document document) {
     if(document!=null) {
        return DocumentDto.builder()
                .id(document.getId())
                .typeDocument(TypeDocumentMapper.toDto(document.getTypeDocument()))
                .path(document.getPath())
                .description(document.getDescription())
                //.appeloffre(AppelOffreMapper.toDto(document.getAppelOffre()))
               // .marche(MarcheMapper.toDto(document.getMarche()))
                //.projet(ProjetMapper.toDto(document.getProjet()))
                .build();
     }else {
    	 return null;
     }
    }

    public static List<DocumentDto> toDtos(List<Document> Documents) {
    	if(Documents!=null) {
        return Documents.stream().filter(Objects::nonNull)
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    	  }else {
    	    	 return null;
    	     }
   
    }

    public static List<Document> toEntities(List<DocumentDto> Documents) {
    	if(Documents!=null) {
        return Documents.stream().filter(Objects::nonNull)
                .map(DocumentMapper::toEntity)
                .collect(Collectors.toList());
    	 }
    	else {
    	  		return null;
    	  	}
    }

    public static Document toEntity(DocumentDto documentDto) {
      if(documentDto!=null) {
        return Document.builder()
                .id(documentDto.getId())
                .typeDocument(TypeDocumentMapper.toEntity(documentDto.getTypeDocument()))
                .path(documentDto.getPath())
                .description(documentDto.getDescription())
                 .appelOffre(AppelOffreMapper.toEntity(documentDto.getAppelOffre()))
                 .marche(MarcheMapper.toEntity(documentDto.getMarche()))
                 .projet(ProjetMapper.toEntity(documentDto.getProjet()))
                .build();
      }else {
  		return null;
  	}
    }
}

