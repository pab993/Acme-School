package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.YearSchool;

@Repository
public interface YearSchoolRepository extends JpaRepository<YearSchool,Integer>{
	
	@Query("select y from YearSchool y where y.isActive=true")
	public YearSchool getYearSchoolIsActiveTrue();
	
}