package br.com.air_traffic_control_api.Domain.Service;

import br.com.air_traffic_control_api.Aplicacao.Dtos.AeroportoDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.FaixaAeroviaDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.RotaDTO;
import br.com.air_traffic_control_api.Aplicacao.Service.IPlanoDeVooService;
import br.com.air_traffic_control_api.Domain.Entities.AeroviaEntity;
import br.com.air_traffic_control_api.Domain.Entities.PlanoDeVooEntity;
import br.com.air_traffic_control_api.Domain.Repositories.IPlanoDeVooRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.List;

@Service
public class PlanoDeVooService implements IPlanoDeVooService {
    private final IPlanoDeVooRepository repository;

    @Autowired
    public PlanoDeVooService(IPlanoDeVooRepository repository) {
        this.repository = repository;
    }
    
    public List<RotaDTO> ListarRotasEntreAeroportos(AeroportoDTO origem, AeroportoDTO destino) {
        return null;
    }

    public List<FaixaAeroviaDTO> ListarNiveisDeVooLivres(int horaPartida, int velocidadeCruzeiro, AeroportoDTO origem, AeroportoDTO destino) {
        return null;
    }

    public List<PlanoDeVooEntity> ListarPlanosDeVoo() {
        return repository.findAll();
    }

    public Boolean LiberarPlanoDeVoo(PlanoDeVooDTO planoDeVoo) {
        if(verificaPlanoDeVoo(planoDeVoo).equalsIgnoreCase("Ok")){
            cadastraPlanoDeVoo(planoDeVoo);
            return true;
        }
        return false;
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
    public String verificaPlanoDeVoo(PlanoDeVooDTO planoDeVoo) {
        int horarioAtual = planoDeVoo.getHorario();

        for(AeroviaEntity aerovia : planoDeVoo.getRota().getConjuntoAerovias()){
            int duracao = (int) Math.ceil(aerovia.getDistancia()/planoDeVoo.getVelocidade());
            for(int i=0; i<duracao; i++){
                if(!aerovia.getSlotByAltiudeAndHorario(planoDeVoo.getAltitude(), horarioAtual).isDisponivel()){
                    return "Aerovia " + aerovia.getNome() +
                     " não disponível para data " + planoDeVoo.getData() +
                     " no horario " + horarioAtual + 
                     " e altitude " + planoDeVoo.getAltitude();
                }
                horarioAtual++;
            }
        }
        return "Ok";
    }

    @Override
    public void cadastraPlanoDeVoo(PlanoDeVooDTO planoDeVoo) {
        PlanoDeVooEntity entity = PlanoDeVooEntity
            .builder()
            .data(planoDeVoo.getData().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
            .altitude(planoDeVoo.getAltitude())
            .numeroVoo(planoDeVoo.getNumeroVoo())
            .velocidade(planoDeVoo.getVelocidade())
            .status(planoDeVoo.getStatus())
            .rotaEntity(planoDeVoo.getRota())
            .build();
            
        repository.save(entity);
    }

    
}
