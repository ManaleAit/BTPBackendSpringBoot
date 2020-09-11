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

import ma.s.gcm.dto.CongeDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.CongeService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/conge")
@PreAuthorize("isAuthenticated()")
public class CongeResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(CongeResource.class);

    private final CongeService congeService;

    public CongeResource(CongeService congeService) {
        this.congeService = congeService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody CongeDto CongeDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE add Conge by id : {}", CongeDto.getId());
        congeService.save(CongeDto);
        LOGGER.debug("END RESOURCE add Conge by id : {} is ok", CongeDto.getId());
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody CongeDto CongeDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Conge  by   id: {}", CongeDto.getId());
        congeService.save(CongeDto);
        LOGGER.debug("END RESOURCE update Conge  by id : {}  is ok", CongeDto.getId());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public CongeDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Conge by id : {}", id);
        CongeDto CongeDto = congeService.findById(id);
        LOGGER.debug("END RESOURCE find Conge by id : {}", id);
        return CongeDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<CongeDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Conges");
        List<CongeDto> CongeDtos = congeService.findAll();
        LOGGER.debug("END RESOURCE find all Conges, size: {}", CongeDtos.size());
        return CongeDtos;
    }
    
    @GetMapping("/demandes")
    @JsonView(UserView.Basic.class)
    public List<CongeDto> getDemandeConge() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Conges");
        List<CongeDto> CongeDtos = congeService.findDemandeConge();
        LOGGER.debug("END RESOURCE find all Conges, size: {}", CongeDtos.size());
        return CongeDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Conge by id : {}", id);
        congeService.delete(id);
        LOGGER.debug("END RESOURCE delete Conge by id : {}, is ok", id);
    }
}
