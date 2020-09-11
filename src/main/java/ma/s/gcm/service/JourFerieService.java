package ma.s.gcm.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.s.gcm.domain.JourFerie;
import ma.s.gcm.domain.TableReferentiel;
import ma.s.gcm.domain.type.TypeJourFerieCode;
import ma.s.gcm.dto.JourFerieDto;
import ma.s.gcm.exception.BureauEtudeException;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.mapper.JourFerieMapper;
import ma.s.gcm.repository.JourFerieRepository;
import ma.s.gcm.repository.TableReferentielRepository;

@Service
@Transactional
public class JourFerieService {
	private static final Logger LOGGER = LoggerFactory.getLogger(JourFerieService.class);

	@Autowired
	private JourFerieRepository jourFerieRepository;

	@Autowired
	private TableReferentielRepository tableReferentielRepository;

	private static int YEAR_JOUR_FERIE;

	public JourFerieDto findById(Long id) throws BureauEtudeException {
		
		LOGGER.debug("START SERVICE find by id {}", id);

		return Optional.ofNullable(jourFerieRepository.findById(id)).map(v -> JourFerieMapper.toDto(v.get()))
				.orElseThrow(
						() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "jour ferie n'existe pas"));

	}

	public List<JourFerieDto> findAll() throws BureauEtudeException {

		LOGGER.debug("START SERVICE find all");
		return Optional.ofNullable(jourFerieRepository.findAll()).map(JourFerieMapper::toDtos).orElseThrow(
				() -> new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, "jours feries not found"));

	}

	public void delete(Long id) throws BureauEtudeException {

		JourFerieDto jourFer = this.findById(id);

		if (jourFer == null) {

			throw new BureauEtudeException(ExceptionCode.API_RESOURCE_NOT_FOUND, " le jour ferie n'existe pas");
		}

		YEAR_JOUR_FERIE = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(jourFer.getDateJour())).getYear();

		if (jourFer.getType() == TypeJourFerieCode.National) {

			throw new BureauEtudeException(ExceptionCode.API_ERROR_DELETE_JOUR_FERIE,
					" Vous avez pas le droit de supprimer  un  jour ferie de type national");
		}
		if (YEAR_JOUR_FERIE < Year.now().getValue()) {

			throw new BureauEtudeException(ExceptionCode.API_ERROR_DELETE_JOUR_FERIE,
					" Vous avez pas le droit de supprimer  ce jour ferie la date est inferieur de la date actuelle");
		}

		LOGGER.debug("START SERVICE delete by id {}", id);
		jourFerieRepository.deleteById(id);
		LOGGER.debug("START SERVICE delete by id {}", id);

	}

	public void save(JourFerieDto jourFerieDto) throws BureauEtudeException {

		if (jourFerieDto.getType() != TypeJourFerieCode.Religieux) {

			throw new BureauEtudeException(ExceptionCode.API_ERROR_TYPE_JOUR_FERIE,
					" Vous avez pas le droit d'ajouter un type national");

		}

		LOGGER.debug("START SERVICE save by id {}, name: {}", jourFerieDto.getId(), jourFerieDto.getLibelle());
		jourFerieRepository.save(JourFerieMapper.toEntity(jourFerieDto));
		LOGGER.debug("START SERVICE save by id {}, name: {}", jourFerieDto.getId(), jourFerieDto.getLibelle());

	}

    //@Scheduled(cron = "${cron.create.timeSheet}")
	public void MajRefTable() {
		for (TableReferentiel obj : tableReferentielRepository.findAll()) {
			JourFerie jourFerie = new JourFerie();
			int year = Year.now().getValue();
			Date date1 = new Date();
			date1 = obj.getDateJour();
			date1.setYear(year - 1900);
			jourFerie.setDateJour(date1);
			jourFerie.setLibelle(obj.getIntituleJourFerie());
			jourFerie.setType(obj.getType());
			jourFerie.setNbJours(obj.getNbJours());
			System.out.println("eeee");
			jourFerieRepository.save(jourFerie);

		}
	}

	// @Scheduled(cron="${cron.create.timeSheet}")
	// @Scheduled(cron = "0/5 * * * * *")
	/*
	 * public void MajRefTable() {
	 * 
	 * if (jourFerieRepository.getMaxDate() != null) { int year1 =
	 * LocalDate.parse(new
	 * SimpleDateFormat("yyyy-MM-dd").format(jourFerieRepository.getMaxDate()))
	 * .getYear();
	 * 
	 * if (java.time.LocalDate.now().getYear() > year1) {
	 * 
	 * for (TableReferentiel obj : tableReferentielRepository.findAll()) {
	 * 
	 * JourFerie jourFerie = new JourFerie(); int year = Year.now().getValue(); Date
	 * date1 = new Date(); date1 = obj.getDateJour(); date1.setYear(year - 1900);
	 * jourFerie.setDateJour(date1);
	 * jourFerie.setLibelle(obj.getIntituleJourFerie());
	 * jourFerie.setType(obj.getType()); jourFerie.setNbJours(obj.getNbJours());
	 * jourFerieRepository.save(jourFerie);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } else {
	 * 
	 * for (TableReferentiel obj : tableReferentielRepository.findAll()) {
	 * 
	 * JourFerie jourFerie = new JourFerie(); int year = Year.now().getValue(); Date
	 * date1 = new Date(); date1 = obj.getDateJour(); date1.setYear(year - 1900);
	 * jourFerie.setDateJour(date1);
	 * jourFerie.setLibelle(obj.getIntituleJourFerie());
	 * jourFerie.setType(obj.getType()); jourFerie.setNbJours(obj.getNbJours());
	 * jourFerieRepository.save(jourFerie);
	 * 
	 * } }
	 * 
	 * }
	 */

}
