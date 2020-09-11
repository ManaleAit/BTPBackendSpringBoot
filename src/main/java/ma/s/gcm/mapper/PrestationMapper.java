package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.AppelOffre;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.PrestationDto;

public class PrestationMapper {
	private PrestationMapper() {
	}

	public static PrestationDto toDto(Prestation prestation) {
		if (prestation == null) {
			return null;
		}
		AppelOffre appel = null;
		if (prestation.getAppelOffre() != null) {
			appel = prestation.getAppelOffre();
			return PrestationDto.builder().id(prestation.getId())
	                .factures(FactureMapper.toDtos(prestation.getFactures()))
					.naturePrestation(prestation.getNaturePrestation())
					.prixPrestations(prestation.getPrixPrestations()).idAppelOffre(appel.getId()).build();
		}
		return PrestationDto.builder().id(prestation.getId()).naturePrestation(prestation.getNaturePrestation())
				.factures(FactureMapper.toDtos(prestation.getFactures()))
				.prixPrestations(prestation.getPrixPrestations()).build();

	}

	public static List<PrestationDto> toDtos(List<Prestation> Prestations) {
		if (Prestations == null) {
			return null;
		}
		return Prestations.stream().filter(Objects::nonNull).map(PrestationMapper::toDto).collect(Collectors.toList());

	}

	public static List<Prestation> toEntities(List<PrestationDto> PrestationDtos) {
		if (PrestationDtos != null) {
			return PrestationDtos.stream().filter(Objects::nonNull).map(PrestationMapper::toEntity)
					.collect(Collectors.toList());
		} else {
			return null;
		}
	}

	public static Prestation toEntity(PrestationDto prestationDto) {
		if (prestationDto != null) {
			return Prestation.builder().id(prestationDto.getId()).naturePrestation(prestationDto.getNaturePrestation())
					.prixPrestations(prestationDto.getPrixPrestations())
					.appelOffre(AppelOffreMapper.toEntity(prestationDto.getAppelOffre()))
				    .build();
		} else {
			return null;
		}
	}
}
