package br.com.flight_planner_service.Api.Controllers;

import br.com.flight_planner_service.Aplicacao.Dtos.PlanoDeVooDTO;
import br.com.flight_planner_service.Aplicacao.Service.IPlanoDeVooService;
import br.com.flight_planner_service.Domain.Entities.PlanoDeVooEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/flight-planner")
public class Controller {
    private final IPlanoDeVooService planoDeVooService;

    Logger logger = LoggerFactory.getLogger(Controller.class);

    public Controller(IPlanoDeVooService planoDeVooService) {
        this.planoDeVooService = planoDeVooService;
    }

    @GetMapping("/answer")
    public String Answer(){
        return "OK";
    }

    @PostMapping("/insert-flight-plan")
    Long InsertFlightPlan(@RequestBody PlanoDeVooDTO plano) {

        plano.setStatus("Cadastrado");
        Long id = planoDeVooService.CadastraPlanoDeVoo(plano);
        System.out.println("Plano: " + plano);
        return id;
    }

    @PutMapping("/cancel-flight-plan")
    Boolean CancelFlightPlan(@RequestParam(name= "planoDeVooID") long id){
        var cancelado = planoDeVooService.CancelarPlanoDeVoo(id);
        System.out.println("PV: " + id);
        return cancelado;
    }

    @GetMapping("/list-flight-plans")
    List<PlanoDeVooEntity> ListAllFlightPlans(){
        return planoDeVooService.ListarPlanosDeVoo();
    }
}
