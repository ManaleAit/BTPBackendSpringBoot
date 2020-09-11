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

import ma.s.gcm.dto.JourFerieDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.JourFerieService;
@CrossOrigin(origins = { "*" }, allowedHeaders = "*")
@RestController
@RequestMapping("/jourFerie")
@PreAuthorize("isAuthenticated()")
public class JourFerieResource {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(JourFerieResource.class);

    private final JourFerieService jourFerieService;

    public JourFerieResource(JourFerieService jourFerieService) {
        this.jourFerieService = jourFerieService;
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody JourFerieDto jourFerieDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update jour Ferie by name : {}, id: {}", jourFerieDto.getLibelle(), jourFerieDto.getId());
        jourFerieService.save(jourFerieDto);
        LOGGER.debug("END RESOURCE update jour Ferie by id : {}, name: {} is ok", jourFerieDto.getId(), jourFerieDto.getLibelle());
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody JourFerieDto jourFerieDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update jour ferie by name : {}, id: {}", jourFerieDto.getLibelle(), jourFerieDto.getId());
        jourFerieService.save(jourFerieDto);
        LOGGER.debug("END RESOURCE update jour ferie by id : {}, name: {} is ok", jourFerieDto.getId(), jourFerieDto.getLibelle());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public JourFerieDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find jour ferie by id : {}", id);
        JourFerieDto jourFerieDto = jourFerieService.findById(id);
        LOGGER.debug("END RESOURCE find jour ferie by id : {}, name :{}", id, jourFerieDto.getLibelle());
        return jourFerieDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<JourFerieDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find jours feries");
        List<JourFerieDto> jourFerieDtos = jourFerieService.findAll();
        LOGGER.debug("END RESOURCE find all jours feries, size: {}", jourFerieDtos.size());
        return jourFerieDtos;
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete jour ferie by id : {}", id);
        jourFerieService.delete(id);
        LOGGER.debug("END RESOURCE delete jour ferie by id : {}, is ok", id);
    }
}

