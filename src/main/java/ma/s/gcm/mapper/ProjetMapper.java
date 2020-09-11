package ma.s.gcm.mapper;

import ma.s.gcm.domain.Projet;
import ma.s.gcm.dto.ProjetDto;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ProjetMapper {
	private ProjetMapper() {
	}

	public static ProjetDto toDto(Projet projet) {
		if (projet == null) {
			return null;
		}
		return ProjetDto.builder().id(projet.getId()).numInterneProjet(projet.getNumInterneProjet())
				.IntituleProjet(projet.getIntituleProjet()).statut(projet.getStatut())
				.EtapesProjet(projet.getEtapesProjet()).montant(projet.getMontant()).dateDebut(projet.getDateDebut())
				.dateFin(projet.getDateFin()).intervenantRemunere(projet.getIntervenantRemunere())
				.intervenants(IntervenantMapper.toDtos(projet.getIntervenants()))
				.lotIntervention(projet.getLotIntervention()).delais(projet.getDelais()).quantite(projet.getQuantite())
				.prixUnitaire(projet.getPrixUnitaire()).unite(projet.getUnite())
				.suiviReglement(projet.getSuiviReglement()).documents(DocumentMapper.toDtos(projet.getDocuments()))
				.nombreUnite(projet.getNombreUnite()).build();
	}

	public static List<ProjetDto> toDtos(List<Projet> Projets) {
		if (Projets == null) {
			return null;
		}
		return Projets.stream().filter(Objects::nonNull).map(ProjetMapper::toDto).collect(Collectors.toList());

	}

	public static List<Projet> toEntities(List<ProjetDto> Projets) {
		if (Projets == null) {
			return null;
		}
		return Projets.stream().filter(Objects::nonNull).map(ProjetMapper::toEntity).collect(Collectors.toList());

	}

	public static Projet toEntity(ProjetDto projetDto) {
		if (projetDto != null) {
			return Projet.builder().id(projetDto.getId()).numInterneProjet(projetDto.getNumInterneProjet())
					.IntituleProjet(projetDto.getIntituleProjet()).statut(projetDto.getStatut())
					.EtapesProjet(projetDto.getEtapesProjet()).montant(projetDto.getMontant())
					.dateDebut(projetDto.getDateDebut()).dateFin(projetDto.getDateFin())
					.intervenantRemunere(projetDto.getIntervenantRemunere())
					.documents(DocumentMapper.toEntities(projetDto.getDocuments()))
					.intervenants(IntervenantMapper.toEntities(projetDto.getIntervenants()))
					.lotIntervention(projetDto.getLotIntervention()).delais(projetDto.getDelais())
					.quantite(projetDto.getQuantite()).prixUnitaire(projetDto.getPrixUnitaire())
					.unite(projetDto.getUnite()).suiviReglement(projetDto.getSuiviReglement())
					.nombreUnite(projetDto.getNombreUnite()).build();
		} else {
			return null;
		}
	}
}
