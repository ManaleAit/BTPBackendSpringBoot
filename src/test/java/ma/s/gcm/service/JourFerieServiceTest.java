package ma.s.gcm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.sun.istack.logging.Logger;

import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.domain.type.TypeJourFerieCode;
import ma.s.gcm.dto.JourFerieDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.JourFerieMapper;
import ma.s.gcm.repository.JourFerieRepository;
import ma.s.gcm.utils.Utils;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "app.scheduling.enable=true")
public class JourFerieServiceTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private JourFerieService jourFerieService;
	@MockBean
	private JourFerieRepository jourFerieRepository;

	private static final String SCHEDULER = "0/5 * * * * *";

	private static final String DATE_CURREN = "2020-07-22";
	protected Logger LOG = Logger.getLogger(getClass());

	@Test
	public void saveReligieuxJourFerieTest() {
		JourFerieDto jourFerieDto = new JourFerieDto();
		jourFerieDto.setLibelle("Aid Adha");
		String date = "01-08-2020";
		try {
			jourFerieDto.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerieDto.setNbJours(2);
		jourFerieDto.setType(TypeJourFerieCode.Religieux);
		jourFerieService.save(jourFerieDto);

	}

	@Test(expected = BureauEtudeException.class)
	public void saveNationalJourFerieTest() {
		JourFerie jourFerie = new JourFerie();
		jourFerie.setLibelle("Premier janvier");

		String date = "01-01-2020";
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(1);
		jourFerie.setType(TypeJourFerieCode.National);
		given(jourFerieRepository.save(jourFerie)).willThrow(new BureauEtudeException(
				ExceptionCode.API_ERROR_TYPE_JOUR_FERIE, " Vous avez pas le droit d'ajouter un type national"));
		jourFerieService.save(JourFerieMapper.toDto(jourFerie));

	}

	@Test(expected = NoSuchElementException.class)
	public void NotFindJourFerieTest() {
		given(jourFerieRepository.findById(anyLong()))
				.willThrow(new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "jour ferie n'existe pas"));
		jourFerieService.findById(any());

	}

	@Test
	public void FindJourFerieTest() {

		JourFerie jourFerie = new JourFerie();
		jourFerie.setLibelle("Premier janvier");
		jourFerie.setId(1L);
		jourFerie.setNbJours(1);
		jourFerie.setType(TypeJourFerieCode.National);
		Optional<JourFerie> optional = Optional.of(jourFerie);
		given(jourFerieRepository.findById(1L)).willReturn(optional);
		JourFerieDto jourFerieDto = JourFerieMapper.toDto(jourFerie);
		assertEquals(jourFerieService.findById(jourFerieDto.getId()).getId(), jourFerieService.findById(1L).getId());
		assertEquals(jourFerieService.findById(jourFerieDto.getId()).getLibelle(),
				jourFerieService.findById(1L).getLibelle());
		Assertions.assertThat(jourFerieRepository.findById(1L).get())
				.isEqualToIgnoringGivenFields(jourFerieService.findById(jourFerieDto.getId()));
	}

	@Test(expected = BureauEtudeException.class)
	public void DeleteJourFerieNotFoundExceptionTest() {
		given(jourFerieRepository.findById(anyLong())).willReturn(null);
		jourFerieService.delete(anyLong());

	}

	@Test(expected = BureauEtudeException.class)
	public void DeleteExceptionJourFerieTest() {

		String date = "2019-01-01";
		JourFerie jourFerie = new JourFerie();
		jourFerie.setId(1L);
		jourFerie.setLibelle("Premier janvier");
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(1);
		jourFerie.setType(TypeJourFerieCode.National);
		Optional<JourFerie> optional = Optional.of(jourFerie);
		given(jourFerieRepository.findById(1L)).willReturn(optional);
		jourFerieService.delete(jourFerie.getId());
	}

	@Test
	public void DeleteJourFerieTest() {

		String date = "2025-01-01";

		JourFerie jourFerie = new JourFerie();
		jourFerie.setId(1L);
		jourFerie.setLibelle("Aid adha");
		try {
			jourFerie.setDateJour(Utils.toDate(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		jourFerie.setNbJours(1);
		jourFerie.setType(TypeJourFerieCode.Religieux);
		Optional<JourFerie> optional = Optional.of(jourFerie);
		given(jourFerieRepository.findById(1L)).willReturn(optional);
		jourFerieService.delete(jourFerie.getId());

	}

	@Test
	public void MajRefTableTest() {

		cronSchedulerGenerator(SCHEDULER, 200);

	}

	public void cronSchedulerGenerator(String paramScheduler, int index) {
		CronSequenceGenerator cronGen = new CronSequenceGenerator(paramScheduler);
		java.util.Date date = java.sql.Date.valueOf(DATE_CURREN);

		for (int i = 0; i < index; i++) {
			date = cronGen.next(date);
			LOG.info(new java.text.SimpleDateFormat("EEE, MMM d, yyyy 'at' hh:mm:ss a").format(date));
		}
	}
}
