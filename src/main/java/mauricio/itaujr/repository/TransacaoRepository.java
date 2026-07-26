package mauricio.itaujr.repository;

import mauricio.itaujr.entity.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransacaoRepository {

    private List<Transacao> transacoes = new ArrayList<>();

    public void Post(Transacao transacao){
        transacoes.add(transacao);
    }

    public void Remove(){
        transacoes.clear();
    }

    public List<Transacao> GetEstatisticas(long tempo){
        return transacoes.stream().filter(t ->
            t.getDataHora().isAfter(OffsetDateTime.now().minusSeconds(tempo))
        ).toList();
    }
}
