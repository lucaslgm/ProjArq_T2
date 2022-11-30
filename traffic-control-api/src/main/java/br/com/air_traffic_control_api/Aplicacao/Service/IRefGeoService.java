package br.com.air_traffic_control_api.Aplicacao.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.RefGeoDTO;
import br.com.air_traffic_control_api.Domain.Entities.RefGeoEntity;

import java.util.List;
import java.util.Optional;

public interface IRefGeoService {
    long CadastrarNovaRefGeo(RefGeoDTO refGeo);
    List<RefGeoEntity> listarReferenciasGeograficas();
    Optional<RefGeoEntity> ObterRefGeo(long id);
}
