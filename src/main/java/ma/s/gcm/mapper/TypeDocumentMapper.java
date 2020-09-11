package ma.s.gcm.mapper;

import ma.s.gcm.domain.TypeDocument;
import ma.s.gcm.dto.TypeDocumentDto;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TypeDocumentMapper {
	private TypeDocumentMapper() {
	}

	public static TypeDocumentDto toDto(TypeDocument typeDocument) {
		if(typeDocument==null) {
    		return null;
    	}
		return TypeDocumentDto.builder().id(typeDocument.getId()).libelle(typeDocument.getLibelle()).build();
	}

	public static List<TypeDocumentDto> toDtos(List<TypeDocument> typeDocuments) {
		if(typeDocuments==null) {
    		return null;
    	}
			return typeDocuments.stream().filter(Objects::nonNull).map(TypeDocumentMapper::toDto)
					.collect(Collectors.toList());
		
	}

	public static TypeDocument toEntity(TypeDocumentDto typeDocumentDto) {
		if (typeDocumentDto != null) {
			return TypeDocument.builder().id(typeDocumentDto.getId()).libelle(typeDocumentDto.getLibelle()).build();
		} else {
			return null;
		}
	}
}
