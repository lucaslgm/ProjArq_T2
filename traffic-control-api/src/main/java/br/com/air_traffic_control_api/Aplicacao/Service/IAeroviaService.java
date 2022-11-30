package br.com.air_traffic_control_api.Aplicacao.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.RefGeoDTO;
import br.com.air_traffic_control_api.Domain.Entities.AeroviaEntity;
import br.com.air_traffic_control_api.Domain.Entities.SlotEntity;

import java.util.List;
import java.util.Optional;

public interface IAeroviaService {
    AeroviaEntity CadastrarNovaAerovia(RefGeoDTO c1, RefGeoDTO c2);
    Optional<AeroviaEntity> ObterAerovia(long id);
    List<AeroviaEntity> findAll();

    List<SlotEntity> ListarSlotsLivres(long aerovia, int partida, double velocidade);

    String RelatorioOcupacaoAeroviaporData(String data, long aerovia);

    AeroviaEntity AtualizarAerovia(AeroviaEntity aerovia);
}
