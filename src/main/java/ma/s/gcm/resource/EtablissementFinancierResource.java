package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.dto.EtablissementFinancierDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.EtablissementFinancierService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/etabli")
@PreAuthorize("isAuthenticated()")
public class EtablissementFinancierResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(EtablissementFinancierResource.class);

	private final EtablissementFinancierService etablissementFinancierService;

	public EtablissementFinancierResource(
			ma.s.gcm.service.EtablissementFinancierService etablissementFinancierService) {
		this.etablissementFinancierService = etablissementFinancierService;
	}

	@PostMapping
	@JsonView(UserView.Basic.class)
	public void add(@RequestBody EtablissementFinancierDto etablissementFinancierDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE add EtablissementFinancier by id : {}", etablissementFinancierDto.getId());
		etablissementFinancierService.save(etablissementFinancierDto);
		LOGGER.debug("END RESOURCE add EtablissementFinancier by id : {} is ok", etablissementFinancierDto.getId());
	}

	@PutMapping
	@JsonView(UserView.Basic.class)
	public void update(@RequestBody EtablissementFinancierDto etablissementFinancierDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE update EtablissementFinancier  by   id: {}", etablissementFinancierDto.getId());
		etablissementFinancierService.save(etablissementFinancierDto);
		LOGGER.debug("END RESOURCE update EtablissementFinancier  by id : {}  is ok",
				etablissementFinancierDto.getId());
	}

	@GetMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public EtablissementFinancierDto get(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find EtablissementFinancier by id : {}", id);
		EtablissementFinancierDto etablissementFinancierDto = etablissementFinancierService.findById(id);
		LOGGER.debug("END RESOURCE find EtablissementFinancier by id : {}", id);
		return etablissementFinancierDto;
	}

	@GetMapping
	@JsonView(UserView.Basic.class)
	public List<EtablissementFinancierDto> getAll() throws BureauEtudeException {
		LOGGER.debug("START RESOURCE all find EtablissementFinanciers");
		List<EtablissementFinancierDto> EtablissementFinancierDtos = etablissementFinancierService.findAll();
		LOGGER.debug("END RESOURCE find all EtablissementFinanciers, size: {}", EtablissementFinancierDtos.size());
		return EtablissementFinancierDtos;
	}

	@DeleteMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public void delete(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE delete EtablissementFinancier by id : {}", id);
		etablissementFinancierService.delete(id);
		LOGGER.debug("END RESOURCE delete EtablissementFinancier by id : {}, is ok", id);
	}
}
