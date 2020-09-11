package ma.s.gcm.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.service.UploadDocumentService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Upload")
public class UploadDocumentResource {

	private final UploadDocumentService uploadDocumentService;

	
	public UploadDocumentResource(UploadDocumentService uploadDocumentService) {
		this.uploadDocumentService = uploadDocumentService;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public boolean add(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id)
			throws BureauEtudeException {
		return uploadDocumentService.saveFile(file, id);

	}

	@DeleteMapping("/{id}")
	public boolean delete(@PathVariable Long id) throws BureauEtudeException, IOException {
		return uploadDocumentService.DeleteFile(id);

	}

	@GetMapping("/{id}")

	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable("id") Long id, HttpServletRequest request) throws IOException {

		String str=uploadDocumentService.getDoc(id);
		Path path = Paths.get(str);
		String mineType = "application/"+str.substring(str.lastIndexOf('.') + 1);
		byte[] data = Files.readAllBytes(path);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString()).contentType(MediaType.parseMediaType(mineType)).contentLength(data.length).body(resource);
	

	}

}
