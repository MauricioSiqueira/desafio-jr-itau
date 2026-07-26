package mauricio.itaujr.dto;

import java.math.BigDecimal;

public record EstatisticasResponse(
        long count,
        BigDecimal sum,
        BigDecimal avg,
        BigDecimal min,
        BigDecimal max
) { }
