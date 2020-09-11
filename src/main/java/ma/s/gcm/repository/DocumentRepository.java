package ma.s.gcm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Document;
@Repository
public interface DocumentRepository  extends JpaRepository<Document, Long> {

}


