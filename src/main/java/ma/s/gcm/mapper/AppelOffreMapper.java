package ma.s.gcm.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import ma.s.gcm.domain.AppelOffre;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.AppelOffreDto;
import ma.s.gcm.dto.PrestationDto;


public class AppelOffreMapper {
    private AppelOffreMapper() {
    }

    public static AppelOffreDto toDto(AppelOffre appelOffre) {
    	if(appelOffre!=null) {
        return AppelOffreDto.builder()
                .id(appelOffre.getId())
                .numOffre(appelOffre.getNumOffre())
                .objet(appelOffre.getObjet())
                .maitreOuvrage(MaitreOuvrageMapper.toDto(appelOffre.getMaitreOuvrage()))
                .natureCode(appelOffre.getNatureCode())
                .documents(DocumentMapper.toDtos(appelOffre.getDocuments()))
                .organismeCaution(appelOffre.getOrganismeCaution())
                .datePublication(appelOffre.getDatePublication())
                .dateOuverture(appelOffre.getDateOuverture())
                .heureOuverture(appelOffre.getHeureOuverture())
                .statut(appelOffre.getStatut())
                .caution(appelOffre.getCaution())
                .adjudicataire(appelOffre.getAdjudicataire())
                .prestations(PrestationMapper.toDtos((List<Prestation>)appelOffre.getPrestations()))
                .build();
    	  }else {
       	 return null;
        }
    }

    public static List<AppelOffreDto> toDtos(List<AppelOffre> AppelOffres) {
      if(AppelOffres!=null) {
        return AppelOffres.stream().filter(Objects::nonNull)
                .map(AppelOffreMapper::toDto)
                .collect(Collectors.toList());
      }
      else {
    	  
	  	return null;
	  	
	  }
    }

    public static AppelOffre toEntity(AppelOffreDto appelOffre) {
    	if(appelOffre!=null) {
        return AppelOffre.builder()
                .id(appelOffre.getId())
                .numOffre(appelOffre.getNumOffre())
                .objet(appelOffre.getObjet())
                .maitreOuvrage(MaitreOuvrageMapper.toEntity(appelOffre.getMaitreOuvrage()))
                .natureCode(appelOffre.getNatureCode())
                .documents(DocumentMapper.toEntities(appelOffre.getDocuments()))
                .organismeCaution(appelOffre.getOrganismeCaution())
                .datePublication(appelOffre.getDatePublication())
                .dateOuverture(appelOffre.getDateOuverture())
                .heureOuverture(appelOffre.getHeureOuverture())
                .statut(appelOffre.getStatut())
                .adjudicataire(appelOffre.getAdjudicataire())
                .caution(appelOffre.getCaution())
                .prestations(PrestationMapper.toEntities((List<PrestationDto>)appelOffre.getPrestations()))
                .build();
    	}else {
    		return null;
    	}
    }
}

