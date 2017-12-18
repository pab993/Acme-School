
package repositories.repositories2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface Administrator2Repository extends JpaRepository<Admin, Integer> {

//	@Query("select a from Administrator a where a.userAccount.id = ?1")
//	Admin findByUserAccountId(int userAccountId);
}
