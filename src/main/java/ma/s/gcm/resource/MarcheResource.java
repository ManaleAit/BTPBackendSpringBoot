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

import ma.s.gcm.dto.MarcheDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.MarcheRepository;
import ma.s.gcm.service.MarcheService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Marche")
@PreAuthorize("isAuthenticated()")
public class MarcheResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarcheResource.class);

    private final MarcheService marcheService;
    @Autowired
    private MarcheRepository marcheRepository;
    public MarcheResource(MarcheService marcheService, MarcheRepository marcheRepository) {
        this.marcheService = marcheService;
        this.marcheRepository=marcheRepository;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public Long add(@RequestBody MarcheDto marcheDto) throws BureauEtudeException {
    	if (marcheRepository.findByNumMarche(marcheDto.getNumMarche()) == null) {
        LOGGER.debug("START RESOURCE add Marche by num de marché : {}", marcheDto.getNumMarche());
       
        return  marcheService.save(marcheDto).getId();
       //.debug("END RESOURCE add Marche by id : {}, num de marché : {} is ok", marcheDto.getId(), marcheDto.getNumMarche());
    	} else {
            throw new BureauEtudeException(ExceptionCode.API_VALIDATION, " Le numero de marche est déja existe doit etre unique!!!!!! ");
        }
    	}

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody MarcheDto marcheDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Marche by : id: {}", marcheDto.getId());
        marcheService.save(marcheDto);
        LOGGER.debug("END RESOURCE update Marche by id : {}  is ok", marcheDto.getId());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public MarcheDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Marche by id : {}", id);
        MarcheDto marcheDto = marcheService.findById(id);
        LOGGER.debug("END RESOURCE find Marche by Num de marché: {} ", id, marcheDto.getNumMarche());
        return marcheDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<MarcheDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Marches");
        List<MarcheDto> MarcheDtos = marcheService.findAll();
        LOGGER.debug("END RESOURCE find all Marches, size: {}", MarcheDtos.size());
        return MarcheDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Marche by id : {}", id);
        marcheService.delete(id);
        LOGGER.debug("END RESOURCE delete Marche by id : {}, is ok", id);
    }
}

