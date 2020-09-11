package ma.s.gcm.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import ma.s.gcm.dto.AppelOffreDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.AppelOffreRepository;
import ma.s.gcm.service.AppelOffreService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Appeloffre")
@PreAuthorize("isAuthenticated()")
public class AppelOffreResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppelOffreResource.class);

	private final AppelOffreRepository appelOffreRepo;

	private final AppelOffreService appelOffreService;

	@Autowired
	public AppelOffreResource(AppelOffreService appelOffreS, AppelOffreRepository appelOffreRepo) {
		this.appelOffreService = appelOffreS;
		this.appelOffreRepo = appelOffreRepo;
	}

	@GetMapping
	@JsonView(UserView.Basic.class)
	public List<AppelOffreDto> getAll() throws BureauEtudeException {
		LOGGER.debug("START RESOURCE all find AppelOffre");
		List<AppelOffreDto> appelOffreDtos = appelOffreService.findAll();
		LOGGER.debug("END RESOURCE find all AppelOffre, size: {}", appelOffreDtos.size());
		return appelOffreDtos;
	}

	@GetMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public AppelOffreDto get(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE find AppelOffre  by id : {}", id);
		AppelOffreDto appelOffreDto = appelOffreService.findById(id);
		LOGGER.debug("END RESOURCE find AppelOffre  by id : {}, num d'offre :{}", id, appelOffreDto.getNumOffre());
		return appelOffreDto;
	}

	@DeleteMapping("/{id}")
	@JsonView(UserView.Basic.class)
	public void delete(@PathVariable Long id) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE delete AppelOffre by id : {}", id);
		appelOffreService.delete(id);
		LOGGER.debug("END RESOURCE delete AppelOffre by id : {}, is ok", id);
	}

	@PostMapping
	@JsonView(UserView.Basic.class)
	public void add(@RequestBody AppelOffreDto appelOffreDto) throws BureauEtudeException {

		if (appelOffreRepo.findByNumOffre(appelOffreDto.getNumOffre()) != null) {
			throw new BureauEtudeException(ExceptionCode.API_VALIDATION,
					" Le numero d'appel d'offre  doit etre unique!!!!!! ");
		}
		LOGGER.debug("START RESOURCE add city by num d'offre : {}", appelOffreDto.getNumOffre());
		appelOffreService.save(appelOffreDto);
		LOGGER.debug("END RESOURCE add appel d'offre   by id : {}, num d'offre: {} is ok", appelOffreDto.getId(),
				appelOffreDto.getNumOffre());
	}

	@PutMapping
	@JsonView(UserView.Basic.class)
	public void update(@RequestBody AppelOffreDto appelOffreDto) throws BureauEtudeException {
		LOGGER.debug("START RESOURCE update appelOffreDto  by num d'offre : {}, id: {}", appelOffreDto.getNumOffre(),
				appelOffreDto.getId());
		appelOffreService.save(appelOffreDto);
		LOGGER.debug("END RESOURCE update appelOffreDto id : {}, num d'offre: {} is ok", appelOffreDto.getId(),
				appelOffreDto.getNumOffre());
	}
}
