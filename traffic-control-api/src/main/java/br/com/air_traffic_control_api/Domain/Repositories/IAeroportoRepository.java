package br.com.air_traffic_control_api.Domain.Repositories;

import br.com.air_traffic_control_api.Domain.Entities.AeroportoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAeroportoRepository extends JpaRepository<AeroportoEntity, Long> {
}
