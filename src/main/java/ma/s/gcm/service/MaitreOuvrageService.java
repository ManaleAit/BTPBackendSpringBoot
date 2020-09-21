package ma.s.gcm.service;

import ma.s.gcm.domain.AppelOffre;
import ma.s.gcm.domain.MaitreOuvrage;
import ma.s.gcm.dto.MaitreOuvrageDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.MaitreOuvrageMapper;
import ma.s.gcm.repository.MaitreOuvrageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class MaitreOuvrageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppelOffreService.class);

	private MaitreOuvrageRepository maitreOuvrageRepository;

	public MaitreOuvrageService(MaitreOuvrageRepository maitreOuvrageRepository) {
		this.maitreOuvrageRepository = maitreOuvrageRepository;
	}

	public List<MaitreOuvrageDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(maitreOuvrageRepository.findAll()).map(MaitreOuvrageMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Maitres Ouvrages not found"));
	}

	public void delete(Long id) {
		MaitreOuvrage maitreOuvrage = new MaitreOuvrage();
		Optional<MaitreOuvrage> value = maitreOuvrageRepository.findById(id);
		if (!value.isPresent()) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, " le maitre d'ouvrage n'existe pas");
		}
		maitreOuvrage = value.get();

		if (maitreOuvrage != null) {
			List<AppelOffre> listAppel = maitreOuvrage.getAppelOffres();
			if (listAppel != null) {

				for (AppelOffre appel : listAppel) {
					appel.setMaitreOuvrage(null);
				}
			}
		}
		LOGGER.debug("START SERVICE delete by id {}", id);
		maitreOuvrageRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(MaitreOuvrageDto maitreOuvrageDto) {
		LOGGER.debug("START SERVICE save by id {}, name: {}", maitreOuvrageDto.getId(),
				maitreOuvrageDto.getDesignation());
		maitreOuvrageRepository.save(MaitreOuvrageMapper.toEntity(maitreOuvrageDto));
		LOGGER.debug("START SERVICE save by id {}, name: {}", maitreOuvrageDto.getId(),
				maitreOuvrageDto.getDesignation());
	}

	public MaitreOuvrageDto findById(Long id) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by id {}", id);
		return Optional.ofNullable(maitreOuvrageRepository.findById(id)).map(v -> MaitreOuvrageMapper.toDto(v.get()))
				.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
						"maitre D'Ouvrage not found"));
	}
}
