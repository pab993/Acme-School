package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Observation;

@Repository
public interface ObservationRepository extends JpaRepository<Observation,Integer>{

	@Query("select o from Observation o where o.child.id = ?1 order by o.dateCreation ASC")
	Collection<Observation> findAllByIdClassAndIdChild(int idChild);

	
	
	
}