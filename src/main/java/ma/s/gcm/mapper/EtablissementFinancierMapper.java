package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.EtablissementFinancier;
import ma.s.gcm.dto.EtablissementFinancierDto;

public class EtablissementFinancierMapper {
	private EtablissementFinancierMapper() {
	}

	public static EtablissementFinancierDto toDto(EtablissementFinancier etablissementFinancier) {
		if (etablissementFinancier == null) {
			return null;
		}
		return EtablissementFinancierDto.builder().id(etablissementFinancier.getId())
				.nom(etablissementFinancier.getNom()).abreviation(etablissementFinancier.getAbreviation())
				.adresse(etablissementFinancier.getAdresse()).telephone(etablissementFinancier.getTelephone())
				.fixe(etablissementFinancier.getFixe()).faxe(etablissementFinancier.getFaxe())
				.cautions(CautionMapper.toDtos(etablissementFinancier.getCautions()))
				.build();
	}

	public static List<EtablissementFinancierDto> toDtos(List<EtablissementFinancier> EtablissementFinanciers) {
		if (EtablissementFinanciers == null) {
			return null;
		}
		return EtablissementFinanciers.stream().filter(Objects::nonNull).map(EtablissementFinancierMapper::toDto)
				.collect(Collectors.toList());

	}

	public static EtablissementFinancier toEntity(EtablissementFinancierDto etablissementFinancierDto) {
		if (etablissementFinancierDto != null) {
			return EtablissementFinancier.builder().id(etablissementFinancierDto.getId())
					.nom(etablissementFinancierDto.getNom()).abreviation(etablissementFinancierDto.getAbreviation())
					.adresse(etablissementFinancierDto.getAdresse()).telephone(etablissementFinancierDto.getTelephone())
					.fixe(etablissementFinancierDto.getFixe()).faxe(etablissementFinancierDto.getFaxe())
					.cautions(CautionMapper.toEntities(etablissementFinancierDto.getCautions()))
					.build();
		} else {
			return null;
		}
	}
}
