package ma.s.gcm.service;

import ma.s.gcm.domain.Document;
import ma.s.gcm.dto.DocumentDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.DocumentMapper;
import ma.s.gcm.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocumentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppelOffreService.class);

    private DocumentRepository documentRepository;
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentDto> findAll() throws BureauEtudeException {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(documentRepository.findAll())
                .map(DocumentMapper::toDtos)
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Document not found"));
    }

    public DocumentDto findById(Long id) throws BureauEtudeException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(documentRepository.findById(id))
                .map(v -> DocumentMapper.toDto(v.get()))
                .orElseThrow(() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Document not found"));
    }

    public void delete(Long id) {
    	/*if(documentRepository.getOne(id)!=null) {
    	AppelOffre Appel=documentRepository.getOne(id).getAppelOffre();
    	List<Document> listDoc=Appel.getDocuments();
    	for(Document doc :listDoc) {
    		if(doc.getId()==id) {
    			listDoc.remove(doc);
    		}
    	}
    	Appel.setDocuments(listDoc);
    	AppelRepo.save(Appel);*/
        LOGGER.debug("START SERVICE delete by id {}", id);
        documentRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    	/*}else {
    		throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, " le document n'existe pas");
		
    	}*/
    }

    public Document save(DocumentDto documentDto) {
        LOGGER.debug("START SERVICE save byid: {}", documentDto.getId());
        return documentRepository.save(DocumentMapper.toEntity(documentDto));
        //LOGGER.debug("START SERVICE save by  id: {}", documentDto.getId());
    }
}


