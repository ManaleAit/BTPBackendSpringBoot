package ma.s.gcm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import ma.s.gcm.domain.TypeDocument;
import ma.s.gcm.dto.TypeDocumentDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.TypeDocumentMapper;
import ma.s.gcm.repository.TypeDocumentRepository;

import org.slf4j.LoggerFactory;

@Service
@Transactional
public class TypeDocumentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TypeDocumentService.class);

    private TypeDocumentRepository typeDocumentRepository;

    public TypeDocumentService(TypeDocumentRepository typeDocumentRepository) {
        this.typeDocumentRepository = typeDocumentRepository;
    }

    public TypeDocumentDto findById(Long id) throws BureauEtudeException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(typeDocumentRepository.findById(id))
                .map(v->TypeDocumentMapper.toDto(v.get()))
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "type document not found"));
    }



    public List<TypeDocumentDto> findAll() throws BureauEtudeException {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(typeDocumentRepository.findAll())
                .map(TypeDocumentMapper::toDtos)
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "types de documents not found"));
    }

    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        typeDocumentRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public TypeDocument save(TypeDocumentDto typeDocumentDto) throws BureauEtudeException {
    	try {
        LOGGER.debug("START SERVICE save by id {}, name: {}", typeDocumentDto.getId(), typeDocumentDto.getLibelle());
        return typeDocumentRepository.save(TypeDocumentMapper.toEntity(typeDocumentDto));
        //LOGGER.debug("START SERVICE save by id {}, name: {}", typeDocumentDto.getId(), typeDocumentDto.getLibelle());
    	} catch (Throwable t) {
			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND,"Resource is not found");
		}
    }
}
