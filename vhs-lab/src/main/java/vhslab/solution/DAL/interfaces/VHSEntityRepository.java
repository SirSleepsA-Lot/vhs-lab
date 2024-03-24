package vhslab.solution.DAL.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vhslab.solution.entities.model.VhsEntity;

import java.util.List;

@Repository

public interface VHSEntityRepository extends JpaRepository<VhsEntity, Long> {
    List<VhsEntity> findAll();
}