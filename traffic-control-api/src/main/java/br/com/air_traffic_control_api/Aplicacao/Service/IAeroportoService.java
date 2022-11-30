package br.com.air_traffic_control_api.Aplicacao.Service;

import br.com.air_traffic_control_api.Domain.Entities.AeroportoEntity;

public interface IAeroportoService {
    public AeroportoEntity CadastrarNovoAeroporto(AeroportoEntity aeroporto);
}
