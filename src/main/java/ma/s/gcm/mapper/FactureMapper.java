package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import ma.s.gcm.domain.Facture;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.FactureDto;
import ma.s.gcm.dto.PrestationDto;

public class FactureMapper {
	 private FactureMapper() {
	    }

	    public static FactureDto toDto(Facture facture) {
	    	if(facture==null) {
	    		return null;
	    	}
	        return FactureDto.builder()
	                .id(facture.getId())
	                .montantGlobale(facture.getMontantGlobale())
	                .maitreOuvrage(MaitreOuvrageMapper.toDto(facture.getMaitreOuvrage()))
	                .prestations(PrestationMapper.toDtos((List<Prestation>)facture.getPrestations()))

	                .build();
	    }


		
	    public static List<FactureDto> toDtos(List<Facture> Factures) {
	    	if(Factures==null) {
	    		return null;
	    	}
	        return Factures.stream().filter(Objects::nonNull)
	                .map(FactureMapper::toDto)
	                .collect(Collectors.toList());
	    	
		  	}
	   

	    public static Facture toEntity(FactureDto factureDto) {
	    	if(factureDto!=null) {
	        return Facture.builder()
	        	     .id(factureDto.getId())
		             .montantGlobale(factureDto.getMontantGlobale())
		             .maitreOuvrage(MaitreOuvrageMapper.toEntity(factureDto.getMaitreOuvrage()))
		             .prestations(PrestationMapper.toEntities((List<PrestationDto>)factureDto.getPrestations()))

	                .build();
	    	}else {
	    	
	    	return null;
	    	
	    	}
	    }
	}


