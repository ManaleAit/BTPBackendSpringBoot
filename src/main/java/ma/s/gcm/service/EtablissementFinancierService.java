package ma.s.gcm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.dto.EtablissementFinancierDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.EtablissementFinancierMapper;
import ma.s.gcm.repository.EtablissementFinancierRepository;

@Service
@Transactional
public class EtablissementFinancierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EtablissementFinancierService.class);

	private EtablissementFinancierRepository etablissementFinancierRepository;

	public EtablissementFinancierService(EtablissementFinancierRepository etablissementFinancierRepository) {
		this.etablissementFinancierRepository = etablissementFinancierRepository;
	}

	public EtablissementFinancierDto findById(Long id) throws BureauEtudeException {

		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(etablissementFinancierRepository.findById(id))
					.map(v -> EtablissementFinancierMapper.toDto(v.get()))
					.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
							"EtablissementFinancier not found"));

		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}

	public List<EtablissementFinancierDto> findAll() throws BureauEtudeException {

		try {
			LOGGER.debug("START SERVICE find all");
			return Optional.ofNullable(etablissementFinancierRepository.findAll())
					.map(EtablissementFinancierMapper::toDtos)
					.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
							"EtablissementFinanciers not found"));

		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}

	public void delete(Long id) {

		if (!etablissementFinancierRepository.findById(id).isPresent()) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "etablissement Financier not found");
		}

		LOGGER.debug("START SERVICE delete by id {}", id);
		etablissementFinancierRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(EtablissementFinancierDto etablissementFinancierDto) throws BureauEtudeException {

		if (etablissementFinancierDto == null) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Vous avez entrer une valeur null");
		}
		LOGGER.debug("START SERVICE save by id {}", etablissementFinancierDto.getId());
		etablissementFinancierRepository.save(EtablissementFinancierMapper.toEntity(etablissementFinancierDto));
		LOGGER.debug("START SERVICE save by id {}", etablissementFinancierDto.getId());

	}
}
