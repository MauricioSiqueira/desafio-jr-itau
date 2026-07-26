package mauricio.itaujr.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record TransacaoRequest(
        @NotNull(message = "Informar o valor da transacao.")
        BigDecimal valor,
        @NotNull(message = "Informar a data e hora da transacao")
        OffsetDateTime dataHora
){}
