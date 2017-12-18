package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child,Integer>{

	@Query("select c from Child c where c.parent.id=?2 and 0=(select COUNT(p) from Petition p where p.child=c and p.schoolClass.id=?1 and p.state!='CANCEL')")
	List<Child> buscarHijoSinSolicitudEnUnaClase(Integer idSchoolClass, Integer idTeacher);

	
	
}