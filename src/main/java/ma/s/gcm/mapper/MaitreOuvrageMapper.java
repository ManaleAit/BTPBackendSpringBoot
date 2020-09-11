package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import ma.s.gcm.domain.MaitreOuvrage;
import ma.s.gcm.dto.MaitreOuvrageDto;

public class MaitreOuvrageMapper {
	private MaitreOuvrageMapper() {
	}

	public static MaitreOuvrageDto toDto(MaitreOuvrage maitreOuvrage) {
		if(maitreOuvrage==null) {
    		return null;
    	}
		return MaitreOuvrageDto.builder().id(maitreOuvrage.getId())
				.nom(maitreOuvrage.getNom())
				.numeroPatente(maitreOuvrage.getNumeroPatente())
				.ville(VilleMapper.toDto(maitreOuvrage.getVille())).activite(maitreOuvrage.getActivite())
				.designation(maitreOuvrage.getDesignation()).build();
	}
	
	
	

	public static List<MaitreOuvrageDto> toDtos(List<MaitreOuvrage> MaitreOuvrages) {
		if (MaitreOuvrages != null) {
			return MaitreOuvrages.stream().filter(Objects::nonNull).map(MaitreOuvrageMapper::toDto)
					.collect(Collectors.toList());
		} else

		{
			return null;
		}
	}

	public static MaitreOuvrage toEntity(MaitreOuvrageDto maitreOuvrageDto) {
		if (maitreOuvrageDto != null) {
			return MaitreOuvrage.builder().id(maitreOuvrageDto.getId()).designation(maitreOuvrageDto.getDesignation())
					.nom(maitreOuvrageDto.getNom())
					.numeroPatente(maitreOuvrageDto.getNumeroPatente())
					.ville(VilleMapper.toEntity(maitreOuvrageDto.getVille())).activite(maitreOuvrageDto.getActivite())
					.build();
		} else {
			return null;
		}
	}
}
