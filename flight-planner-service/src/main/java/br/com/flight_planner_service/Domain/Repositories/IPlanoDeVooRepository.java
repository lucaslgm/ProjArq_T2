package br.com.flight_planner_service.Domain.Repositories;

import br.com.flight_planner_service.Domain.Entities.PlanoDeVooEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanoDeVooRepository extends JpaRepository<PlanoDeVooEntity, Long> {
}
