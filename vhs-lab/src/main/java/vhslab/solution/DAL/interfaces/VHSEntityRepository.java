package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vhslab.solution.entities.model.VhsEntity;

@Repository

public interface VHSEntityRepository extends JpaRepository<VhsEntity, Long> {

}