package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.EventEntity;

@Repository
public interface EventEntityRepository extends JpaRepository<EventEntity,Integer>{

	
	
}