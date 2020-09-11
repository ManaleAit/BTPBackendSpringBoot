package ma.s.gcm.resource;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.s.gcm.domain.Employee;
import ma.s.gcm.dto.CongeDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.EmployeeMapper;
import ma.s.gcm.service.CongeService;
import ma.s.gcm.service.EmployeeService;
import ma.s.gcm.utils.Utils;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class CongeResourceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CongeService congeService;
	@MockBean
	private EmployeeService empService;

	@Test
	public void getNotFoundTest() throws Exception {
		given(congeService.findById(anyLong()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "conge not found"));

		mockMvc.perform(get("/conge/{Id}", 2).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("conge not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "04-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		given(congeService.findById(anyLong())).willReturn(congeDto);
		mockMvc.perform(get("/conge/{Id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.duree").value(congeDto.getDuree()));

	}

	@Test
	public void SaveExceptionNnJourMaxTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "01-9-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doThrow(new BureauEtudeException(ExceptionCode.API_ERROR_CONGE, "la durée ne doit pas dépasser 30 jour"))
				.when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_ERROR_CONGE"))
				.andExpect(jsonPath("$.message").value("la durée ne doit pas dépasser 30 jour"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));

	}

	@Test
	public void SaveExceptionDateDebutNullTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String endDateString = "11-08-2020";
		congeDto.setDebutPeriode(null);
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doThrow(new BureauEtudeException(ExceptionCode.API_DATE_NULL, "la date debut doit etre non vide"))
				.when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATE_NULL"))
				.andExpect(jsonPath("$.message").value("la date debut doit etre non vide"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void SaveExceptionDateFinNullTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String StartDateString = "11-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(StartDateString));
		congeDto.setFinPeriode(null);
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doThrow(new BureauEtudeException(ExceptionCode.API_DATE_NULL, "la date fin doit etre non vide"))
				.when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATE_NULL"))
				.andExpect(jsonPath("$.message").value("la date fin doit etre non vide"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void SaveExceptionEmployeeNullTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(null);
		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'employee est obligatoire"))
				.when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATA_ERRORS"))
				.andExpect(jsonPath("$.message").value("l'employee est obligatoire"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void SaveExceptionEmployeeIdNullTest() throws Exception {
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(null);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'employee est obligatoire"))
				.when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATA_ERRORS"))
				.andExpect(jsonPath("$.message").value("l'employee est obligatoire"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void SaveTestConge() throws Exception {

		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doNothing().when(congeService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void SaveTestEmployeeNotFound() throws Exception {

		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "03-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		given(empService.findById(employee.getId()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "employee not found"));

		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'employee non trouver")).when(congeService)
				.save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/conge").content(asJsonString(congeDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATA_ERRORS"))
				.andExpect(jsonPath("$.message").value("l'employee non trouver"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void DeleteTest() throws Exception {
        doNothing().when(congeService).delete(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(
				delete("/conge/{Id}", 1).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void DeleteNotFoundTest() throws Exception {
		
		doThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "congé not found"))
				.when(congeService).delete(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(delete("/conge/{Id}", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("congé not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getAllNotFoundTest() throws Exception {
		given(congeService.findAll()).willThrow(
				new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "congé not found"));

		mockMvc.perform(get("/conge").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("congé not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getAllTest() throws Exception {
		List<CongeDto> congeDtos = new Vector<CongeDto>();
		CongeDto congeDto = new CongeDto();
		congeDto.setId(1L);
		Employee employee = new Employee();
		employee.setId(1L);
		String startDateString = "01-08-2020";
		String endDateString = "05-08-2020";
		congeDto.setDebutPeriode(Utils.toDate(startDateString));
		congeDto.setFinPeriode(Utils.toDate(endDateString));
		congeDto.setEmlpoyee(EmployeeMapper.toDto(employee));
		congeDtos.add(congeDto);
		given(congeService.findAll()).willReturn(congeDtos);
		mockMvc.perform(get("/conge")).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1))
				.andExpect(jsonPath("$.[0].duree").value(congeDto.getDuree()));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
