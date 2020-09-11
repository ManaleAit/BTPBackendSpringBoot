package ma.s.gcm.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ma.s.gcm.domain.Document;
import ma.s.gcm.repository.DocumentRepository;
import ma.s.gcm.repository.TypeDocumentRepository;

@Service
@Transactional
public class UploadDocumentService {

	private DocumentRepository documentRepository;

	private TypeDocumentRepository TypedocumentRepository;

	@Autowired
	public UploadDocumentService(DocumentRepository documentRepository, TypeDocumentRepository TypedocumentRepository) {
		this.documentRepository = documentRepository;
		this.TypedocumentRepository = TypedocumentRepository;
	}

	@Value("${upload.document.path}")
	private String path;

	public boolean saveFile(MultipartFile file, Long id) {

		InputStream inputStream = null;
		OutputStream outputStream = null;
		// MultipartFile file = uploadedFile.getFile();
		String extension = file.getOriginalFilename().split("\\.")[1];
		// String fileName = file.getOriginalFilename();
		File newFile = new File(path + id + "." + extension);

		try {
			inputStream = file.getInputStream();

			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			inputStream.close();
			outputStream.close();
			Document doc = documentRepository.findById(id).get();
			doc.setPath(path + id + "." + extension);
			documentRepository.save(doc);
			return true;
		} catch (IOException e) {
			e.printStackTrace();

		}

		return false;

	}

	public boolean DeleteFile(Long id) throws IOException {

		if (documentRepository.findById(id) != null) {
			String str = documentRepository.findById(id).get().getPath();

			String name = id + "." + str.substring(str.lastIndexOf('.') + 1);
			try {
				Files.delete(Paths.get(path + name));
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return false;
		}
		return false;

	}

	public String getDoc(Long id) {

		Document doc = documentRepository.findById(id).get();
		if (doc != null) {
			return doc.getPath();
		}

		return null;

	}
}
