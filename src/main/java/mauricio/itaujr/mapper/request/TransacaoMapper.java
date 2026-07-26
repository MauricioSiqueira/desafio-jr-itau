package mauricio.itaujr.mapper.request;

import mauricio.itaujr.dto.TransacaoRequest;
import mauricio.itaujr.entity.Transacao;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {
    public Transacao ToEntity(TransacaoRequest transacaoRequest){

        Transacao transacao = new Transacao();
        transacao.setValor(transacaoRequest.valor());
        transacao.setDataHora(transacaoRequest.dataHora());

        return  transacao;
    }
}
