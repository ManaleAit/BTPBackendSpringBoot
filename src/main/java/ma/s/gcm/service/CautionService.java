package ma.s.gcm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.dto.CautionDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.CautionMapper;
import ma.s.gcm.repository.CautionRepository;
import ma.s.gcm.repository.EtablissementFinancierRepository;

@Service
@Transactional
public class CautionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CautionService.class);

	private CautionRepository cautionRepository;
	private EtablissementFinancierRepository etablissementFinancierRepository;

	@Autowired
	public CautionService(CautionRepository cautionRepository,
			EtablissementFinancierRepository etablissementFinancierRepository) {
		this.cautionRepository = cautionRepository;
		this.etablissementFinancierRepository = etablissementFinancierRepository;
	}

	public CautionDto findById(Long id) throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(cautionRepository.findById(id)).map(v -> CautionMapper.toDto(v.get()))
					.orElseThrow(
							() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Caution not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Caution not found");
		}
	}

	public List<CautionDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(cautionRepository.findAll()).map(CautionMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Cautions not found"));
	}

	public void delete(Long id) {
		LOGGER.debug("START SERVICE delete by id {}", id);
		cautionRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(CautionDto cautionDto) throws BureauEtudeException {

		LOGGER.debug("START SERVICE save by id {}", cautionDto.getId());
		cautionRepository.save(CautionMapper.toEntity(cautionDto));
		LOGGER.debug("START SERVICE save by id {}", cautionDto.getId());

	}
}
