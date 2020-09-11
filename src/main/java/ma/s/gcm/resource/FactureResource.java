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

import ma.s.gcm.domain.Facture;
import ma.s.gcm.dto.FactureDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.FactureService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/facture")
@PreAuthorize("isAuthenticated()")
public class FactureResource {
	 private static final Logger LOGGER = LoggerFactory.getLogger(FactureResource.class);
	   
	    
	    private final FactureService factureService;

	    
	    @Autowired
		public FactureResource(FactureService factureService) {
	        this.factureService = factureService;
	    }

	    @PostMapping
	    @JsonView(UserView.Basic.class)
	    public void add(@RequestBody FactureDto factureDto) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE update Facture by  id: {}", factureDto.getId());
	        factureService.save(factureDto);
	        LOGGER.debug("END RESOURCE update Facture by id : {} is ok", factureDto.getId());
	   
	    }

	    @PutMapping
	    @JsonView(UserView.Basic.class)
	    public void update(@RequestBody FactureDto factureDto) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE update Facture by  id: {}", factureDto.getId());
	        factureService.save(factureDto);
	        LOGGER.debug("END RESOURCE update Facture by id : {} is ok", factureDto.getId());
	    }

	    @GetMapping("/{id}")
	    @JsonView(UserView.Basic.class)
	    public FactureDto get(@PathVariable Long id) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE find Facture by id : {}", id);
	        FactureDto factureDto = factureService.findById(id);
	        LOGGER.debug("END RESOURCE find Facture by id : {} ", id);
	        return factureDto;
	    }

	    @GetMapping
	    @JsonView(UserView.Basic.class)
	    public List<FactureDto> getAll() throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE all find actures");
	        List<FactureDto> factureDtos = factureService.findAll();
	        LOGGER.debug("END RESOURCE find all Factures, size: {}", factureDtos.size());
	        return factureDtos;
	    }

	    @DeleteMapping("/{id}")
	    @JsonView(UserView.Basic.class)
	    public void delete(@PathVariable Long id) throws BureauEtudeException {
	        LOGGER.debug("START RESOURCE delete facture by id : {}", id);
	        factureService.delete(id);
	        LOGGER.debug("END RESOURCE delete facture by id : {}, is ok", id);
	    }
	    
	    
	    @PutMapping("deletePres/{id}")
	    @JsonView(UserView.Basic.class)
	    public void update(@PathVariable Long id,@RequestBody Facture facture) throws BureauEtudeException {
	        factureService.deletePrestation(id,facture);
	    }
	}
