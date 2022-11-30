package br.com.air_traffic_control_api.Domain.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.AeronaveDTO;
import br.com.air_traffic_control_api.Aplicacao.Service.IAeronaveService;
import br.com.air_traffic_control_api.Domain.Entities.AeronaveEntity;
import br.com.air_traffic_control_api.Domain.Repositories.IAeronaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AeronaveService implements IAeronaveService {
    private final IAeronaveRepository repository;

    @Autowired
    public AeronaveService(IAeronaveRepository repository) {
        this.repository = repository;
    }

    public AeronaveEntity CadastrarAeronave(AeronaveDTO aeronave){

        var entity = AeronaveEntity
                .builder()
                .Prefixo(aeronave.getPrefixo())
                .autonomia(aeronave.getAutonomia())
                .VelocidadeCruzeiro(aeronave.getVelocidadeCruzeiro())
                .build();

        return repository.save(entity);
    }
}
