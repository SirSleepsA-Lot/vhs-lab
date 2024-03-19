package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.solution.entities.model.RentalEntity;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, Long> {

}
