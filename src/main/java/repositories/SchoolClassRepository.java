package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SchoolClass;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass,Integer>{

	@Query("select c from SchoolClass c where (c.name like ?1 or c.description like ?1) " +
			"and (c.yearSchool.id = ?2) and c.school.id = ?3")
	Collection<SchoolClass> findAllByClasse(String keyword,
			Integer idYearSchool, Integer idSchool);

	@Query("select c from SchoolClass c where c.isArchive=false and (c.name like ?1 or c.description like ?1) " +
			"and (c.yearSchool.id = ?2) and c.school.id = ?3")
	Collection<SchoolClass> findAllByClasseActive(String keyword, Integer idYearSchool, Integer idSchool);

	@Query("select c from SchoolClass c left join c.teacher t where c.isArchive=false and (c.name like ?1 or c.description like ?1) " +
			"and (c.yearSchool.id = ?2) and t.id=?3")
	Collection<SchoolClass> findAllByTeacherActivas(String keyword,
			Integer idYearSchool, Integer idTeacher);

	@Query("select c from SchoolClass c left join c.petitions p where c.school.isArchive=false and c.school.isCancel=false and c.isArchive=false and (c.name like ?1 or c.description like ?1) " +
			"and (c.yearSchool.id = ?2) and p.child.id=?3 and p.state='ACCEPTE'")
	Collection<SchoolClass> buscarAllByChildActivas(String keyword, Integer idYearSchool, Integer idChild);
	
	@Query("select c from SchoolClass c left join c.petitions p where c.school.isArchive=false and c.school.isCancel=false and c.isArchive=false " +
			"and p.child.id=?1 and p.state='ACCEPTE'")
	Collection<SchoolClass> findAllByChildActivas(Integer idChild);

	
	
}