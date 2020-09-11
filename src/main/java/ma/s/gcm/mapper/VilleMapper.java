package ma.s.gcm.mapper;

import ma.s.gcm.domain.Ville;
import ma.s.gcm.dto.VilleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class VilleMapper {
	private VilleMapper() {
	}

	public static VilleDto toDto(Ville ville) {
		if(ville==null) {
    		return null;
    	}
		return VilleDto.builder().id(ville.getId()).libelle(ville.getLibelle()).build();
	}

	public static List<VilleDto> toDtos(List<Ville> villes) {
		if(villes==null) {
    		return null;
    	}
			return villes.stream().filter(Objects::nonNull).map(VilleMapper::toDto).collect(Collectors.toList());
		
	}

	public static Ville toEntity(VilleDto villeDto) {
		if (villeDto != null) {
			return Ville.builder().id(villeDto.getId()).libelle(villeDto.getLibelle()).build();
		} else {
			return null;
		}
	}
}
