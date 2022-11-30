package br.com.air_traffic_control_api.Domain.Service;

import br.com.air_traffic_control_api.Aplicacao.Service.IAeroportoService;
import br.com.air_traffic_control_api.Domain.Entities.AeroportoEntity;
import br.com.air_traffic_control_api.Domain.Repositories.IAeroportoRepository;
import org.springframework.stereotype.Service;

@Service
public class AeroportoService implements IAeroportoService {
    private  final IAeroportoRepository repository;

    public AeroportoService(IAeroportoRepository repository) {
        this.repository = repository;
    }

    @Override
    public AeroportoEntity CadastrarNovoAeroporto(AeroportoEntity aeroporto) {
        return repository.save(aeroporto);
    }
}
