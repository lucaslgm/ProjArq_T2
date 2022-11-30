package br.com.air_traffic_control_api.Domain.Repositories;

import br.com.air_traffic_control_api.Domain.Entities.AeroviaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IAeroviaRepository extends JpaRepository<AeroviaEntity, Long> {
    List<AeroviaEntity> findAll();
}
