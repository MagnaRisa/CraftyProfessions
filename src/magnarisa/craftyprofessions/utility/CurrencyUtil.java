package magnarisa.craftyprofessions.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyUtil
{
    private static final Integer SCALE = 2;

    public static BigDecimal formatDouble (Double toFormat)
    {
        BigDecimal currency = new BigDecimal (toFormat);

        return currency.setScale (SCALE, RoundingMode.DOWN);
    }
}
