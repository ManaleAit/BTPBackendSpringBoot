package ma.s.gcm.service;

import ma.s.gcm.domain.Marche;
import ma.s.gcm.dto.MarcheDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.MarcheMapper;
import ma.s.gcm.repository.MarcheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MarcheService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FonctionService.class);

    private MarcheRepository marcheRepository;

    public MarcheService(MarcheRepository marcheRepository) {
        this.marcheRepository = marcheRepository;
    }

    public MarcheDto findById(Long id) {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(marcheRepository.findById(id))
                .map(v -> MarcheMapper.toDto(v.get()))
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Marché not found"));
    }

    public List<MarcheDto> findAll()  {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(marcheRepository.findAll())
                .map(MarcheMapper::toDtos)
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Marchés not found"));
    }

    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        marcheRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public Marche  save(MarcheDto marcheDto) {
        
            LOGGER.debug("START SERVICE save by id {}", marcheDto.getId());
           
            LOGGER.debug("START SERVICE save by id {} ", marcheDto.getId());
            
            return   marcheRepository.save(MarcheMapper.toEntity(marcheDto));
		
        
    }
}
