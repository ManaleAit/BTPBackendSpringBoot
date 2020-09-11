package ma.s.gcm.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import ma.s.gcm.dto.CautionDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.CautionService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/caution")
@PreAuthorize("isAuthenticated()")
public class CautionResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(CautionResource.class);

	private final CautionService cautionService;

	public CautionResource(CautionService cautionService) {
		this.cautionService = cautionService;
	}

	@PostMapping
	@JsonView(UserView.Basic.class)
	public void add(@RequestBody CautionDto cautionDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE add caution by Id : {}", cautionDto.getId());
		cautionService.save(cautionDto);
		LOGGER.debug("END RESOURCE add caution by id : {} is ok", cautionDto.getId());
	}

	@PutMapping
	@JsonView(UserView.Basic.class)
	public void update(@RequestBody CautionDto cautionDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE update caution by id: {}", cautionDto.getId());
		cautionService.save(cautionDto);
		LOGGER.debug("END RESOURCE update caution by id :: {} is ok", cautionDto.getId());
	}

	@GetMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public CautionDto get(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find caution by id : {}", id);
		CautionDto cautionDto = cautionService.findById(id);
		LOGGER.debug("END RESOURCE find caution by id : {}", id, cautionDto.getId());
		return cautionDto;
	}

	@GetMapping
	@JsonView(UserView.Basic.class)
	public List<CautionDto> getAll() throws BureauEtudeException {
		LOGGER.debug("START RESOURCE all find Cautions");
		List<CautionDto> cautionDtos = cautionService.findAll();
		LOGGER.debug("END RESOURCE find all Cautions, size: {}", cautionDtos.size());
		return cautionDtos;
	}

	@DeleteMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public void delete(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE delete Caution by id : {}", id);
		cautionService.delete(id);
		LOGGER.debug("END RESOURCE delete Caution by id : {}, is ok", id);
	}
}
