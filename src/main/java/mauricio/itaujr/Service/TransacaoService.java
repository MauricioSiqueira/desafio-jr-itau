package mauricio.itaujr.Service;

import mauricio.itaujr.dto.EstatisticasResponse;
import mauricio.itaujr.dto.TransacaoRequest;
import mauricio.itaujr.entity.Transacao;
import mauricio.itaujr.exceptions.BussinessException;
import mauricio.itaujr.mapper.request.TransacaoMapper;
import mauricio.itaujr.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {
    private TransacaoRepository transacaoRepository;
    private TransacaoMapper mapper;

    public TransacaoService(TransacaoRepository transacaoRepository, TransacaoMapper mapper) {
        this.transacaoRepository = transacaoRepository;
        this.mapper = mapper;
    }

    public void Post(TransacaoRequest request){
        ValidaTransacao(request);
        Transacao transacao = mapper.ToEntity(request);
        transacaoRepository.Post(transacao);
    }

    public void Delete(){
        transacaoRepository.Remove();
    }

    public EstatisticasResponse GetEstatistica(long tempo){
        List<Transacao> transacoes = transacaoRepository.GetEstatisticas(tempo);

        if(transacoes.isEmpty()){
            throw new BussinessException("Nao foram feitas transacoes nos ultimos " + tempo + " segundos.");
        }

        long count = transacoes.size();

        BigDecimal min = null;
        BigDecimal max = null;
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal avg = BigDecimal.ZERO;
        for(Transacao transacao : transacoes){
            BigDecimal valor = transacao.getValor();

            sum = sum.add(valor);

            if(min == null || valor.compareTo(min) < 0){
                min = valor;
            }

            if(max == null || valor.compareTo(max) > 0){
                max = valor;
            }

            avg = sum.divide(BigDecimal.valueOf(count),2, RoundingMode.HALF_UP);
        }
        return new EstatisticasResponse(count,sum,avg,min,max);
    }

    private void ValidaTransacao(TransacaoRequest transacao){
        if(transacao.dataHora().isAfter(OffsetDateTime.now())){
            throw new BussinessException("transação NÃO DEVE acontecer no futuro");
        }

        if(transacao.valor().compareTo(BigDecimal.ZERO) <= 0){
            throw new BussinessException("A transação NÃO DEVE ter valor negativo");
        }
    }
}
