package ma.s.gcm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.List;
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

import ma.s.gcm.domain.Intervenant;
import ma.s.gcm.domain.type.NatureActiviteCode;
import ma.s.gcm.domain.type.TypeIntervenant;
import ma.s.gcm.dto.IntervenantDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.IntervenantMapper;
import ma.s.gcm.repository.IntervenantRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntervenantServiceTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private IntervenantService intervenantService;
	@MockBean
	private IntervenantRepository intervenantRepository;

	@Test(expected = BureauEtudeException.class)
	public void saveIntervenantExceptionTest() {
		Intervenant intervenant = null;
		given(intervenantRepository.save(intervenant))
				.willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		intervenantService.save(IntervenantMapper.toDto(intervenant));
	}

	@Test
	public void saveIntervenantTest() {
		Intervenant intervenant = new Intervenant();
		intervenant.setNatureActivite(NatureActiviteCode.Architecte);
		intervenant.setNom("ait dik");
		intervenant.setPrenom("manal");
		intervenant.setNumCIN("H123");
		intervenant.setTelephone("06259871");
		intervenant.setType(TypeIntervenant.physique);
		intervenant.setEmail("manalait@gmail.com");
		given(intervenantRepository.save(intervenant)).willReturn(intervenant);
		intervenantService.save(IntervenantMapper.toDto(intervenant));
	}

	@Test(expected = BureauEtudeException.class)
	public void FindIntervenantExceptionTest() {

		given(intervenantRepository.findAll()).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		intervenantService.findAll();
	}

	@Test
	public void FindIntervenantTest() {
		List<Intervenant> Intervenants = new Vector<Intervenant>();
		Intervenant intervenant = new Intervenant();
		intervenant.setId(1L);
		intervenant.setNatureActivite(NatureActiviteCode.Architecte);
		intervenant.setNom("ait dik");
		intervenant.setPrenom("manal");
		intervenant.setNumCIN("H123");
		intervenant.setTelephone("06259871");
		intervenant.setType(TypeIntervenant.physique);
		intervenant.setEmail("manalait@gmail.com");
		Intervenants.add(intervenant);

		Intervenant intervenant2 = new Intervenant();
		intervenant2.setId(1L);
		intervenant2.setNatureActivite(NatureActiviteCode.Architecte);
		intervenant2.setNom("ait dik");
		intervenant2.setPrenom("manal");
		intervenant2.setNumCIN("H123");
		intervenant2.setTelephone("06259871");
		intervenant2.setType(TypeIntervenant.physique);
		intervenant2.setEmail("manalait@gmail.com");
		Intervenants.add(intervenant2);
		given(intervenantRepository.findAll()).willReturn(Intervenants);
		List<IntervenantDto> listobj = intervenantService.findAll();
		// assertThat(listobj).containsExactly(IntervenantMapper.toDto(intervenant),IntervenantMapper.toDto(intervenant2));
		assertEquals(listobj.size(), 2);

	}

	@Test
	public void FindIntervenantrieTest() {
		Intervenant intervenant = new Intervenant();
		intervenant.setId(1L);
		intervenant.setNatureActivite(NatureActiviteCode.Architecte);
		intervenant.setNom("ait dik");
		intervenant.setPrenom("manal");
		intervenant.setNumCIN("H123");
		intervenant.setTelephone("06259871");
		intervenant.setType(TypeIntervenant.physique);
		intervenant.setEmail("manalait@gmail.com");
		Optional<Intervenant> optional = Optional.of(intervenant);
		given(intervenantRepository.findById(1L)).willReturn(optional);
		IntervenantDto intervenantDto = IntervenantMapper.toDto(intervenant);
		assertEquals(intervenantService.findById(intervenantDto.getId()).getId(),
				intervenantRepository.findById(1L).get().getId());
		Assertions.assertThat(intervenantRepository.findById(1L).get())
				.isEqualToIgnoringGivenFields(intervenantService.findById(intervenantDto.getId()));
	}

	@Test(expected = BureauEtudeException.class)
	public void NotFindIntervenantTest() {
		Optional<Intervenant> optional = null;
		given(intervenantRepository.findById(anyLong())).willReturn(optional);
		intervenantService.findById(any());

	}

	@Test
	public void DeleteIntervenantTest() {
		Intervenant intervenant = new Intervenant();
		intervenant.setId(1L);
		intervenant.setNatureActivite(NatureActiviteCode.Architecte);
		intervenant.setNom("ait dik");
		intervenant.setPrenom("manal");
		intervenant.setNumCIN("H123");
		intervenant.setTelephone("06259871");
		intervenant.setType(TypeIntervenant.physique);
		intervenant.setEmail("manalait@gmail.com");
		Optional<Intervenant> optional = Optional.of(intervenant);
		given(intervenantRepository.findById(1L)).willReturn(optional);
		intervenantService.delete(1L);
	}

	@Test(expected = BureauEtudeException.class)
	public void DeleteIntervenantExceptionTest() {
		given(intervenantRepository.findById(anyLong())).willReturn(null);
		intervenantService.findById(any());

	}

}
