package br.com.flight_planner_service.Aplicacao.Service;

import br.com.flight_planner_service.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.flight_planner_service.Domain.Entities.PlanoDeVooEntity;

import java.util.List;
import java.util.Optional;

public interface IPlanoDeVooService {
    List<PlanoDeVooEntity> ListarPlanosDeVoo();
    Boolean CancelarPlanoDeVoo(long planoDeVoo);
    Long CadastraPlanoDeVoo(PlanoDeVooDTO planoDeVoo);

    Optional<PlanoDeVooEntity> ObterPlanoDeVoo(long id);
}
