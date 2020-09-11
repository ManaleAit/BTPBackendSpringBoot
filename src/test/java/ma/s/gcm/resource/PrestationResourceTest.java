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

import ma.s.gcm.domain.AppelOffre;
import ma.s.gcm.domain.Employee;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.CongeDto;
import ma.s.gcm.dto.PrestationDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.AppelOffreMapper;
import ma.s.gcm.mapper.EmployeeMapper;
import ma.s.gcm.mapper.PrestationMapper;
import ma.s.gcm.service.PrestationService;
import ma.s.gcm.utils.Utils;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class PrestationResourceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PrestationService prestationService;

	@Test
	public void getNotFoundTest() throws Exception {
		given(prestationService.findById(anyLong()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestation not found"));

		mockMvc.perform(get("/prestation/{Id}", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Prestation not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getTest() throws Exception {
		Prestation prestation = new Prestation();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(2L);
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		prestation.setAppelOffre(appelOffre);
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);

		given(prestationService.findById(anyLong())).willReturn(prestationDto);
		mockMvc.perform(get("/prestation/{Id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.naturePrestation").value("xx"))
				.andExpect(jsonPath("$.prixPrestations").value(100.00));
	}

	@Test
	public void SaveTest() throws Exception {
		Prestation prestation = new Prestation();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(2L);
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		prestation.setAppelOffre(appelOffre);
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);
		doNothing().when(prestationService).save(prestationDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/prestation").content(asJsonString(prestationDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void SaveExceptionPrestationNullTest() throws Exception {
		PrestationDto prestationDto = null;

		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Vous avez entrer une valeur null"))
				.when(prestationService).save(prestationDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/prestation").content(asJsonString(prestationDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void SaveExceptionAppelOffreNullTest() throws Exception {
		Prestation prestation = new Prestation();
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		prestation.setAppelOffre(null);
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);
		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Appel offre ne doit pas etre null"))
				.when(prestationService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/prestation").content(asJsonString(prestationDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATA_ERRORS"))
				.andExpect(jsonPath("$.message").value("Appel offre ne doit pas etre null"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void SaveExceptionIdAppelOffreNullTest() throws Exception {
		PrestationDto prestationDto = new PrestationDto();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(null);
		prestationDto.setId(1L);
		prestationDto.setNaturePrestation("xx");
		prestationDto.setPrixPrestations(100.00);
		prestationDto.setAppelOffre(AppelOffreMapper.toDto(appelOffre));
		doThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Id d'ppel offre ne doit pas etre null"))
				.when(prestationService).save(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(MockMvcRequestBuilders.post("/prestation").content(asJsonString(prestationDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.code").value("API_DATA_ERRORS"))
				.andExpect(jsonPath("$.message").value("Id d'ppel offre ne doit pas etre null"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void DeleteTest() throws Exception {
		doNothing().when(prestationService).delete(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(delete("/prestation/{Id}", 1).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void DeleteNotFoundTest() throws Exception {

		doThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestations not found"))
				.when(prestationService).delete(org.mockito.ArgumentMatchers.any());
		mockMvc.perform(delete("/prestation/{Id}", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Prestations not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getAllNotFoundTest() throws Exception {
		given(prestationService.findAll())
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestations not found"));

		mockMvc.perform(get("/prestation").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Prestations not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getAllTest() throws Exception {
		List<PrestationDto> PrestationDtos = new Vector<PrestationDto>();
		Prestation prestation = new Prestation();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(2L);
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		prestation.setAppelOffre(appelOffre);
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);
		PrestationDtos.add(prestationDto);
		given(prestationService.findAll()).willReturn(PrestationDtos);
		mockMvc.perform(get("/prestation")).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1))
				.andExpect(jsonPath("$.[0].naturePrestation").value("xx"))
				.andExpect(jsonPath("$.[0].prixPrestations").value(100.00));

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
