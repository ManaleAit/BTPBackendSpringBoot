package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.dto.JourFerieDto;

public class JourFerieMapper {
	private JourFerieMapper() {
    }

    public static JourFerieDto toDto(JourFerie jourFerie) {
        return JourFerieDto.builder()
                .id(jourFerie.getId())
                .libelle(jourFerie.getLibelle())
                .dateJour(jourFerie.getDateJour())
                .type(jourFerie.getType())
                .nbJours(jourFerie.getNbJours())
                .build();
    }

    public static List<JourFerieDto> toDtos(List<JourFerie> jourFeries) {
    	
        return jourFeries.stream().filter(Objects::nonNull)
                .map(JourFerieMapper::toDto)
                .collect(Collectors.toList());
    }

    public static JourFerie toEntity(JourFerieDto jourFerieDto) {
        return JourFerie.builder()
        		.id(jourFerieDto.getId())
                .libelle(jourFerieDto.getLibelle())
                .dateJour(jourFerieDto.getDateJour())
                .type(jourFerieDto.getType())
                .nbJours(jourFerieDto.getNbJours())
                .build();
    }
}
