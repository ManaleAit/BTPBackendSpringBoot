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
import ma.s.gcm.dto.FactureDto;
import ma.s.gcm.dto.PrestationDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.FactureMapper;
import ma.s.gcm.mapper.PrestationMapper;
import ma.s.gcm.repository.FactureRepository;
import ma.s.gcm.repository.PrestationRepository;

@Service
@Transactional
public class FactureService {
	private static final Logger LOGGER = LoggerFactory.getLogger(FactureService.class);

	private FactureRepository factureRepository;
	private PrestationRepository prestationRepository;
	@Autowired
	public FactureService(FactureRepository factureRepository,PrestationRepository prestationRepository) {
		this.factureRepository = factureRepository;
		this.prestationRepository=prestationRepository;
	}

	public FactureDto findById(Long id) throws BureauEtudeException {
		LOGGER.debug("START SERVICE find by id {}", id);
		return Optional.ofNullable(factureRepository.findById(id)).map(v -> FactureMapper.toDto(v.get())).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Facture not found"));
	}

	public List<FactureDto> findAll() throws BureauEtudeException {
		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(factureRepository.findAll()).map(FactureMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Factures not found"));
	}

	public void delete(Long id) {
		FactureDto facture=this.findById(id);
		if(facture.getPrestations()!=null) {
			for(PrestationDto obg :facture.getPrestations()) {
				  obg.getFactures().remove(facture);
				  prestationRepository.save(PrestationMapper.toEntity(obg));
				  
			}
		}
		LOGGER.debug("START SERVICE delete by id {}", id);
		factureRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);
	}
    public void deletePrestation(Long id,Facture facture) {
    	         
				  Prestation pres=prestationRepository.findById(id).get();
				      Double somme=0.0;
				      for(int i=0;i<  facture.getPrestations().size();i++) {
				    	  if(pres.getId()==facture.getPrestations().get(i).getId()) {
				    		  facture.getPrestations().remove(i);
				    		  pres.getFactures().remove(facture);
				    		  prestationRepository.save(pres);
				    		  break;
				    	  }
				      }
	    	          
					  somme=facture.getMontantGlobale()-pres.getPrixPrestations();
					  facture.setMontantGlobale(somme);
					  factureRepository.save(facture);

				
			
	
    	
    }
	public void save(FactureDto factureDto) throws BureauEtudeException {
               
				LOGGER.debug("START SERVICE save by id {}", factureDto.getId());
				factureRepository.save(FactureMapper.toEntity(factureDto));
				LOGGER.debug("START SERVICE save by id {}", factureDto.getId());
			

	}
}
