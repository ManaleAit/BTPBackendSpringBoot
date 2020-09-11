package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import ma.s.gcm.domain.Intervenant;
import ma.s.gcm.dto.IntervenantDto;


public class IntervenantMapper {
	 private IntervenantMapper() {
	    }

	    public static IntervenantDto toDto(Intervenant intervenant) {
	    	if(intervenant==null) {
	    		
	    		return null;
	    	}
	        return IntervenantDto.builder()
	                .id(intervenant.getId()) 
	                .natureActivite(intervenant.getNatureActivite())
	                .type(intervenant.getType())
	                .nom(intervenant.getNom())
	                .prenom(intervenant.getPrenom())
	                .numCIN(intervenant.getNumCIN())
	                .telephone(intervenant.getTelephone())
	                .email(intervenant.getEmail())
	                .projets(ProjetMapper.toDtos(intervenant.getProjets()))
	                .build();
	    }
	  

	    public static List<IntervenantDto> toDtos(List<Intervenant> Intervenants) {
	    	if(Intervenants==null) {
	    		return null;
	    	}
	        return Intervenants.stream().filter(Objects::nonNull)
	                .map(IntervenantMapper::toDto)
	                .collect(Collectors.toList());
	    	
		  	}

	    public static List<Intervenant> toEntities(List<IntervenantDto> Intervenants) {
	    	if(Intervenants==null) {
	    		return null;
	    	}
	        return Intervenants.stream().filter(Objects::nonNull)
	                .map(IntervenantMapper::toEntity)
	                .collect(Collectors.toList());
	    	
		  	}
	   

	    public static Intervenant toEntity(IntervenantDto intervenantDto) {
	    	if(intervenantDto!=null) {
	        return Intervenant.builder()
	        		.id(intervenantDto.getId()) 
		            .natureActivite(intervenantDto.getNatureActivite())
		            .type(intervenantDto.getType())
		            .nom(intervenantDto.getNom())
		            .prenom(intervenantDto.getPrenom())
		            .numCIN(intervenantDto.getNumCIN())
		            .telephone(intervenantDto.getTelephone())
		            .email(intervenantDto.getEmail())
		            //.projets(ProjetMapper.toEntities(intervenantDto.getProjets()))
	                .build();
	    	}else {
	    	
	    	return null;
	    	
	    	}
	    }
	}