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

import ma.s.gcm.dto.PrestationDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.PrestationService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/prestation")
@PreAuthorize("isAuthenticated()")
public class PrestationResource {
	 private static final Logger LOGGER = LoggerFactory.getLogger(PrestationResource.class);

	    private final PrestationService prestationService;

	    public PrestationResource(PrestationService prestationService) {
	        this.prestationService = prestationService;
	    }

	    @PostMapping
	    @JsonView(UserView.Basic.class)
	    public void add(@RequestBody  PrestationDto  prestationDto) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE add Prestation by id de marché : {}", prestationDto.getId());
	        prestationService.save(prestationDto);
	        LOGGER.debug("END RESOURCE add Prestation by id : {}  is ok", prestationDto.getId());
	    }

	    @PutMapping
	    @JsonView(UserView.Basic.class)
	    public void update(@RequestBody PrestationDto prestationDto) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE update Prestation by : id: {}", prestationDto.getId());
	        prestationService.save(prestationDto);
	        LOGGER.debug("END RESOURCE update Prestation by id : {}  is ok", prestationDto.getId());
	    }

	    @GetMapping("/{id}")
	    @JsonView(UserView.Basic.class)
	    public PrestationDto get(@PathVariable Long id) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE find Prestation by id : {}", id);
	        PrestationDto PrestationDto = prestationService.findById(id);
	        LOGGER.debug("END RESOURCE find Prestation by id : {} ", id, PrestationDto.getId());
	        return PrestationDto;
	    }

	    @GetMapping
	    @JsonView(UserView.Basic.class)
	    public List<PrestationDto> getAll() throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE all find Prestations");
	        List<PrestationDto> PrestationDtos = prestationService.findAll();
	        LOGGER.debug("END RESOURCE find all Prestations, size: {}", PrestationDtos.size());
	        return PrestationDtos;
	    }

	    @DeleteMapping("/{id}")
	    @JsonView(UserView.Basic.class)
	    public void delete(@PathVariable Long id) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE delete Prestation by id : {}", id);
	        prestationService.delete(id);
	        LOGGER.debug("END RESOURCE delete Prestation by id : {}, is ok", id);
	    }
	}

