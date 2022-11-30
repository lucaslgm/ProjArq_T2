package br.com.air_traffic_control_api.Domain.Service;

import br.com.air_traffic_control_api.Aplicacao.Service.IRotaService;
import br.com.air_traffic_control_api.Domain.Entities.RotaEntity;
import br.com.air_traffic_control_api.Domain.Repositories.IRotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotaService implements IRotaService {
    private final IRotaRepository repository;

    @Autowired
    public RotaService(IRotaRepository repository) {
        this.repository = repository;
    }

    @Override
    public RotaEntity CadastrarNovaRota(RotaEntity rota) {
        return repository.save(rota);
    }

    @Override
    public List<RotaEntity> ConsultarRotasEntreDoisAeroportos(String origem, String destino) {
        return repository.findAllByOrigemAndDestino(origem, destino);
    }

    @Override
    public List<RotaEntity> ListarRotas() {
        return repository.findAll();
    }

    public RotaEntity findRotaById(long id){
        return repository.findById(id).get();
    }
}
