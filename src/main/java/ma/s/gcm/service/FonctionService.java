package ma.s.gcm.service;

import ma.s.gcm.dto.FonctionDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.FonctionMapper;
import ma.s.gcm.repository.FonctionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FonctionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FonctionService.class);

	private FonctionRepository fonctionRepository;

	public FonctionService(FonctionRepository fonctionRepository) {
		this.fonctionRepository = fonctionRepository;
	}

	public FonctionDto findById(Long id) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by id {}", id);
		return Optional.ofNullable(fonctionRepository.findById(id)).map(v -> FonctionMapper.toDto(v.get())).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Fonction not found"));
	}

	public List<FonctionDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(fonctionRepository.findAll()).map(FonctionMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "fonctions not found"));
	}

	public void delete(Long id) {
		LOGGER.debug("START SERVICE delete by id {}", id);
		fonctionRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(FonctionDto fonctionDto) throws BureauEtudeException {

		try {
				LOGGER.debug("START SERVICE save by id {}, name: {}", fonctionDto.getId(), fonctionDto.getLibelle());
				fonctionRepository.save(FonctionMapper.toEntity(fonctionDto));
				LOGGER.debug("START SERVICE save by id {}, name: {}", fonctionDto.getId(), fonctionDto.getLibelle());
			

		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}
}
