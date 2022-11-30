package br.com.air_traffic_control_api.Api.Controllers;

import br.com.air_traffic_control_api.Aplicacao.Dtos.*;
import br.com.air_traffic_control_api.Aplicacao.Service.IAeroviaService;
import br.com.air_traffic_control_api.Aplicacao.Service.IPlanoDeVooService;
import br.com.air_traffic_control_api.Aplicacao.Service.IRotaService;
import br.com.air_traffic_control_api.Aplicacao.TrafficControlApiProxy;
import br.com.air_traffic_control_api.Domain.Entities.PlanoDeVooEntity;
import br.com.air_traffic_control_api.Domain.Entities.RotaEntity;
import br.com.air_traffic_control_api.Domain.Entities.SlotEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/traffic-control")
public class TrafegoAereoController {
    private final IRotaService rotaService;
    private final IAeroviaService aeroviaService;
    private final IPlanoDeVooService planoDeVooService;

    Logger logger = LoggerFactory.getLogger(TrafegoAereoController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TrafficControlApiProxy proxy;

    @Autowired
    public TrafegoAereoController(IRotaService rotaService, IAeroviaService aeroviaService, IPlanoDeVooService planoDeVooService) {
        this.rotaService = rotaService;
        this.aeroviaService = aeroviaService;
        this.planoDeVooService = planoDeVooService;
    }

    @GetMapping("/rotas/listarRotasEntreAeroportos")
    List<RotaEntity> ListarRotasEntreAeroportos(@RequestParam("origem") String origem, @RequestParam("destino") String destino){
        return  rotaService.ConsultarRotasEntreDoisAeroportos(origem, destino);
    }

    @GetMapping("/rotas/listarSlotsLivres")
    List<SlotEntity> ListarSlotsLivres(@RequestParam("partida") int horaPartida,
                                       @RequestParam("velocidade") int velocidadeCruzeiro,
                                       @RequestParam("aerovia") long aerovia){

        return aeroviaService.ListarSlotsLivres(aerovia,horaPartida,velocidadeCruzeiro);
    };

    @GetMapping("/listar-planos-de-voo")
    List<PlanoDeVooEntity> ListarPlanosDeVoo(){
        var answer = proxy.retriveFindAllPlansAnswer();
        return answer;
    }

    @GetMapping("/rotas/listarTodasRotas")
    List<RotaEntity> ListarRotas(){
        return rotaService.ListarRotas();
    }

    @PostMapping("/LiberarPlanoDeVoo")
    Boolean LiberarPlanoDeVoo(@RequestParam("data") String data, @RequestParam("horario") int horario,
                            @RequestParam("numeroVoo") int nroVoo, @RequestParam("rotaId") long rotaId,
                            @RequestParam("velocidade") int velocidade, @RequestParam("altitude") int altitude) throws ParseException{

        var rota = rotaService.findRotaById(rotaId);

        if (rota != null) {
            System.out.println("ROTA: " + rota.getId());
            PlanoDeVooDTO planoDeVoo = PlanoDeVooDTO
                    .builder()
                    .data(new SimpleDateFormat("yyyy-MM-dd").parse(data))
                    .horario(horario)
                    .numeroVoo(nroVoo)
                    .rota(rota)
                    .velocidade(velocidade)
                    .altitude(altitude)
                    .status("Liberado")
                    .build();

            return planoDeVooService.LiberarPlanoDeVoo(planoDeVoo);
        }
        return false;
    }

    @PostMapping("/cadastrar-plano-de-voo")
    Long CadastrarPlanoDeVoo(
            @RequestParam("data") String data,
            @RequestParam("horario") int horario,
            @RequestParam("numeroVoo") int nroVoo,
            @RequestParam("rotaId") long rotaId,
            @RequestParam("velocidade") int velocidade,
            @RequestParam("altitude") int altitude) throws ParseException{

        var rota = rotaService.findRotaById(rotaId);

        if(rota != null) {
            NovoPlanoDeVooDTO planoDeVoo = NovoPlanoDeVooDTO
                    .builder()
                    .data(new SimpleDateFormat("yyyy-MM-dd").parse(data))
                    .horario(horario)
                    .numeroVoo(nroVoo)
                    .rota(rota.getId())
                    .velocidade(velocidade)
                    .altitude(altitude)
                    .build();

            Long answer = proxy.retriveInsertPlanAnswer(planoDeVoo);
            return answer;
        }

        return null;

//        return planoDeVooService.LiberarPlanoDeVoo(planoDeVoo);


    }

    @PutMapping("/cancelarPlanoDeVoo")
    Boolean CancelarPlanoDeVoo(@RequestParam("planoDeVooID") long planoDeVoo){
//        return planoDeVooService.CancelarPlanoDeVoo(planoDeVoo);
        System.out.println("PV" + planoDeVoo);
        boolean answer = proxy.retriveCancelPlanAnswer(planoDeVoo);
        return answer;
    }

    @GetMapping("/verificarPlanoDeVoo")
    String VerificaPlanoDeVoo(
            @RequestParam("data") String data,
            @RequestParam("horario") int horario,
            @RequestParam("numeroVoo") int nroVoo,
            @RequestParam("rotaId") long rotaId,
            @RequestParam("velocidade") int velocidade,
            @RequestParam("altitude") int altitude) throws ParseException{

        var rota = rotaService.findRotaById(rotaId);

        if(rota != null) {
            PlanoDeVooDTO planoDeVoo = PlanoDeVooDTO
                    .builder()
                    .data(new SimpleDateFormat("yyyy-MM-dd").parse(data))
                    .horario(horario)
                    .numeroVoo(nroVoo)
                    .rota(rota)
                    .velocidade(velocidade)
                    .altitude(altitude)
                    .build();

            return planoDeVooService.verificaPlanoDeVoo(planoDeVoo);
        }
        return null;
    }

    @GetMapping("/rotas/relatorioOcupacao")
    String RelatorioOcupacaoAeroviaporData(@RequestParam("data") String data,
                                           @RequestParam("aerovia")long aerovia){
        return aeroviaService.RelatorioOcupacaoAeroviaporData(data, aerovia);
    };

    @GetMapping("/call")
    void Call(String fila, Object msg){
//        String answer = proxy.retrieveExchangeValue();
//        return answer;
//        rabbitTemplate.convertAndSend

        rabbitTemplate.convertAndSend("zipkin", 10);
    }
}
