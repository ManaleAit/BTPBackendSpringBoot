package ma.s.gcm.service;

import ma.s.gcm.dto.ProjetDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.ProjetMapper;
import ma.s.gcm.repository.ProjetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FonctionService.class);

    private ProjetRepository projetRepository;

    public ProjetService(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    public ProjetDto findById(Long id) throws BureauEtudeException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(projetRepository.findById(id))
                .map(v -> ProjetMapper.toDto(v.get()))
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Projet not found"));
    }

    public List<ProjetDto> findAll() throws BureauEtudeException {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(projetRepository.findAll())
                .map(ProjetMapper::toDtos)
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Projets not found"));
    }

    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        projetRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public void save(ProjetDto projetDto) {
        LOGGER.debug("START SERVICE save by id {}", projetDto.getId());
        projetRepository.save(ProjetMapper.toEntity(projetDto));
        LOGGER.debug("START SERVICE save by id {}, name: {}", projetDto.getId());
    }
}

