package ma.s.gcm.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.PrestationDto;

public class PrestationMapperTest {
	@Test
	public void toDto() {
		Prestation prestation = Prestation.builder().build();
		PrestationDto prestationDto = PrestationMapper.toDto(prestation);
		assertNotNull(prestationDto);
		Assertions.assertThat(prestationDto).isEqualToIgnoringGivenFields(prestation);
	}

	@Test
	public void toDtos() {
		Prestation prestation1 = Prestation.builder().id(1L).build();
		Prestation prestation2 = Prestation.builder().id(2L).build();
		Prestation prestation3 = Prestation.builder().id(3L).build();
		List<PrestationDto> prestationDtos = PrestationMapper.toDtos(asList(prestation1, null, prestation2, prestation3));
		assertTrue(prestationDtos != null && prestationDtos.size() == 3);
	}

	@Test
	public void toEntities() {
		PrestationDto prestationDto1 = PrestationDto.builder().id(1L).build();
		PrestationDto prestationDto2 = PrestationDto.builder().id(2L).build();
		PrestationDto prestationDto3 = PrestationDto.builder().id(3L).build();
		List<Prestation> prestationDtos = PrestationMapper.toEntities(asList(prestationDto1, null, prestationDto2, prestationDto3));
		assertTrue(prestationDtos != null && prestationDtos.size() == 3);
	}

	@Test
	public void toEntity() {
		PrestationDto prestationDto = PrestationDto.builder().build();
		Prestation prestation = PrestationMapper.toEntity(prestationDto);
		Assertions.assertThat(prestation).isEqualToIgnoringGivenFields(prestationDto);
	}
}
