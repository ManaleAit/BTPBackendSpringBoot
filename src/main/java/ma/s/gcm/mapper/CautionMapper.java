package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import ma.s.gcm.domain.Caution;
import ma.s.gcm.dto.CautionDto;
public class CautionMapper {
	private CautionMapper() {
	}

	public static CautionDto toDto(Caution caution) {
		if (caution == null) {
			return null;
		} else {
			return CautionDto.builder().id(caution.getId())
					//.etablissement(EtablissementFinancierMapper.toDto(caution.getEtablissement()))
					.montantCautionDefinitive(caution.getMontantCautionDefinitive())
					.montantCautionProvisoire(caution.getMontantCautionProvisoire())
					.build();
		}
	}

	 public static List<Caution> toEntities(List<CautionDto> CautionDtos) {
	    	if(CautionDtos!=null) {
	        return CautionDtos.stream().filter(Objects::nonNull)
	                .map(CautionMapper::toEntity)
	                .collect(Collectors.toList());
	    	 }
	    	else {
	    	  		return null;
	    	  	}
	    }

	public static List<CautionDto> toDtos(List<Caution> Cautions) {

		return Cautions.stream().filter(Objects::nonNull).map(CautionMapper::toDto).collect(Collectors.toList());

	}

	public static Caution toEntity(CautionDto cautionDto) {
		if (cautionDto == null) {
			return null;
		}
		return Caution.builder().id(cautionDto.getId())
				.etablissement(EtablissementFinancierMapper.toEntity(cautionDto.getEtablissement()))
				.montantCautionDefinitive(cautionDto.getMontantCautionDefinitive())
				.montantCautionProvisoire(cautionDto.getMontantCautionProvisoire())
				.build();
	}
}