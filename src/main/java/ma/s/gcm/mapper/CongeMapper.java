package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.Conge;
import ma.s.gcm.dto.CongeDto;

public class CongeMapper {
	  private CongeMapper() {
	    }

	    public static CongeDto toDto(Conge conge) {
	     if(conge!=null) {
	        return CongeDto.builder()
	        		.id(conge.getId())
	        		.debutPeriode(conge.getDebutPeriode())
	        		.finPeriode(conge.getFinPeriode())
	        		.duree(conge.getDuree())
	        		//.emlpoyee(EmployeeMapper.toDto(conge.getEmlpoyee()))
	        		.matricule(conge.getEmlpoyee().getMatricule())
	        		.nom(conge.getEmlpoyee().getNom())
	        		.prenom(conge.getEmlpoyee().getPrenom())
	        		.etat(conge.getEtat())
	        		.vue(conge.getVue())
	        		.idEmployee(conge.getEmlpoyee().getId())
	                .build();
	     }else {
	    	 return null;
	     }
	    }

	    public static List<CongeDto> toDtos(List<Conge> Conges) {
	    	if(Conges!=null) {
	        return Conges.stream().filter(Objects::nonNull)
	                .map(CongeMapper::toDto)
	                .collect(Collectors.toList());
	    	  }else {
	    	    	 return null;
	    	  }
	   
	    }

	    public static List<Conge> toEntities(List<CongeDto> Conges) {
	    	if(Conges!=null) {
	        return Conges.stream().filter(Objects::nonNull)
	                .map(CongeMapper::toEntity)
	                .collect(Collectors.toList());
	    	 }
	    	else {
	    	  		return null;
	    	  	}
	    }

	    public static Conge toEntity(CongeDto congeDto) {
	      if(congeDto!=null) {
	        return Conge.builder()
	        		.id(congeDto.getId())
	        		.debutPeriode(congeDto.getDebutPeriode())
	        		.finPeriode(congeDto.getFinPeriode())
	        		.duree(congeDto.getDuree())
	        		.etat(congeDto.getEtat())
	        		.vue(congeDto.getVue())
	        		//.emlpoyee(EmployeeMapper.toEntity(congeDto.getEmlpoyee()))
	                .build();
	      }else {
	  		return null;
	  	}
	    }
	}

