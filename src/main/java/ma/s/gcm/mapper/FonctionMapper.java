package ma.s.gcm.mapper;

import ma.s.gcm.domain.Fonction;
import ma.s.gcm.dto.FonctionDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FonctionMapper {
    private FonctionMapper() {
    }

    public static FonctionDto toDto(Fonction fonction) {
    	if(fonction==null) {
    		return null;
    	}
        return FonctionDto.builder()
                .id(fonction.getId())
                .libelle(fonction.getLibelle())
                .build();
    }

    public static List<FonctionDto> toDtos(List<Fonction> Fonctions) {
    	if(Fonctions==null) {
    		return null;
    	}
        return Fonctions.stream().filter(Objects::nonNull)
                .map(FonctionMapper::toDto)
                .collect(Collectors.toList());
    	
	  	}
   

    public static Fonction toEntity(FonctionDto FonctionDto) {
    	if(FonctionDto!=null) {
        return Fonction.builder()
                .id(FonctionDto.getId())
                .libelle(FonctionDto.getLibelle())
                .build();
    	}else {
    	
    	return null;
    	
    	}
    }
}
