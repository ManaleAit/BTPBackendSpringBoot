package ma.s.gcm.service;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.BDDMockito.given;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import ma.s.gcm.domain.Fonction;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.mapper.FonctionMapper;
import ma.s.gcm.repository.FonctionRepository;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FonctionServiceTest {
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	@Autowired
	private FonctionService fonctionService;
	@MockBean
	private FonctionRepository fonctionRepository;

	@Test
	public void saveNullFonctionTest() {
		Fonction fonction = new Fonction();
		fonction.setId(1002L);
		given(fonctionRepository.findByLibelle(any())).willReturn(null);
		given(fonctionRepository.save(any())).willReturn(fonction);
		fonctionService.save(FonctionMapper.toDto(fonction));

	}

	@Test(expected = BureauEtudeException.class)
	public void saveExceptionTest() {
		Fonction fonction = new Fonction();
		fonction.setId(1002L);
		fonction.setLibelle("Chef de projet");
		given(fonctionRepository.save(any())).willThrow(DataIntegrityViolationException.class);
		fonctionService.save(FonctionMapper.toDto(fonction));

	}

	@Test
	public void saveNotNullFonctionTest() {
		Fonction fonction = new Fonction();
		fonction.setId(1002L);
		given(fonctionRepository.findByLibelle(any())).willReturn(fonction);
		fonctionService.save(FonctionMapper.toDto(fonction));
	}


}
