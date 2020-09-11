package ma.s.gcm.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import ma.s.gcm.dto.TypeDocumentDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.TypeDocumentService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/typeDocument")
@PreAuthorize("isAuthenticated()")
public class TypeDocumentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(TypeDocumentResource.class);

    private final TypeDocumentService typeDocumentService;

    public TypeDocumentResource(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public Long add(@RequestBody TypeDocumentDto typeDocumentDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE add type de document by name : {}", typeDocumentDto.getLibelle());
        return typeDocumentService.save(typeDocumentDto).getId();
        //LOGGER.debug("END RESOURCE add type de document by id : {}, name: {} is ok", typeDocumentDto.getId(), typeDocumentDto.getLibelle());
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody TypeDocumentDto typeDocumentDto) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE update type de document by name : {}, id: {}", typeDocumentDto.getLibelle(), typeDocumentDto.getId());
        typeDocumentService.save(typeDocumentDto);
        LOGGER.debug("END RESOURCE update type de document by id : {}, name: {} is ok", typeDocumentDto.getId(), typeDocumentDto.getLibelle());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public TypeDocumentDto get(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE find type de document by id : {}", id);
        TypeDocumentDto TypeDocumentDto = typeDocumentService.findById(id);
        LOGGER.debug("END RESOURCE find type de document by id : {}, name :{}", id, TypeDocumentDto.getLibelle());
        return TypeDocumentDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<TypeDocumentDto> getAll() throws BureauEtudeException {
        LOGGER.debug("START RESOURCE all find types de documents");
        List<TypeDocumentDto> typeDocumentDtos = typeDocumentService.findAll();
        LOGGER.debug("END RESOURCE find all types de documents, size: {}", typeDocumentDtos.size());
        return typeDocumentDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws BureauEtudeException {
        LOGGER.debug("START RESOURCE delete type de document by id : {}", id);
        typeDocumentService.delete(id);
        LOGGER.debug("END RESOURCE delete type de document by id : {}, is ok", id);
    }
}
