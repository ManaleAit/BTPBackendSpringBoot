package ma.s.gcm.service;

import ma.s.gcm.domain.Employee;
import ma.s.gcm.dto.EmployeeDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.EmployeeMapper;
import ma.s.gcm.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public EmployeeDto findById(Long id) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by id {}", id);
		return Optional.ofNullable(employeeRepository.findById(id)).map(v -> EmployeeMapper.toDto(v.get())).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Employee not found"));
	}

	public EmployeeDto findByMatricule(String matricule) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by matricule {}", matricule);
		return EmployeeMapper.toDto(employeeRepository.findByMatricule(matricule));
	}

	public EmployeeDto findByEmail(String email) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by email {}", email);
		return EmployeeMapper.toDto(employeeRepository.findByEmail(email));
	}

	public List<EmployeeDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");

		return Optional.ofNullable(employeeRepository.findAll()).map(EmployeeMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Employees not found"));
	}

	public void delete(Long id) throws BureauEtudeException {
		Employee employee = new Employee();
		Optional<Employee> value = employeeRepository.findById(id);
		if (!value.isPresent()) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, " l'employee n'existe pas");
		} else {
		
			employee = value.get();
		}

		if (employee != null) {
			List<Employee> empls = employeeRepository.findByResponsable(employee.getId());
			for (Employee emp : empls) {

				emp.setResponsable(null);
			}
		}

		LOGGER.debug("START SERVICE delete by id {}", id);
		employeeRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}

	public void save(EmployeeDto employeeDto) throws BureauEtudeException {

		LOGGER.debug("START SERVICE save by matricule {}, name: {}", employeeDto.getMatricule(), employeeDto.getNom());
		employeeRepository.save(EmployeeMapper.toEntity(employeeDto));
		LOGGER.debug("START SERVICE save by id {}, name: {}", employeeDto.getMatricule(), employeeDto.getNom());

	}
}
