package ma.s.gcm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.dto.IntervenantDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.IntervenantMapper;
import ma.s.gcm.repository.IntervenantRepository;

@Service
@Transactional
public class IntervenantService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IntervenantService.class);

	private IntervenantRepository intervenantRepository;

	public IntervenantService(IntervenantRepository intervenantRepository) {
		this.intervenantRepository = intervenantRepository;
	}

	public IntervenantDto findById(Long id) throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(intervenantRepository.findById(id)).map(v -> IntervenantMapper.toDto(v.get()))
					.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
							"Intervenant not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}

	public List<IntervenantDto> findAll() throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find all");
			return Optional.ofNullable(intervenantRepository.findAll()).map(IntervenantMapper::toDtos).orElseThrow(
					() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Intervenants not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}

	public void delete(Long id) {
		if (!intervenantRepository.findById(id).isPresent()){

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Intervenant not found");
		}
		LOGGER.debug("START SERVICE delete by id {}", id);
		intervenantRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);

	}

	public void save(IntervenantDto IntervenantDto) throws BureauEtudeException {

		try {
			LOGGER.debug("START SERVICE save by id {}:", IntervenantDto.getId());
			intervenantRepository.save(IntervenantMapper.toEntity(IntervenantDto));
			LOGGER.debug("START SERVICE save by id {}:", IntervenantDto.getId());

		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}
}
