package ma.s.gcm.mapper;

import ma.s.gcm.domain.Employee;
import ma.s.gcm.dto.EmployeeDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeMapper {
	private EmployeeMapper() {
	}

	public static EmployeeDto toDto(Employee employee) {
		if (employee == null) {
			return null;
		} else {
			return EmployeeDto.builder().id(employee.getId()).matricule(employee.getMatricule()).nom(employee.getNom())
					.prenom(employee.getPrenom()).cnss(employee.getCnss()).dateNaissance(employee.getDateNaissance())
					.dateRecrutement(employee.getDateRecrutement())
					.fonction(FonctionMapper.toDto(employee.getFonction()))
					.conges(CongeMapper.toDtos(employee.getConges())).cin(employee.getCin())
					.responsable(EmployeeMapper.toDto(employee.getResponsable()))
					.email(employee.getEmail()).build();
		}
	}

	public static List<EmployeeDto> toDtos(List<Employee> employees) {

		return employees.stream().filter(Objects::nonNull).map(EmployeeMapper::toDto).collect(Collectors.toList());

	}

	public static Employee toEntity(EmployeeDto employeeDto) {
		if (employeeDto == null) {
			return null;
		}
		return Employee.builder().id(employeeDto.getId()).matricule(employeeDto.getMatricule())
				.nom(employeeDto.getNom()).prenom(employeeDto.getPrenom()).cnss(employeeDto.getCnss())
				.dateNaissance(employeeDto.getDateNaissance()).dateRecrutement(employeeDto.getDateRecrutement())
				.fonction(FonctionMapper.toEntity(employeeDto.getFonction()))
				.conges(CongeMapper.toEntities(employeeDto.getConges())).cin(employeeDto.getCin())
				.responsable(EmployeeMapper.toEntity(employeeDto.getResponsable())).email(employeeDto.getEmail()).build();
	}
}