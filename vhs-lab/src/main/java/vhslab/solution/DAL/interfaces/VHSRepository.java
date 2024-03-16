package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.solution.entities.model.User;

@Repository

public interface VHSRepository extends JpaRepository<User, Long> {

}