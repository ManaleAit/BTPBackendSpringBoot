package ma.s.gcm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Vector;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ma.s.gcm.domain.Conge;
import ma.s.gcm.domain.Employee;
import ma.s.gcm.domain.Intervenant;
import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.dto.CongeDto;
import ma.s.gcm.dto.EmployeeDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.CongeMapper;
import ma.s.gcm.mapper.EmployeeMapper;
import ma.s.gcm.repository.CongeRepositoty;
import ma.s.gcm.repository.EmployeeRepository;
import ma.s.gcm.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CongeServiceTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private CongeService congeService;
	@MockBean
	private CongeRepositoty congeRepository;
	@MockBean
	private EmployeeService empService;

	@MockBean
	private EmployeeRepository empRepo;

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionDureeTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "27-09-2020";
		conge.setDebutPeriode(Utils.toDate(startDateString));
		conge.setFinPeriode(Utils.toDate(endDateString));
		conge.setEmlpoyee(employee);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_ERROR_CONGE));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionDateDebutNullTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String endDateString = "27-09-2020";
		conge.setDebutPeriode(null);
		conge.setFinPeriode(Utils.toDate(endDateString));
		conge.setEmlpoyee(employee);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_DATE_NULL));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionDateFinNullTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String StartDateString = "27-09-2020";
		conge.setDebutPeriode(Utils.toDate(StartDateString));
		conge.setFinPeriode(null);
		conge.setEmlpoyee(employee);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_DATE_NULL));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionEmlpyeeNullTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		conge.setDebutPeriode(Utils.toDate(startDateString));
		conge.setFinPeriode(Utils.toDate(endDateString));
		conge.setEmlpoyee(null);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionIdNullTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		Employee employee = new Employee();
		employee.setId(null);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		conge.setDebutPeriode(Utils.toDate(startDateString));
		conge.setFinPeriode(Utils.toDate(endDateString));
		conge.setEmlpoyee(employee);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void saveCongeExceptionEmlpyeeNotFoundTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		conge.setDebutPeriode(Utils.toDate(startDateString));
		conge.setFinPeriode(Utils.toDate(endDateString));
		given(empRepo.findById(employee.getId()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "employee not found"));

		conge.setEmlpoyee(employee);
		given(congeRepository.save(conge)).willThrow(new BureauEtudeException(ExceptionCode.API_DATE_NULL));
		congeService.save(CongeMapper.toDto(conge));
	}

	@Test
	public void saveCongeTest() throws ParseException {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "2020-07-01";
		String endDateString = "2020-07-05";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		given(empService.findById(employee.getId())).willReturn(EmployeeMapper.toDto(employee));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		Conge conge = CongeMapper.toEntity(congeDto);
		given(congeRepository.save(CongeMapper.toEntity(congeDto))).willReturn(conge);
		// congeService.save(CongeMapper.toDto(conge));
	}

	@Test(expected = BureauEtudeException.class)
	public void FindCongeExceptionTest() {
		given(congeRepository.findAll()).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		congeService.findAll();
	}

	@Test
	public void FindCongeTest() throws ParseException {
		List<Conge> conges = new Vector<Conge>();
		Conge conge = new Conge();
		String dateDebut = "2020-01-01";
		String dateFin = "2020-02-20";
		conge.setDebutPeriode(Utils.toDate(dateDebut));
		conge.setFinPeriode(Utils.toDate(dateFin));
		conges.add(conge);
		given(congeRepository.findAll()).willReturn(conges);
		List<CongeDto> listobj = congeService.findAll();
		assertEquals(listobj.size(), 1);

	}

	@Test
	public void FindOneCongeTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		String dateDebut = "2020-01-01";
		String dateFin = "2020-02-20";
		conge.setDebutPeriode(Utils.toDate(dateDebut));
		conge.setFinPeriode(Utils.toDate(dateFin));
		Optional<Conge> optional = Optional.of(conge);
		given(congeRepository.findById(1L)).willReturn(optional);
		CongeDto congeDto = CongeMapper.toDto(conge);
		assertEquals(congeService.findById(congeDto.getId()).getId(), congeRepository.findById(1L).get().getId());
		Assertions.assertThat(congeRepository.findById(1L).get())
				.isEqualToIgnoringGivenFields(congeService.findById(congeDto.getId()));
	}

	@Test(expected = BureauEtudeException.class)
	public void NotFindOneCongeTest() {
		Optional<Conge> optional = null;
		given(congeRepository.findById(anyLong())).willReturn(optional);
		congeService.findById(any());

	}

	@Test
	public void DeleteCongeTest() throws ParseException {
		Conge conge = new Conge();
		conge.setId(1L);
		String dateDebut = "2020-01-01";
		String dateFin = "2020-02-20";
		conge.setDebutPeriode(Utils.toDate(dateDebut));
		conge.setFinPeriode(Utils.toDate(dateFin));
		doNothing().when(congeRepository).deleteById(anyLong());
		congeService.delete(1L);
	}

	@Test(expected = BureauEtudeException.class)
	public void DeleteCongNotFoundeExceptionTest() {
		given(congeRepository.findById(anyLong())).willReturn(null);
		congeService.delete(anyLong());
	}

}
