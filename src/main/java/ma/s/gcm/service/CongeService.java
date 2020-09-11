package ma.s.gcm.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.domain.Conge;
import ma.s.gcm.domain.Employee;
import ma.s.gcm.dto.CongeDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.CongeMapper;
import ma.s.gcm.repository.CongeRepositoty;
import ma.s.gcm.repository.EmployeeRepository;
import ma.s.gcm.utils.Utils;

@Service
@Transactional
public class CongeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CongeService.class);

	private CongeRepositoty congeRepository;

	@Autowired
	private EmployeeRepository employeeRepositoty;

	private static int NB_JOUR_CONGE_MAX = 30;

	public CongeService(CongeRepositoty congeRepository) {
		this.congeRepository = congeRepository;
	}

	public List<CongeDto> findAll() throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find all");
			return Optional.ofNullable(congeRepository.findAll()).map(CongeMapper::toDtos).orElseThrow(
					() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Conge not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "congés not found");
		}
	}
	
	public List<CongeDto> findDemandeConge() throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find all");
			return Optional.ofNullable(congeRepository.findCongeDemande()).map(CongeMapper::toDtos).orElseThrow(
					() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Conge not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "congés not found");
		}
	}

	public CongeDto findById(Long id) throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(congeRepository.findById(id)).map(v -> CongeMapper.toDto(v.get())).orElseThrow(
					() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Conge not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "congé not found");
		}
	}

	public void delete(Long id) throws BureauEtudeException {
		if (congeRepository.findById(id) == null) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestations not found");
		}

		LOGGER.debug("START SERVICE delete by id {}", id);
		congeRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);

	}

	public void save(CongeDto congeDto) throws BureauEtudeException {

		if (congeDto.getDebutPeriode() == null) {
			throw new BureauEtudeException(ExceptionCode.API_DATE_NULL, "la date debut doit etre non vide");
		}

		if (congeDto.getFinPeriode() == null) {
			throw new BureauEtudeException(ExceptionCode.API_DATE_NULL, "la date fin doit etre non vide");
		}

		if (congeDto.getFinPeriode().before(congeDto.getDebutPeriode())) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "la date fin doit etre apres la date debut");
		}

		long daysBetween = Duration.between(Utils.convertToLocalDate(congeDto.getDebutPeriode()).atStartOfDay(),
				Utils.convertToLocalDate(congeDto.getFinPeriode()).atStartOfDay()).toDays();

		if (daysBetween > NB_JOUR_CONGE_MAX) {

			throw new BureauEtudeException(ExceptionCode.API_ERROR_CONGE, "la durée ne doit pas dépasser 30 jour");
		}

		if (congeDto.getEmlpoyee() == null || congeDto.getEmlpoyee().getId() == null) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'employee est obligatoire");
		}

		Employee employee = employeeRepositoty.getOne(congeDto.getEmlpoyee().getId());

		if (employee == null) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'employee non trouver");
		}

		LOGGER.debug("START SERVICE save byid: {}", congeDto.getId());
		congeDto.setDuree((int) daysBetween);
		Conge conge = CongeMapper.toEntity(congeDto);
		conge.setEmlpoyee(employee);
		congeRepository.save(conge);
		LOGGER.debug("START SERVICE save by  id: {}", congeDto.getId());

	}
}
