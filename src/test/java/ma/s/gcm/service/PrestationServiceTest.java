package ma.s.gcm.service;

import static org.junit.Assert.assertEquals;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import ma.s.gcm.domain.AppelOffre;
import ma.s.gcm.domain.Conge;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.PrestationDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.AppelOffreMapper;
import ma.s.gcm.mapper.CongeMapper;
import ma.s.gcm.mapper.EmployeeMapper;
import ma.s.gcm.mapper.PrestationMapper;
import ma.s.gcm.repository.AppelOffreRepository;
import ma.s.gcm.repository.PrestationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PrestationServiceTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private PrestationService prestationService;
	@MockBean
	private PrestationRepository prestationRepository;
	
	@MockBean
	private AppelOffreService appelOffreService;

	@Test(expected = BureauEtudeException.class)
	public void FindAllPrestationExceptionTest() {
		given(prestationRepository.findAll()).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		prestationService.findAll();
	}

	@Test
	public void SavePrestationTest() {
		Prestation prestation = new Prestation();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(2L);
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		given(appelOffreService.findById(appelOffre.getId())).willReturn(AppelOffreMapper.toDto(appelOffre));
		prestation.setAppelOffre(appelOffre);
		given(prestationRepository.save(prestation)).willReturn(prestation);
	}

	@Test(expected = BureauEtudeException.class)
	public void SavePrestationExceptionTest() {
		Prestation prestation = null;
		given(prestationRepository.save(prestation)).willThrow(new BureauEtudeException(ExceptionCode.API_DATA_ERRORS));
		prestationService.save(PrestationMapper.toDto(prestation));
	}

	@Test
	public void FindAllPrestationTest() {
		List<Prestation> prestations = new Vector<Prestation>();
		Prestation prestation = new Prestation();
		AppelOffre appelOffre = new AppelOffre();
		appelOffre.setId(2L);
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		prestation.setAppelOffre(appelOffre);
		prestations.add(prestation);
		given(prestationRepository.findAll()).willReturn(prestations);
		List<PrestationDto> listobj = prestationService.findAll();
		assertEquals(listobj.size(), 1);

	}

	@Test
	public void FindOnePrestationTest() {
		Prestation prestation = new Prestation();
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		Optional<Prestation> optional = Optional.of(prestation);
		given(prestationRepository.findById(1L)).willReturn(optional);
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);
		Assertions.assertThat(prestationRepository.findById(1L).get())
				.isEqualToIgnoringGivenFields(prestationService.findById(prestationDto.getId()));

	}

	@Test(expected = BureauEtudeException.class)
	public void NotFindOnePrestationExceptionTest() {
		Optional<Prestation> optional = null;
		given(prestationRepository.findById(anyLong())).willReturn(optional);
		prestationService.findById(any());

	}

	@Test
	public void DeletePrestationTest() {
		Prestation prestation = new Prestation();
		prestation.setId(1L);
		prestation.setNaturePrestation("xx");
		prestation.setPrixPrestations(100.00);
		Optional<Prestation> optional = Optional.of(prestation);
		given(prestationRepository.findById(1L)).willReturn(optional);
		prestationService.delete(1L);
	}

	@Test(expected = BureauEtudeException.class)
	public void DeletePrestationNotExceptionTest() {
		given(prestationRepository.findById(anyLong())).willReturn(null);
		prestationService.delete(anyLong());

	}

}
