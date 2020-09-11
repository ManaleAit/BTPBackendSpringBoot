package ma.s.gcm.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ma.s.gcm.domain.Conge;
import ma.s.gcm.dto.CongeDto;

public class CongeMapperTest {
	    @Test
	    public void toDto() {
		 Conge conge = Conge.builder().build();
		 CongeDto  congeDto = CongeMapper.toDto(conge);
	     assertNotNull(congeDto);
	     Assertions.assertThat(congeDto).isEqualToIgnoringGivenFields(conge);
	    }

	    @Test
	    public void toDtos() {
	    	Conge Conge1 = Conge.builder().id(1L).build();
	    	Conge Conge2 = Conge.builder().id(2L).build();
	    	Conge Conge3 = Conge.builder().id(3L).build();
	        List<CongeDto> congeDtos = CongeMapper.toDtos(asList(Conge1, null, Conge2,Conge3));
	        assertTrue(congeDtos != null && congeDtos.size() == 3);
	    }
	    @Test
	    public void toEntities() {
	    	CongeDto CongeDto1 = CongeDto.builder().id(1L).build();
	    	CongeDto CongeDto2 = CongeDto.builder().id(2L).build();
	    	CongeDto CongeDto3 = CongeDto.builder().id(3L).build();
	        List<Conge> congeDtos = CongeMapper.toEntities(asList(CongeDto1, null, CongeDto2,CongeDto3));
	        assertTrue(congeDtos != null && congeDtos.size() == 3);
	    }
	    @Test
	    public void toEntity() {
	    	CongeDto congeDto = CongeDto.builder().build();
	    	Conge conge = CongeMapper.toEntity(congeDto);
	        Assertions.assertThat(conge).isEqualToIgnoringGivenFields(congeDto);
	    }
	}
