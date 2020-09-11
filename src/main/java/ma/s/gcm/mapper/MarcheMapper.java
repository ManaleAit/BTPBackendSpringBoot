package ma.s.gcm.mapper;

import ma.s.gcm.domain.Marche;
import ma.s.gcm.dto.MarcheDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MarcheMapper {
	private MarcheMapper() {
	}

	public static MarcheDto toDto(Marche marche) {
		if(marche==null) {
    		return null;
    	}
		return MarcheDto.builder().id(marche.getId()).numMarche(marche.getNumMarche()).objet(marche.getObjet())
				.maitreOuvrage(MaitreOuvrageMapper.toDto(marche.getMaitreOuvrage()))
				.ordreServiceCommencer(marche.getOrdreServiceCommencer()).delai(marche.getDelai())
				.dateFinInitial(marche.getDateFinInitial()).dateFinPrevue(marche.getDateFinPrevue())
				.delaiRestant(marche.getDelaiRestant()).documents(DocumentMapper.toDtos(marche.getDocuments()))
				.montantCaution(marche.getMontantCaution()).mountantGlobal(marche.getMountantGlobal())
				.lieuExecution(VilleMapper.toDto(marche.getLieuExecution()))
				.ordreServiceArret(marche.getOrdreServiceArret()).ordreServiceReprise(marche.getOrdreServiceReprise())
				.dateFinReelle(marche.getDateFinReelle()).marcheEtatCode(marche.getMarcheEtatCode()).build();
	}

	public static List<MarcheDto> toDtos(List<Marche> Marches) {
		if(Marches==null) {
    		return null;
    	}
			return Marches.stream().filter(Objects::nonNull).map(MarcheMapper::toDto).collect(Collectors.toList());
		
	}

	public static Marche toEntity(MarcheDto marcheDto) {
		if (marcheDto != null) {
			return Marche.builder().id(marcheDto.getId()).numMarche(marcheDto.getNumMarche())
					.objet(marcheDto.getObjet())
					.maitreOuvrage(MaitreOuvrageMapper.toEntity(marcheDto.getMaitreOuvrage()))
					.ordreServiceCommencer(marcheDto.getOrdreServiceCommencer()).delai(marcheDto.getDelai())
					.dateFinInitial(marcheDto.getDateFinInitial()).dateFinPrevue(marcheDto.getDateFinPrevue())
					.delaiRestant(marcheDto.getDelaiRestant())
					.documents(DocumentMapper.toEntities(marcheDto.getDocuments()))
					.montantCaution(marcheDto.getMontantCaution()).mountantGlobal(marcheDto.getMountantGlobal())
					.lieuExecution(VilleMapper.toEntity(marcheDto.getLieuExecution()))
					.ordreServiceArret(marcheDto.getOrdreServiceArret())
					.ordreServiceReprise(marcheDto.getOrdreServiceReprise()).dateFinReelle(marcheDto.getDateFinReelle())
					.marcheEtatCode(marcheDto.getMarcheEtatCode()).build();
		} else {
			return null;
		}
	}
}