package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer>{

	@Query("select t from Teacher t where t.school.id=?1 and t.isArchive=false and (t !=ANY(select t2 from Teacher t2 left join t2.schoolClass s where s.id=?2) or (select t2 from Teacher t2 left join t2.schoolClass s where s.id=?2)=null)")
	Collection<Teacher> buscarProfesoresDeUnColegioNoAsignadosAUnaClase(
			Integer idSchool, Integer idSchoolClass);

}