package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vhslab.solution.entities.model.RentalEntity;
import vhslab.solution.entities.model.VhsEntity;

import java.sql.Date;
import java.util.Optional;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, Long> {

    Optional<RentalEntity> findByVhsByVhsId_IdAndDateRented(long vhsId, Date dateRented);
}
