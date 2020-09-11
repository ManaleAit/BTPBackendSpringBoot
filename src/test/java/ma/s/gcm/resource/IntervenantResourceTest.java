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

import java.text.ParseException;
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

import ma.s.gcm.domain.type.NatureActiviteCode;
import ma.s.gcm.domain.type.TypeIntervenant;
import ma.s.gcm.dto.IntervenantDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.service.IntervenantService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class IntervenantResourceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IntervenantService intervenantService;
	
	@Test
	public void getNotFoundTest() throws Exception {
		given(intervenantService.findById(anyLong()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Intervenant  not found"));

		mockMvc.perform(get("/intervenant/{Id}", 2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Intervenant  not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}

	@Test
	public void getTest() throws Exception {
		IntervenantDto intervenantDto= new IntervenantDto();
		intervenantDto.setId(1L);
		intervenantDto.setNatureActivite(NatureActiviteCode.Architecte);
		intervenantDto.setNom("ait dik");
		intervenantDto.setPrenom("manal");
		intervenantDto.setNumCIN("H123");
		intervenantDto.setTelephone("06259871");
		intervenantDto.setType(TypeIntervenant.physique);
		intervenantDto.setEmail("manalait@gmail.com");
		
		given(intervenantService.findById(anyLong())).willReturn(intervenantDto);
		mockMvc.perform(get("/intervenant/{Id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.natureActivite").value("Architecte"))
				.andExpect(jsonPath("$.nom").value("ait dik")).andExpect(jsonPath("$.prenom").value("manal"))
				.andExpect(jsonPath("$.numCIN").value("H123")).andExpect(jsonPath("$.telephone").value("06259871"))
				.andExpect(jsonPath("$.type").value("physique")).andExpect(jsonPath("$.email").value("manalait@gmail.com"));

	}
	@Test
	public void SaveExceptionTest() throws Exception {
		IntervenantDto intervenantDto= new IntervenantDto();
		intervenantDto.setId(1L);
		intervenantDto.setNatureActivite(NatureActiviteCode.Architecte);
		intervenantDto.setNom("ait dik");
		intervenantDto.setPrenom("manal");
		intervenantDto.setNumCIN("H123");
		intervenantDto.setTelephone("06259871");
		intervenantDto.setType(TypeIntervenant.physique);
		intervenantDto.setEmail("manalait@gmail.com");
		doThrow(new BureauEtudeException(ExceptionCode.API_UNIQUE,
				"Le CIN est déja existe doit etre unique!!!!!!")).when(intervenantService).save(org.mockito.ArgumentMatchers.any());

		mockMvc.perform(MockMvcRequestBuilders.post("/intervenant").content(asJsonString(intervenantDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_UNIQUE"))
				.andExpect(jsonPath("$.message").value("Le CIN est déja existe doit etre unique!!!!!!"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));

	}
	
	@Test
	public void SaveTest() throws Exception {

		IntervenantDto intervenantDto= new IntervenantDto();
		intervenantDto.setId(1L);
		intervenantDto.setNatureActivite(NatureActiviteCode.Architecte);
		intervenantDto.setNom("ait dik");
		intervenantDto.setPrenom("manal");
		intervenantDto.setNumCIN("H123");
		intervenantDto.setTelephone("06259871");
		intervenantDto.setType(TypeIntervenant.physique);
		intervenantDto.setEmail("manalait@gmail.com");
		doNothing().when(intervenantService).save(org.mockito.ArgumentMatchers.any());

		mockMvc.perform(MockMvcRequestBuilders.post("/intervenant").content(asJsonString(intervenantDto))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	public void DeleteTest() throws Exception {

		doNothing().when(intervenantService).delete(org.mockito.ArgumentMatchers.any());

		mockMvc.perform(get("/intervenant/{Id}", 1)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	
	@Test
	public void DeleteNotFoundTest() throws Exception {
		doThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Intervenant ferie not found")).when(intervenantService).delete(anyLong());

		mockMvc.perform(delete("/intervenant/{Id}",2).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Intervenant ferie not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}
	
	@Test
	public void getAllNotFoundTest() throws Exception {
		given(intervenantService.findAll())
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Intervenant ferie not found"));

		mockMvc.perform(get("/intervenant").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
				.andExpect(jsonPath("$.message").value("Intervenant ferie not found"))
				.andExpect(jsonPath("$.ticketId").isNotEmpty()).andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.time").isNotEmpty()).andExpect(jsonPath("$.errors").exists())
				.andExpect(jsonPath("$.errors", hasSize(0))).andExpect(jsonPath("$.fieldErrors").exists())
				.andExpect(jsonPath("$.fieldErrors", hasSize(0)));
	}
	@Test
	public void getAllTest() throws Exception {
		List<IntervenantDto> intervenantDtos=new Vector<IntervenantDto>();
		IntervenantDto intervenantDto= new IntervenantDto();
		intervenantDto.setId(1L);
		intervenantDto.setNatureActivite(NatureActiviteCode.Architecte);
		intervenantDto.setNom("ait dik");
		intervenantDto.setPrenom("manal");
		intervenantDto.setNumCIN("H123");
		intervenantDto.setTelephone("06259871");
		intervenantDto.setType(TypeIntervenant.physique);
		intervenantDto.setEmail("manalait@gmail.com");
		intervenantDtos.add(intervenantDto);
		given(intervenantService.findAll()).willReturn(intervenantDtos);
		
		mockMvc.perform(get("/intervenant").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.[0].id").value(1)).andExpect(jsonPath("$.[0].natureActivite").value("Architecte"))
		.andExpect(jsonPath("$.[0].nom").value("ait dik")).andExpect(jsonPath("$.[0].prenom").value("manal"))
		.andExpect(jsonPath("$.[0].numCIN").value("H123")).andExpect(jsonPath("$.[0].telephone").value("06259871"))
		.andExpect(jsonPath("$.[0].type").value("physique")).andExpect(jsonPath("$.[0].email").value("manalait@gmail.com"));


	}

	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	

}
