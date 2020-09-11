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

import ma.s.gcm.dto.MaitreOuvrageDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.repository.MaitreOuvrageRepository;
import ma.s.gcm.service.MaitreOuvrageService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/MaitreOuvrage")
@PreAuthorize("isAuthenticated()")
public class MaitreOuvrageResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaitreOuvrageResource.class);

    private final MaitreOuvrageService maitreOuvrageService;
    private final MaitreOuvrageRepository maitreOuvRepository;

    @Autowired
    public MaitreOuvrageResource(MaitreOuvrageService maitreOuvrageService, MaitreOuvrageRepository maitreOuvRepository) {
        this.maitreOuvrageService = maitreOuvrageService;
        this.maitreOuvRepository=maitreOuvRepository;
    }


    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<MaitreOuvrageDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find MaitreOuvrage");
        List<MaitreOuvrageDto> maitreOuvrageDtos = maitreOuvrageService.findAll();
        LOGGER.debug("END RESOURCE find all MaitreOuvrage, size: {}", maitreOuvrageDtos.size());
        return maitreOuvrageDtos;
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public MaitreOuvrageDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Maitre d'Ouvrage  by id : {}", id);
        MaitreOuvrageDto maitreOuvrageDto = maitreOuvrageService.findById(id);
        LOGGER.debug("END RESOURCE find AppelOffre  by id : {}, nname :{}", id, maitreOuvrageDto.getId(), maitreOuvrageDto.getDesignation());
        return maitreOuvrageDto;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Maitre Ouvrage by id : {}", id);
        maitreOuvrageService.delete(id);
        LOGGER.debug("END RESOURCE delete Maitre Ouvrageby id : {}, is ok", id);
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody MaitreOuvrageDto maitreOuvrageDto) throws BureauEtudeException {
    	
    	if (maitreOuvRepository.findByNumeroPatente(maitreOuvrageDto.getNumeroPatente())== null) {
    		 LOGGER.debug("START RESOURCE add maitre ouvrage by id: {}", maitreOuvrageDto.getId());
    	        maitreOuvrageService.save(maitreOuvrageDto);
    	        LOGGER.debug("END RESOURCE add maitre ouvrage by id : {}, name: {} is ok", maitreOuvrageDto.getId(), maitreOuvrageDto.getDesignation());
		} else {
			throw new BureauEtudeException(ExceptionCode.API_UNIQUE,
					" le numéro de Patente est déja existe doit etre unique!!!!!! ");
		}
       
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody MaitreOuvrageDto maitreOuvrageDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update maitre ouvrage  by name : {}, id: {}", maitreOuvrageDto.getDesignation(), maitreOuvrageDto.getId());
        maitreOuvrageService.save(maitreOuvrageDto);
        LOGGER.debug("END RESOURCE update maitre ouvrage id : {}, name: {} is ok", maitreOuvrageDto.getId(), maitreOuvrageDto.getDesignation());
    }
}