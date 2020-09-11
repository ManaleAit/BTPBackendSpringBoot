package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;

import ma.s.gcm.dto.DocumentDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.mapper.DocumentMapper;
import ma.s.gcm.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/document")
@PreAuthorize("isAuthenticated()")
public class DocumentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(FonctionResource.class);

    private final DocumentService documentService;

    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public DocumentDto add(@RequestBody DocumentDto documentDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE add Document by Type : {}", documentDto.getTypeDocument());
        return DocumentMapper.toDto(documentService.save(documentDto));
        //LOGGER.debug("END RESOURCE add Document by id : {}, type: {} is ok", documentDto.getId(), documentDto.getTypeDocument());
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody DocumentDto documentDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update Document  by type : {}, id: {}", documentDto.getTypeDocument(), documentDto.getId());
        documentService.save(documentDto);
        LOGGER.debug("END RESOURCE update Document  by id : {}, type: {} is ok", documentDto.getId(), documentDto.getTypeDocument());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public DocumentDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find Document by id : {}", id);
        DocumentDto documentDto = documentService.findById(id);
        LOGGER.debug("END RESOURCE find Document by id : {}, Type :{}", id, documentDto.getTypeDocument());
        return documentDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<DocumentDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find Documents");
        List<DocumentDto> documentDtos = documentService.findAll();
        LOGGER.debug("END RESOURCE find all Documents, size: {}", documentDtos.size());
        return documentDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete Document by id : {}", id);
        documentService.delete(id);
        LOGGER.debug("END RESOURCE delete Document by id : {}, is ok", id);
    }
}
