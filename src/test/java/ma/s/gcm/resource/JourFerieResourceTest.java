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

import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.domain.type.TypeJourFerieCode;
import ma.s.gcm.dto.JourFerieDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.service.JourFerieService;
import ma.s.gcm.utils.Utils;
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class JourFerieResourceTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private JourFerieService jourFerieService;
	

    @Test
    public void getNotFoundTest() throws Exception {
        given(jourFerieService.findById(anyLong())).willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "jour ferie not found"));

        mockMvc.perform(get("/jourFerie/{Id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("jour ferie not found"))
                .andExpect(jsonPath("$.ticketId").isNotEmpty())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.time").isNotEmpty())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.errors", hasSize(0)))
                .andExpect(jsonPath("$.fieldErrors").exists())
                .andExpect(jsonPath("$.fieldErrors", hasSize(0)));
    }
    
    
    @Test
    public void getTest() throws Exception {
		JourFerieDto jourFerieDto = new JourFerieDto();
		jourFerieDto.setId(2L);
		jourFerieDto.setLibelle("fete travail");
		String  date = "10-08-2020";
		try {
			jourFerieDto.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerieDto.setNbJours(2);
		jourFerieDto.setType(TypeJourFerieCode.National);
        given(jourFerieService.findById(anyLong())).willReturn(jourFerieDto);
        mockMvc.perform(get("/jourFerie/{Id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.libelle").value("fete travail"))
                .andExpect(jsonPath("$.dateJour").isNotEmpty())
                .andExpect(jsonPath("$.nbJours").value(2))
                .andExpect(jsonPath("$.type").value("National"));
               
    }
    
    
    @Test
    public void SaveExceptionTest() throws Exception {
    	
    	JourFerieDto jourFerieDto = new JourFerieDto();
		jourFerieDto.setId(2L);
		jourFerieDto.setLibelle("fete travail");
		String  date = "10-08-2020";
		try {
			jourFerieDto.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerieDto.setNbJours(2);
		jourFerieDto.setType(TypeJourFerieCode.National);
		doThrow(new BureauEtudeException(ExceptionCode.API_ERROR_TYPE_JOUR_FERIE, "Vous avez pas le droit d'ajouter un type national")).when(jourFerieService).save(org.mockito.ArgumentMatchers.any());
	
		mockMvc.perform( MockMvcRequestBuilders
    	          .post("/jourFerie")
    	          .content(asJsonString(jourFerieDto))
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isNotFound())
    	          .andExpect(jsonPath("$.code").value("API_ERROR_TYPE_JOUR_FERIE"))
                  .andExpect(jsonPath("$.message").value("Vous avez pas le droit d'ajouter un type national"))
                  .andExpect(jsonPath("$.ticketId").isNotEmpty())
                  .andExpect(jsonPath("$.status").value(404))
                  .andExpect(jsonPath("$.time").isNotEmpty())
                  .andExpect(jsonPath("$.errors").exists())
                  .andExpect(jsonPath("$.errors", hasSize(0)))
                  .andExpect(jsonPath("$.fieldErrors").exists())
                  .andExpect(jsonPath("$.fieldErrors", hasSize(0)));
               
    }
    
    @Test
    public void SaveTest() throws Exception {
    	
    	JourFerieDto jourFerieDto = new JourFerieDto();
		jourFerieDto.setId(2L);
		jourFerieDto.setLibelle("Aid Adha");
		String  date = "10-08-2020";
		try {
			jourFerieDto.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerieDto.setNbJours(2);
		jourFerieDto.setType(TypeJourFerieCode.Religieux);
		doNothing().when(jourFerieService).save(org.mockito.ArgumentMatchers.any());
		
		mockMvc.perform( MockMvcRequestBuilders
    	          .post("/jourFerie")
    	          .content(asJsonString(jourFerieDto))
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isOk());
               
    }
    
    
    @Test
    public void DeleteTest() throws Exception {
    	JourFerie jourFerie = new JourFerie();
		jourFerie.setId(2L);
		jourFerie.setLibelle("Aid Adha");
		String  date = "10-08-2020";
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(2);
		jourFerie.setType(TypeJourFerieCode.Religieux);
    	
		doNothing().when(jourFerieService).delete(anyLong());
		
		   mockMvc.perform(delete("/jourFerie/{Id}", 2)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk()); 
    }
    
    
    @Test
    public void DeleteDateInferieurTest() throws Exception {
    	JourFerie jourFerie = new JourFerie();
		jourFerie.setId(2L);
		jourFerie.setLibelle("Aid Adha");
		String  date = "10-08-1998";
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(2);
		jourFerie.setType(TypeJourFerieCode.Religieux);
    	
		doThrow(new BureauEtudeException(ExceptionCode.API_ERROR_DELETE_JOUR_FERIE, "Vous avez pas le droit de supprimer  ce jour ferie la date est inferieur de la date actuelle")).when(jourFerieService).delete(anyLong());
		  
		mockMvc.perform(delete("/jourFerie/{Id}", 2)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isNotFound())
    	          .andExpect(jsonPath("$.code").value("API_ERROR_DELETE_JOUR_FERIE"))
                  .andExpect(jsonPath("$.message").value("Vous avez pas le droit de supprimer  ce jour ferie la date est inferieur de la date actuelle"))
                  .andExpect(jsonPath("$.ticketId").isNotEmpty())
                  .andExpect(jsonPath("$.status").value(404))
                  .andExpect(jsonPath("$.time").isNotEmpty())
                  .andExpect(jsonPath("$.errors").exists())
                  .andExpect(jsonPath("$.errors", hasSize(0)))
                  .andExpect(jsonPath("$.fieldErrors").exists())
                  .andExpect(jsonPath("$.fieldErrors", hasSize(0)));
    }
    
    
    @Test
    public void DeleteNotFoundJourTest() throws Exception {
    	
		doThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "le jour ferie n'existe pas")).when(jourFerieService).delete(anyLong());
		  
		mockMvc.perform(delete("/jourFerie/{Id}", 2)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isNotFound())
    	          .andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
                  .andExpect(jsonPath("$.message").value("le jour ferie n'existe pas"))
                  .andExpect(jsonPath("$.ticketId").isNotEmpty())
                  .andExpect(jsonPath("$.status").value(404))
                  .andExpect(jsonPath("$.time").isNotEmpty())
                  .andExpect(jsonPath("$.errors").exists())
                  .andExpect(jsonPath("$.errors", hasSize(0)))
                  .andExpect(jsonPath("$.fieldErrors").exists())
                  .andExpect(jsonPath("$.fieldErrors", hasSize(0)));
    }
    @Test
    public void DeleteExceptionNationalTypeTest() throws Exception {
    	JourFerie jourFerie = new JourFerie();
		jourFerie.setId(2L);
		jourFerie.setLibelle("fete travail");
		String  date = "10-08-2022";
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(2);
		jourFerie.setType(TypeJourFerieCode.National);
    	
		doThrow(new BureauEtudeException(ExceptionCode.API_ERROR_DELETE_JOUR_FERIE, "Vous avez pas le droit de supprimer  un  jour ferie de type national")).when(jourFerieService).delete(anyLong());
		  
		mockMvc.perform(delete("/jourFerie/{Id}", 2)
    	          .contentType(MediaType.APPLICATION_JSON)
    	          .accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isNotFound())
    	          .andExpect(jsonPath("$.code").value("API_ERROR_DELETE_JOUR_FERIE"))
                  .andExpect(jsonPath("$.message").value("Vous avez pas le droit de supprimer  un  jour ferie de type national"))
                  .andExpect(jsonPath("$.ticketId").isNotEmpty())
                  .andExpect(jsonPath("$.status").value(404))
                  .andExpect(jsonPath("$.time").isNotEmpty())
                  .andExpect(jsonPath("$.errors").exists())
                  .andExpect(jsonPath("$.errors", hasSize(0)))
                  .andExpect(jsonPath("$.fieldErrors").exists())
                  .andExpect(jsonPath("$.fieldErrors", hasSize(0)));
    }
    
     
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
