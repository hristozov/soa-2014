package dao;

import com.google.inject.ImplementedBy;

import java.math.BigDecimal;

@ImplementedBy(CurrencyApiFacadeImpl.class)
public interface CurrencyApiFacade {
	BigDecimal getCurrentRate();
}
