package br.com.air_traffic_control_api.Aplicacao.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.AeronaveDTO;
import br.com.air_traffic_control_api.Domain.Entities.AeronaveEntity;

public interface IAeronaveService {
    AeronaveEntity CadastrarAeronave(AeronaveDTO aeronave);
}
