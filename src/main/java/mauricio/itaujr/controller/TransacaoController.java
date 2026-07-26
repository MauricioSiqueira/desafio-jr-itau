package mauricio.itaujr.controller;

import jakarta.validation.Valid;
import mauricio.itaujr.Service.TransacaoService;
import mauricio.itaujr.dto.EstatisticasResponse;
import mauricio.itaujr.dto.TransacaoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transacao")
public class TransacaoController {
    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping("")
    public ResponseEntity Post(
            @Valid @RequestBody TransacaoRequest request
    ){
        transacaoService.Post(request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity Delete(){
        transacaoService.Delete();
        return ResponseEntity.ok().build();
    }

    @GetMapping("estatisticas")
    public ResponseEntity<EstatisticasResponse> GetEstatistica(@RequestParam long tempo){
        return ResponseEntity.ok(transacaoService.GetEstatistica(tempo));
    }
}
