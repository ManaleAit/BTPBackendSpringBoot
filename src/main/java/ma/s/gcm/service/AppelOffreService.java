package ma.s.gcm.service;

import ma.s.gcm.dto.AppelOffreDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.AppelOffreMapper;
import ma.s.gcm.repository.AppelOffreRepository;
import ma.s.gcm.repository.MaitreOuvrageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppelOffreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppelOffreService.class);

	private AppelOffreRepository appelOffreRepository;

	@Autowired
	private MaitreOuvrageRepository maitreOuvrageRepository;

	public AppelOffreService(AppelOffreRepository appelOffreRepository) {
		this.appelOffreRepository = appelOffreRepository;
	}

	public List<AppelOffreDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(appelOffreRepository.findAll()).map(AppelOffreMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "AppelOffres not found"));
	}

	public AppelOffreDto findById(Long id) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by id {}", id);
		return Optional.ofNullable(appelOffreRepository.findById(id)).map(v -> AppelOffreMapper.toDto(v.get()))
				.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
						"Appel d'offre not found"));
	}

	public void delete(Long id) {
		LOGGER.debug("START SERVICE delete by id {}", id);
		appelOffreRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(AppelOffreDto appelOffreDto) {

		if (appelOffreDto.getMaitreOuvrage() == null) {

			throw new BureauEtudeException(ExceptionCode.API_DATE_NULL, " le maitre ouvrage ne doit pas etre null");
		}

		if (maitreOuvrageRepository.findById(appelOffreDto.getMaitreOuvrage().getId()) == null) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, " le maitre ouvrage n'existe pas");
		}

		if (appelOffreDto.getMaitreOuvrage().getId() == null) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
					" l'id de  maitre ouvrage ne doit  pas etre null");

		}

		LOGGER.debug("START SERVICE save by Num d'offre {}, id: {}", appelOffreDto.getNumOffre(),
				appelOffreDto.getId());
		appelOffreRepository.save(AppelOffreMapper.toEntity(appelOffreDto));
		LOGGER.debug("START SERVICE save by Num d'offre {}, id: {}", appelOffreDto.getNumOffre(),
				appelOffreDto.getId());
	}
}
