package ma.s.gcm.mapper;

import ma.s.gcm.domain.Ville;
import ma.s.gcm.dto.VilleDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class VilleMapperTest {

    @Test
    public void toDto() {
        Ville user = Ville.builder().build();
        VilleDto villeDto = VilleMapper.toDto(user);
        assertNotNull(villeDto);
        Assertions.assertThat(villeDto).isEqualToIgnoringGivenFields(user, "Casablanca");
        assertNull(villeDto.getLibelle());
    }

    @Test
    public void toDtos() {
        Ville casablanca = Ville.builder().libelle("Casablanca").build();
        Ville fes = Ville.builder().libelle("Fès").build();
        Ville safi = Ville.builder().libelle("Safi").build();
        List<VilleDto> userDtos = VilleMapper.toDtos(asList(casablanca, null, fes, safi));
        assertTrue(userDtos != null && userDtos.size() == 3);
    }

    @Test
    public void toEntity() {
        VilleDto villeDto = VilleDto.builder().build();
        Ville ville = VilleMapper.toEntity(villeDto);
        assertNotNull(ville);
        Assertions.assertThat(ville).isEqualToIgnoringGivenFields(villeDto, "nom", "id");
        assertNull(ville.getId());
        assertNull(ville.getLibelle());
    }
}