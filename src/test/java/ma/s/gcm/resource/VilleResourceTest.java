package ma.s.gcm.resource;

import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.service.VilleService;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WithMockUser
public class VilleResourceTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private VilleService villeService;

    @Test
    public void findByUsername_ko_serviceThrowsException() throws Exception {
        given(villeService.findById(anyLong())).willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "User not found"));

        mockMvc.perform(get("/ville/{Id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("API_RESOURCE_NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("User not found"))
                .andExpect(jsonPath("$.ticketId").isNotEmpty())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.time").isNotEmpty())
                .andExpect(jsonPath("$.errors").exists())
                .andExpect(jsonPath("$.fieldErrors").exists())
                .andExpect(jsonPath("$.fieldErrors", hasSize(0)))
        ;
    }
//
//    @Test
//    public void findByUsername_ok() throws Exception {
//
//        ApplicationDto applicationDto = new ApplicationDto()
//                .setName("gcm")
//                .setCode("gcm")
//                .setDescription("gcm is an application for trading and international transactions")
//                .setLink("http://gcm.gcm.ma");
//        ClientDto client = new ClientDto()
//                .setCustomerNumber("1200000002")
//                .setApplications(singletonList(applicationDto));
//        UserDto userDto = new UserDto()
//                .setUsername("hlayachi")
//                .setCin("JD000001")
//                .setEmail("hlayachi@gcm.ma")
//                .setFirstName("Hamza")
//                .setLastName("LAYACHI")
//                .setClient(client);
//        given(villeService.findByUsername(anyString())).willReturn(userDto);
//
//        mockMvc.perform(get("/users/{username}", "hlayachi")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.username").value("hlayachi"))
//                .andExpect(jsonPath("$.cin").value("JD000001"))
//                .andExpect(jsonPath("$.email").value("hlayachi@gcm.ma"))
//                .andExpect(jsonPath("$.firstName").value("Hamza"))
//                .andExpect(jsonPath("$.lastName").value("LAYACHI"))
//                .andExpect(jsonPath("$.client").exists())
//                .andExpect(jsonPath("$.client.customerNumber").value("1200000002"))
//                .andExpect(jsonPath("$.client.users").doesNotExist())
//                .andExpect(jsonPath("$.client.applications").exists())
//                .andExpect(jsonPath("$.client.applications", hasSize(1)))
//                .andExpect(jsonPath("$.client.applications[*].name").value(containsInAnyOrder("gcm")))
//                .andExpect(jsonPath("$.client.applications[*].code").value(containsInAnyOrder("gcm")))
//                .andExpect(jsonPath("$.client.applications[*].description").value(containsInAnyOrder("gcm is an application for trading and international transactions")))
//                .andExpect(jsonPath("$.client.applications[*].link").value(containsInAnyOrder("http://gcm.gcm.ma")));
//    }
}