package ma.s.gcm.service;

import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.BureauEtudeMatcher;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.VilleRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VilleServiceTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Autowired
    private VilleService villeService;
    @MockBean
    private VilleRepository villeRepository;

    @Test(expected = BureauEtudeException.class)
    public void findById_ko_villeNotFound() {
      
        given(villeRepository.findAllById(any())).willReturn(null);
        villeService.findById(1L);
    }

//    @Test
//    public void findByUsername_ko_userHasNoClient() {
//        exceptionRule.expect(BureauEtudeException.class);
//        exceptionRule.expect(BureauEtudeMatcher.hasType(ExceptionCode.API_ERROR_INTERNAL));
//
//        User user = new User().setId(1L)
//                .setUsername("hlayachi")
//                .setCin("JD000001")
//                .setEmail("hlayachi@gcm.ma")
//                .setFirstName("Hamza")
//                .setLastName("LAYACHI");
//        given(villeRepository.findByUsername(anyString())).willReturn(user);
//        villeService.findByUsername("hlayachi");
//    }
//
//    @Test
//    public void findByUsername_ok() {
//        Application application = new Application()
//                .setName("Sogetrade")
//                .setCode("SOGETRADE")
//                .setDescription("Sogetrade is an application for trading and international transactions")
//                .setLink("http://sogetrade.gcm.ma");
//        Client client = new Client()
//                .setId(10L)
//                .setCustomerNumber("1200000002")
//                .setApplications(singletonList(application));
//        User user = new User()
//                .setId(1L)
//                .setUsername("hlayachi")
//                .setCin("JD000001")
//                .setEmail("hlayachi@gcm.ma")
//                .setFirstName("Hamza")
//                .setLastName("LAYACHI")
//                .setClient(client);
//        given(villeRepository.findByUsername(anyString())).willReturn(user);
//        UserDto userDto = villeService.findByUsername("hlayachi");
//        Assert.assertNotNull(userDto);
//        assertEquals("hlayachi", userDto.getUsername());
//        assertEquals("JD000001", userDto.getCin());
//        assertEquals("hlayachi@gcm.ma", userDto.getEmail());
//        assertEquals("Hamza", userDto.getFirstName());
//        assertEquals("LAYACHI", userDto.getLastName());
//        ClientDto clientDto = userDto.getClient();
//        assertNotNull(clientDto);
//        assertEquals("1200000002", clientDto.getCustomerNumber());
//        assertTrue(clientDto.getApplications() != null && clientDto.getApplications().size() == 1);
//
//    }

}