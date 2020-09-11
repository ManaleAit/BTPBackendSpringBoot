package ma.s.gcm.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ma.s.gcm.dto.EmployeeDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.EmployeeRepository;
import ma.s.gcm.service.EmployeeService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Employee")
@PreAuthorize("isAuthenticated()")
public class EmployeeResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(VilleResource.class);

	private final EmployeeService employeeService;

	
	private final EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeResource(EmployeeService employeeService,EmployeeRepository  employeeRepository) {
		this.employeeService = employeeService;
		this.employeeRepository=employeeRepository;
	}

	@PostMapping
	@JsonView(UserView.Basic.class)
	public void add(@RequestBody EmployeeDto employeeDto) throws BureauEtudeException {

		if (employeeRepository.findByMatricule(employeeDto.getMatricule())== null) {
			LOGGER.debug("START RESOURCE add Employee by name : {}", employeeDto.getNom());
			employeeService.save(employeeDto);
			LOGGER.debug("END RESOURCE add city by id : {}, name: {} is ok", employeeDto.getMatricule(),
					employeeDto.getNom());
		} else {
			throw new BureauEtudeException(ExceptionCode.API_UNIQUE,
					" matricule est déja existe doit etre unique!!!!!! ");
		}
	}

	@PutMapping
	@JsonView(UserView.Basic.class)
	public void update(@RequestBody EmployeeDto employeeDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE update Employee  by name : {}, matricule: {}", employeeDto.getNom(),
				employeeDto.getMatricule());
		employeeService.save(employeeDto);
		LOGGER.debug("END RESOURCE update Employeeby matricule : {}, name: {} is ok", employeeDto.getMatricule(),
				employeeDto.getNom());
	}

	@GetMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public EmployeeDto get(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find Employee  by id : {}", id);
		EmployeeDto employeeDto = employeeService.findById(id);
		LOGGER.debug("END RESOURCE find Employee  by id : {}, name :{}", id, employeeDto.getNom());
		return employeeDto;
	}
	
	
	@GetMapping("/matricule/{matricule}")
	@JsonView(UserView.Basic.class)
	public EmployeeDto getMatricule(@PathVariable String matricule) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find Employee  by id : {}", matricule);
		EmployeeDto employeeDto = employeeService.findByMatricule(matricule);
		LOGGER.debug("END RESOURCE find Employee  by matricule :{}",employeeDto.getMatricule());
		return employeeDto;
	}
	
	
	@GetMapping("/Email/{email}")
	@JsonView(UserView.Basic.class)
	public EmployeeDto get(@PathVariable String email) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find Employee  by email : {}", email);
		EmployeeDto employeeDto = employeeService.findByEmail(email);
		LOGGER.debug("END RESOURCE find Employee  by email : {}", email, employeeDto.getEmail());
		return employeeDto;
	}

	@GetMapping
	@JsonView(UserView.Basic.class)
	public List<EmployeeDto> getAll() throws BureauEtudeException {
		LOGGER.debug("START RESOURCE all find Employees");
		List<EmployeeDto> employeeDtos = employeeService.findAll();
		LOGGER.debug("END RESOURCE find all Employees, size: {}", employeeDtos.size());
		return employeeDtos;
	}

	@DeleteMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public void delete(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE delete Employee by id : {}", id);
		employeeService.delete(id);
		LOGGER.debug("END RESOURCE delete Employee by id : {}, is ok", id);
	}
}
