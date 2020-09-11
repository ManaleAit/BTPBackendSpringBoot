package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.dto.FonctionDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.FonctionRepository;
import ma.s.gcm.service.FonctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/fonction")
@PreAuthorize("isAuthenticated()")
public class FonctionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FonctionResource.class);
   
    private final FonctionRepository fonctionrepository;
    
    private final FonctionService fonctionService;

    
    @Autowired
	public FonctionResource(FonctionService fonctionService,FonctionRepository fonctionrepository) {
        this.fonctionService = fonctionService;
        this.fonctionrepository=fonctionrepository;
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody FonctionDto fonctionDto) throws BureauEtudeException {
    if(fonctionrepository.findByLibelle(fonctionDto.getLibelle())==null) {
        LOGGER.debug("START RESOURCE update Fonction by name : {}, id: {}", fonctionDto.getLibelle(), fonctionDto.getId());
        fonctionService.save(fonctionDto);
        LOGGER.debug("END RESOURCE update Fonction by id : {}, name: {} is ok", fonctionDto.getId(), fonctionDto.getLibelle());
    } else {
		throw new BureauEtudeException(ExceptionCode.API_UNIQUE,
				" Le nom est déja existe doit etre unique!!!!!! ");
	}
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody FonctionDto fonctionDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Fonction by name : {}, id: {}", fonctionDto.getLibelle(), fonctionDto.getId());
        fonctionService.save(fonctionDto);
        LOGGER.debug("END RESOURCE update Fonction by id : {}, name: {} is ok", fonctionDto.getId(), fonctionDto.getLibelle());
    }
   
    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public FonctionDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Fonction by id : {}", id);
        FonctionDto fonctionDto = fonctionService.findById(id);
        LOGGER.debug("END RESOURCE find Fonction by id : {}, name :{}", id, fonctionDto.getLibelle());
        return fonctionDto;
    }
    
    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<FonctionDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Fonctions");
        List<FonctionDto> fonctionDtos = fonctionService.findAll();
        LOGGER.debug("END RESOURCE find all Fonctions, size: {}", fonctionDtos.size());
        return fonctionDtos;
    }
    @PreAuthorize("hasRole('ROLE_assistant')")
    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete fonction by id : {}", id);
        fonctionService.delete(id);
        LOGGER.debug("END RESOURCE delete fonction by id : {}, is ok", id);
    }
    
   
}
