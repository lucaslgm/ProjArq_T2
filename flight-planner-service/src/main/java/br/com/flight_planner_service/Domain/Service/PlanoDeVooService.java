package br.com.flight_planner_service.Domain.Service;

import br.com.flight_planner_service.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.flight_planner_service.Aplicacao.Service.IPlanoDeVooService;
import br.com.flight_planner_service.Domain.Entities.PlanoDeVooEntity;
import br.com.flight_planner_service.Domain.Repositories.IPlanoDeVooRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PlanoDeVooService implements IPlanoDeVooService {
    private final IPlanoDeVooRepository repository;

    @Autowired
    public PlanoDeVooService(IPlanoDeVooRepository repository) {
        this.repository = repository;
    }

    public List<PlanoDeVooEntity> ListarPlanosDeVoo() {
        return repository.findAll();
    }

    public Boolean CancelarPlanoDeVoo(long planoDeVoo) {
        if(repository.existsById(planoDeVoo)){
            PlanoDeVooEntity updatedPlanoDeVoo = repository.findById(planoDeVoo).get();
            updatedPlanoDeVoo.setStatus("Cancelado");
            repository.save(updatedPlanoDeVoo);
            return true;
        }
        return false;
    }


    @Override
    public Long CadastraPlanoDeVoo(PlanoDeVooDTO planoDeVoo) {
        PlanoDeVooEntity entity = PlanoDeVooEntity
            .builder()
            .data(planoDeVoo.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
            .altitude(planoDeVoo.getAltitude())
            .numeroVoo(planoDeVoo.getNumeroVoo())
            .velocidade(planoDeVoo.getVelocidade())
            .status(planoDeVoo.getStatus())
            .rota(planoDeVoo.getRota())
            .build();
            
        var ret = repository.save(entity);
        return ret.getId();
    }

    @Override
    public Optional<PlanoDeVooEntity> ObterPlanoDeVoo(long id) {
        var ret = repository.findById(id);
        return ret;
    }


}
