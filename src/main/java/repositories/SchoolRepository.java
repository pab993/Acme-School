package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.School;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer>{

	@Query("select school from School school where school.isArchive=false and school.isCancel=false")
	Collection<School> findAllActive();
	
	@Query("select school from School school where school.isArchive=false and school.isCancel=false " +
			"and (school.name LIKE ?1 OR school.description LIKE ?1)")
	Collection<School> findAllActiveFilter(String keyword);

	@Query("select school from School school where school.manager.id=?2 " +
			"and (school.name LIKE ?1 OR school.description LIKE ?1)")
	Collection<School> findAllByManager(String keyword, Integer managerId);
	
	
}