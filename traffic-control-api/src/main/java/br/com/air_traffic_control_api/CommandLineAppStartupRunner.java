package br.com.air_traffic_control_api;

import br.com.air_traffic_control_api.Aplicacao.Dtos.AeronaveDTO;
import br.com.air_traffic_control_api.Aplicacao.Dtos.RefGeoDTO;
import br.com.air_traffic_control_api.Aplicacao.Service.*;
import br.com.air_traffic_control_api.Domain.Entities.AeronaveEntity;
import br.com.air_traffic_control_api.Domain.Entities.AeroportoEntity;
import br.com.air_traffic_control_api.Domain.Entities.AeroviaEntity;
import br.com.air_traffic_control_api.Domain.Entities.RotaEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;

@Configuration
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private final IAeroviaService aeroviaService;
    private final IAeronaveService aeronaveService;
    private final IRotaService rotaService;
    private final IAeroportoService aeroportoService;
    private final IRefGeoService refGeoService;
    private final IPlanoDeVooService planoDeVooService;

    public CommandLineAppStartupRunner(IAeroviaService aeroviaService, IAeronaveService aeronaveService, IRotaService rotaService, IAeroportoService aeroportoService, IRefGeoService refGeoService, IPlanoDeVooService planoDeVooService) {
        this.aeroviaService = aeroviaService;
        this.aeronaveService = aeronaveService;
        this.rotaService = rotaService;
        this.aeroportoService = aeroportoService;
        this.refGeoService = refGeoService;
        this.planoDeVooService = planoDeVooService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Startou!");

        //REFGEOs ---------------------------------------------------------------------------------------------
        RefGeoDTO GRU = RefGeoDTO
                .builder()
                .nome("GRU")
                .latitude(-23.432200)
                .longitude(-46.469200)
                .build();
        RefGeoDTO CGH = RefGeoDTO
                .builder()
                .nome("CGH")
                .latitude(-23.626111)
                .longitude(-46.656389)
                .build();
        RefGeoDTO GIG = RefGeoDTO
                .builder()
                .nome("GIG")
                .latitude(-22.808900)
                .longitude(-43.243600)
                .build();
        RefGeoDTO FLN = RefGeoDTO
                .builder()
                .nome("FLN")
                .latitude(-27.670278)
                .longitude(-48.552500)
                .build();
        RefGeoDTO POA = RefGeoDTO
                .builder()
                .nome("POA")
                .latitude(-29.993889)
                .longitude(-51.171111)
                .build();

        GRU.setId(CreateRefGeo(GRU));
        CGH.setId(CreateRefGeo(CGH));
        GIG.setId(CreateRefGeo(GIG));
        FLN.setId(CreateRefGeo(FLN));
        POA.setId(CreateRefGeo(POA));

        //AEROVIAS --------------------------------------------------------------------------------------------
        var GRU_CGH = CreateAerovia(GRU,CGH); //1
        var GRU_POA = CreateAerovia(GRU,POA); //2
        var POA_FLN = CreateAerovia(POA,FLN); //3
        var GIG_GRU = CreateAerovia(GIG,GRU); //4
        var POA_GIG = CreateAerovia(POA,GIG); //5

        OcuparSlots(GRU_CGH);
        OcuparSlots(POA_GIG);
        OcuparSlots(GRU_POA);
        OcuparSlots(POA_FLN);
        OcuparSlots(GIG_GRU);

        //AERONAVES ---------------------------------------------------------------------------------------------
        var A320 = AeronaveDTO
                .builder()
                .autonomia(6100)
                .Prefixo("PR-GUO")
                .VelocidadeCruzeiro(875)
                .build();
        CreateAeronave(A320);

        var B737 = AeronaveDTO
                .builder()
                .autonomia(5765)
                .Prefixo("PR-TAM")
                .VelocidadeCruzeiro(850)
                .build();
        CreateAeronave(B737);

        //ROTAS ---------------------------------------------------------------------------------------------
        var Rota_POAGRU = RotaEntity
                .builder()
                .data(new Date())
                .origem("POA")
                .destino("GRU")
                .build();
        var A5 = aeroviaService.ObterAerovia(5);
        var A4 = aeroviaService.ObterAerovia(4);
        Rota_POAGRU.setConjuntoAerovias(new ArrayList<>());
        Rota_POAGRU.getConjuntoAerovias().add(A5.get());
        Rota_POAGRU.getConjuntoAerovias().add(A4.get());
        CreateRota(Rota_POAGRU);


        var Rota_POAFLN = RotaEntity
                .builder()
                .data(new Date())
                .origem("POA")
                .destino("FLN")
                .build();
        var A3 = aeroviaService.ObterAerovia(3);
        Rota_POAFLN.setConjuntoAerovias(new ArrayList<>());
        Rota_POAFLN.getConjuntoAerovias().add(A3.get());
        CreateRota(Rota_POAFLN);


        var Rota_GRUFLN = RotaEntity
                .builder()
                .data(new Date())
                .origem("GRU")
                .destino("FLN")
                .build();
        var A2 = aeroviaService.ObterAerovia(2);
        Rota_GRUFLN.setConjuntoAerovias(new ArrayList<>());
        Rota_GRUFLN.getConjuntoAerovias().add(A2.get());
        Rota_GRUFLN.getConjuntoAerovias().add(A3.get());
        CreateRota(Rota_GRUFLN);


        var Rota_GIGPOA = RotaEntity
                .builder()
                .data(new Date())
                .origem("GIG")
                .destino("POA")
                .build();
        Rota_GIGPOA.setConjuntoAerovias(new ArrayList<>());
        Rota_GIGPOA.getConjuntoAerovias().add(A4.get());
        Rota_GIGPOA.getConjuntoAerovias().add(A2.get());
        CreateRota(Rota_GIGPOA);


        var Rota_GIGCGH = RotaEntity
                .builder()
                .data(new Date())
                .origem("GIG")
                .destino("CGH")
                .build();
        var A1 = aeroviaService.ObterAerovia(1);
        Rota_GIGCGH.setConjuntoAerovias(new ArrayList<>());
        Rota_GIGCGH.getConjuntoAerovias().add(A4.get());
        Rota_GIGCGH.getConjuntoAerovias().add(A1.get());
        CreateRota(Rota_GIGCGH);

        //AEROPORTOS ---------------------------------------------------------------------------------------------
        var RGRU = refGeoService.ObterRefGeo(1);
        var RCGH = refGeoService.ObterRefGeo(2);
        var RGIG = refGeoService.ObterRefGeo(3);
        var RFLN = refGeoService.ObterRefGeo(4);
        var RPOA = refGeoService.ObterRefGeo(5);

        var AGRU = AeroportoEntity
                .builder()
                .nome("AI Governador André Franco Montoro")
                .iata("GRU")
                .localizacao(RGRU.get())
                .build();
        var ACGH = AeroportoEntity
                .builder()
                .nome("AI Deputado Freitas Nobre")
                .iata("CGH")
                .localizacao(RCGH.get())
                .build();
        var AGIG = AeroportoEntity
                .builder()
                .nome("AI Tom Jobim")
                .iata("GIG")
                .localizacao(RGIG.get())
                .build();
        var AFLN = AeroportoEntity
                .builder()
                .nome("AI Hercílio Luz")
                .iata("FLN")
                .localizacao(RFLN.get())
                .build();
        var APOA = AeroportoEntity
                .builder()
                .nome("AI Salgado Filho")
                .iata("POA")
                .localizacao(RPOA.get())
                .build();

        CreateAeroporto(AGRU);
        CreateAeroporto(ACGH);
        CreateAeroporto(AGIG);
        CreateAeroporto(AFLN);
        CreateAeroporto(APOA);

    }

    private AeroviaEntity CreateAerovia(RefGeoDTO c1, RefGeoDTO c2){
        return aeroviaService.CadastrarNovaAerovia(c1,c2);
    }

    private long CreateRefGeo(RefGeoDTO c){
        return refGeoService.CadastrarNovaRefGeo(c);
    }

    private AeronaveEntity CreateAeronave(AeronaveDTO aeronave){
        return aeronaveService.CadastrarAeronave(aeronave);
    }

    private void CreateRota(RotaEntity rota){
        rotaService.CadastrarNovaRota(rota);
    }

    private void CreateAeroporto(AeroportoEntity aeroporto){
        aeroportoService.CadastrarNovoAeroporto(aeroporto);
    }

    private void OcuparSlots(AeroviaEntity aerovia){
        for (int i = 0; i< 30; i++){
            aerovia.getSlots().get(i).setDisponivel(false);
        }
        for (int i = 100; i< 190; i++){
                aerovia.getSlots().get(i).setDisponivel(false);
        }

        aeroviaService.AtualizarAerovia(aerovia);
    }
}
