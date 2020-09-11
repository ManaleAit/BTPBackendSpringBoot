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

import ma.s.gcm.dto.IntervenantDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.IntervenantRepository;
import ma.s.gcm.service.IntervenantService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/intervenant")
@PreAuthorize("isAuthenticated()")
public class IntervenantResource {
private static final Logger LOGGER = LoggerFactory.getLogger(IntervenantResource.class);

    private final IntervenantService intervenantService;
    @Autowired
	private IntervenantRepository intervenantRepository;

    public IntervenantResource(IntervenantService intervenantService) {
        this.intervenantService = intervenantService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody IntervenantDto IntervenantDto) throws BureauEtudeException {
    if(intervenantRepository.findByNumCIN(IntervenantDto.getNumCIN())==null) {
        LOGGER.debug("START RESOURCE update Intervenant by  id: {}",IntervenantDto.getId());
        intervenantService.save(IntervenantDto);
        LOGGER.debug("END RESOURCE update Intervenant by id : {}  is ok", IntervenantDto.getId());
    } else {
		throw new BureauEtudeException(ExceptionCode.API_UNIQUE,
				" Le CIN est déja existe doit etre unique!!!!!! ");
	}
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody IntervenantDto IntervenantDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Intervenant by  id: {}",  IntervenantDto.getId());
        intervenantService.save(IntervenantDto);
        LOGGER.debug("END RESOURCE update Intervenant by id : {} is ok", IntervenantDto.getId());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public IntervenantDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Intervenant by id : {}", id);
        IntervenantDto IntervenantDto = intervenantService.findById(id);
        LOGGER.debug("END RESOURCE find Intervenant by id : {}, name :{}", id);
        return IntervenantDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<IntervenantDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Intervenants");
        List<IntervenantDto> IntervenantDtos = intervenantService.findAll();
        LOGGER.debug("END RESOURCE find all Intervenants, size: {}", IntervenantDtos.size());
        return IntervenantDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Intervenant by id : {}", id);
        intervenantService.delete(id);
        LOGGER.debug("END RESOURCE delete Intervenant by id : {}, is ok", id);
    }
}


