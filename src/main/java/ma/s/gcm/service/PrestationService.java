package ma.s.gcm.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.domain.Facture;
import ma.s.gcm.domain.Prestation;
import ma.s.gcm.dto.PrestationDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.PrestationMapper;
import ma.s.gcm.repository.AppelOffreRepository;
import ma.s.gcm.repository.FactureRepository;
import ma.s.gcm.repository.PrestationRepository;

@Service
@Transactional
public class PrestationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestationService.class);

	private PrestationRepository prestationRepository;

	private FactureRepository factureRepository;
	private AppelOffreRepository appleOffreRepository;

	@Autowired
	public PrestationService(FactureRepository factureRepository,PrestationRepository prestationRepository,AppelOffreRepository appleOffreRepository) {
		this.prestationRepository = prestationRepository;
		this.appleOffreRepository=appleOffreRepository;
		this.factureRepository=factureRepository;
	}

	public PrestationDto findById(Long id) throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find by id {}", id);
			return Optional.ofNullable(prestationRepository.findById(id)).map(v -> PrestationMapper.toDto(v.get()))
					.orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,
							"Prestation not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestation not found");
		}
	}

	public List<PrestationDto> findAll() throws BureauEtudeException {
		try {
			LOGGER.debug("START SERVICE find all");
			return Optional.ofNullable(prestationRepository.findAll()).map(PrestationMapper::toDtos).orElseThrow(
					() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestations not found"));
		} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, t.getMessage());
		}
	}

	public void delete(Long id) {

		if (prestationRepository.findById(id) == null) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Prestations not found");
		}
		
		
        
		Prestation pres=prestationRepository.findById(id).get();
		List<Facture>  factures=factureRepository.findAll();
		for(int j=0;j<factureRepository.findAll().size();j++) {
			
		 for(int i=0;i<  factures.get(j).getPrestations().size();i++) {
	    	  if(pres.getId()==factures.get(j).getPrestations().get(i).getId()) {
	    		  factures.get(j).getPrestations().remove(i);	 ;
	    		  factures.get(j).setMontantGlobale(factures.get(j).getMontantGlobale()-pres.getPrixPrestations());
	    		  prestationRepository.save(pres);
	    		  factureRepository.save(factures.get(j));
	    	  }
	      }
		}

		LOGGER.debug("START SERVICE delete by id {}", id);
		prestationRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);

	}

	public void save(PrestationDto prestationDto) throws BureauEtudeException {
		if (prestationDto == null) {

			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Vous avez entrer une valeur null");
		}
		if (!appleOffreRepository.findById(prestationDto.getAppelOffre().getId()).isPresent()) {
            
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "l'appel d'offre n'existe pas");
		}
		if (prestationDto.getAppelOffre() == null) {
          
			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Appel offre ne doit pas etre null");
		}
		if (prestationDto.getAppelOffre().getId() == null) {

			throw new BureauEtudeException(ExceptionCode.API_DATA_ERRORS, "Id d'ppel offre ne doit pas etre null");
		}

		LOGGER.debug("START SERVICE save by id {}", prestationDto.getId());
		prestationRepository.save(PrestationMapper.toEntity(prestationDto));
		LOGGER.debug("START SERVICE save by id {}", prestationDto.getId());

	}
}
