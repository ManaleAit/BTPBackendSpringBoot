package ma.s.gcm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.s.gcm.domain.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
	public Employee findByMatricule(String matricule);
	public Employee findByEmail(String email);
	@Query("SELECT t FROM Employee t WHERE t.responsable.id = ?1")
    List<Employee> findByResponsable(Long id);
	
}


