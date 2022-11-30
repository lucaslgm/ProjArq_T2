package br.com.air_traffic_control_api.Aplicacao.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.AeroportoDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.FaixaAeroviaDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.RotaDTO;
import br.com.air_traffic_control_api.Domain.Entities.PlanoDeVooEntity;

import java.util.List;
public interface IPlanoDeVooService {

    List<RotaDTO> ListarRotasEntreAeroportos(AeroportoDTO origem, AeroportoDTO destino);
    List<FaixaAeroviaDTO> ListarNiveisDeVooLivres(int horaPartida, int velocidadeCruzeiro, AeroportoDTO origem, AeroportoDTO destino);
    List<PlanoDeVooEntity> ListarPlanosDeVoo();
    Boolean LiberarPlanoDeVoo(PlanoDeVooDTO planoDeVoo);
    Boolean CancelarPlanoDeVoo(long planoDeVoo);
    String verificaPlanoDeVoo(PlanoDeVooDTO planoDeVoo);
    void cadastraPlanoDeVoo(PlanoDeVooDTO planoDeVoo);
}
