package ma.s.gcm.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ma.s.gcm.domain.Intervenant;
import ma.s.gcm.dto.IntervenantDto;

public class IntervenantMapperTest {
	 @Test
	    public void toDto() {
		   Intervenant intervenant = Intervenant.builder().build();
		   IntervenantDto  intervenantDto = IntervenantMapper.toDto(intervenant);
	        assertNotNull(intervenantDto);
	        Assertions.assertThat(intervenantDto).isEqualToIgnoringGivenFields(intervenant);
	    }

	    @Test
	    public void toDtos() {
	    	Intervenant intervenant1 = Intervenant.builder().email("manalait2@gmail.com").build();
	    	Intervenant intervenant2 = Intervenant.builder().email("manalait@gmail.com").build();
	    	Intervenant intervenant3 = Intervenant.builder().email("manalait3@gmail.com").build();
	        List<IntervenantDto> intervenantDtos = IntervenantMapper.toDtos(asList(intervenant1, null, intervenant2,intervenant3));
	        assertTrue(intervenantDtos != null && intervenantDtos.size() == 3);
	    }

	    @Test
	    public void toEntity() {
	    	IntervenantDto intervenantDto = IntervenantDto.builder().build();
	    	Intervenant intervenant = IntervenantMapper.toEntity(intervenantDto);
	        assertNotNull(intervenant);
	        Assertions.assertThat(intervenant).isEqualToIgnoringGivenFields(intervenantDto);
	    }
	}
