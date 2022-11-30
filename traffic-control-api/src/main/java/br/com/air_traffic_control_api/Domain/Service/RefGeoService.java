package br.com.air_traffic_control_api.Domain.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.RefGeoDTO;
import br.com.air_traffic_control_api.Aplicacao.Service.IRefGeoService;
import br.com.air_traffic_control_api.Domain.Entities.RefGeoEntity;
import br.com.air_traffic_control_api.Domain.Repositories.IRefGeoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefGeoService implements IRefGeoService {
    private final IRefGeoRepository repository;

    @Autowired
    public RefGeoService(IRefGeoRepository repository) {
        this.repository = repository;
    }

    @Override
    public long CadastrarNovaRefGeo(RefGeoDTO refGeo) {
        var entity = RefGeoEntity
                .builder()
                .nome(refGeo.getNome())
                .latitude(refGeo.getLatitude())
                .longitude(refGeo.getLongitude())
                .build();

        var ret = repository.save(entity);
        return ret.getId();
    }

    @Override
    public List<RefGeoEntity> listarReferenciasGeograficas() {
        return repository.findAll();
    }

    @Override
    public Optional<RefGeoEntity> ObterRefGeo(long id) {
        return repository.findById(id);
    }
}
