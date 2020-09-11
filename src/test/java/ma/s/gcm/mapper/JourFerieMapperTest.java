package ma.s.gcm.mapper;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.dto.JourFerieDto;

public class JourFerieMapperTest {
	 @Test
	    public void toDto() {
	        JourFerie jour = JourFerie.builder().build();
	        JourFerieDto  jourDto = JourFerieMapper.toDto(jour);
	        assertNotNull(jourDto);
	        Assertions.assertThat(jourDto).isEqualToIgnoringGivenFields(jour, "Aid Adha");
	        assertNull(jourDto.getLibelle());
	    }

	    @Test
	    public void toDtos() {
	    	JourFerie libelle1 = JourFerie.builder().libelle("Aid Adha").build();
	    	JourFerie libelle2 = JourFerie.builder().libelle("Fete de travail").build();
	    	JourFerie libelle3 = JourFerie.builder().libelle("Premier janvier").build();
	        List<JourFerieDto> JourFerieDtos = JourFerieMapper.toDtos(asList(libelle1, null, libelle2, libelle3));
	        assertTrue(JourFerieDtos != null && JourFerieDtos.size() == 3);
	    }

	    @Test
	    public void toEntity() {
	    	JourFerieDto jourFerieDto = JourFerieDto.builder().build();
	    	JourFerie jourFerie = JourFerieMapper.toEntity(jourFerieDto);
	        assertNotNull(jourFerie);
	        Assertions.assertThat(jourFerie).isEqualToIgnoringGivenFields(jourFerieDto, "libelle", "id");
	        assertNull(jourFerie.getId());
	        assertNull(jourFerie.getLibelle());
	    }
	}
