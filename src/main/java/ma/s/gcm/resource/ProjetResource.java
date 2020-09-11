package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.dto.ProjetDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.ProjetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/projet")
@PreAuthorize("isAuthenticated()")
public class ProjetResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetResource.class);

    private final ProjetService projetService;

    public ProjetResource(ProjetService projetService) {
        this.projetService = projetService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody ProjetDto ProjetDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE add Projet by id : {}", ProjetDto.getId());
        projetService.save(ProjetDto);
        LOGGER.debug("END RESOURCE add Projet by id : {}: is ok", ProjetDto.getId());
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody ProjetDto projetDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Projet by  id: {}", projetDto.getId());
        projetService.save(projetDto);
        LOGGER.debug("END RESOURCE update Projet by id : {}  is ok", projetDto.getId());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public ProjetDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Projet by id : {}", id);
        ProjetDto projetDto = projetService.findById(id);
        LOGGER.debug("END RESOURCE find Projet by id : {}", id);
        return projetDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<ProjetDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Projets");
        List<ProjetDto> projetDtos = projetService.findAll();
        LOGGER.debug("END RESOURCE find all Projets, size: {}", projetDtos.size());
        return projetDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Projet by id : {}", id);
        projetService.delete(id);
        LOGGER.debug("END RESOURCE delete Projet by id : {}, is ok", id);
    }
}

