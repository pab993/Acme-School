package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Petition;

@Repository
public interface PetitionRepository extends JpaRepository<Petition,Integer>{
	//query.a
	@Query("select stddev(coalesce((select count(p) from Petition p where p.schoolClass=s and p.state='ACCEPTE'),0)), avg(coalesce((select count(p) from Petition p where p.schoolClass=s and p.state='ACCEPTE'),0)), max(coalesce((select count(p) from Petition p where p.schoolClass=s and p.state='ACCEPTE'),0)), min(coalesce((select count(p) from Petition p where p.schoolClass=s and p.state='ACCEPTE'),0)) from SchoolClass s where s.yearSchool.isActive=true")
	public Object[] desvianzaMediaMaxMinNinoPorClaseActiva();
	
	//query.c
	@Query("select (select count(pa) from Parent pa where pa=ANY(select DISTINCT(p.child.parent) from Petition p where p.state='ACCEPTE' and p.schoolClass.yearSchool.isActive = true group by p.child.parent))/count(parent)*100 from Parent parent")
	public Double porcentajePadresConNinosRegistradosEnClasesActivas();
}