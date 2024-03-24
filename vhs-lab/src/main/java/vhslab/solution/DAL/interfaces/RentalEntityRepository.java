package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vhslab.solution.entities.model.RentalEntity;

import java.sql.Date;
import java.util.List;

@Repository
public interface RentalEntityRepository extends JpaRepository<RentalEntity, Long> {

    @Query("SELECT COUNT(r) > 0 FROM RentalEntity r " +
            "WHERE r.vhsByVhsId.id = :vhsId " +
            "AND (r.dateReturned IS NULL OR :dateRented BETWEEN r.dateDue AND r.dateReturned)")
    Boolean existsRentalForVhsOnDate(long vhsId, Date dateRented);
    List<RentalEntity> findAll();
}
