package ma.s.gcm.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ma.s.gcm.domain.EtablissementFinancier;
import ma.s.gcm.dto.EtablissementFinancierDto;

public class EtablissementFinancierMapperTest {
	@Test
	public void toDto() {
		EtablissementFinancier etablissementFinancier = EtablissementFinancier.builder().build();
		EtablissementFinancierDto etablissementFinancierDto = EtablissementFinancierMapper
				.toDto(etablissementFinancier);
		assertNotNull(etablissementFinancierDto);
		Assertions.assertThat(etablissementFinancierDto).isEqualToIgnoringGivenFields(etablissementFinancier);
	}

	@Test
	public void toDtos() {
		EtablissementFinancier etablissementFinancier1 = EtablissementFinancier.builder().id(1L).build();
		EtablissementFinancier etablissementFinancier2 = EtablissementFinancier.builder().id(2L).build();
		EtablissementFinancier etablissementFinancier3 = EtablissementFinancier.builder().id(3L).build();
		List<EtablissementFinancierDto> EtablissementFinancierDtos = EtablissementFinancierMapper
				.toDtos(asList(etablissementFinancier1, null, etablissementFinancier2, etablissementFinancier3));
		assertTrue(EtablissementFinancierDtos != null && EtablissementFinancierDtos.size() == 3);
	}

	@Test
	public void toEntity() {
		EtablissementFinancierDto etablissementFinancierDto = EtablissementFinancierDto.builder().build();
		EtablissementFinancier etablissementFinancier = EtablissementFinancierMapper.toEntity(etablissementFinancierDto);
		assertNotNull(etablissementFinancier);
		Assertions.assertThat(etablissementFinancier).isEqualToIgnoringGivenFields(etablissementFinancierDto);
	}
}
