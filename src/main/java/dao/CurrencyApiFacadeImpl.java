package dao;

import com.google.inject.Singleton;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

@Singleton
public class CurrencyApiFacadeImpl implements CurrencyApiFacade {
	private BigDecimal currentRate;
	private Long lastContact = 0l;
	private Long refreshInterval = 10 * 60 * 1000l;

	@Override
	public BigDecimal getCurrentRate() {
		if (lastContact < new Date().getTime() - refreshInterval || currentRate == null) {
			try {
				URL uri = new URL("http://rate-exchange.appspot.com/currency?from=BGN&to=USD&q=1");
				InputStream inputStream = uri.openStream();
				String jsonString = IOUtils.toString(inputStream);
				JSONObject jsonObject = new JSONObject(jsonString);
				Number rate = (Number) jsonObject.get("rate");
				currentRate = BigDecimal.valueOf(rate.doubleValue()).setScale(4, BigDecimal.ROUND_HALF_UP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return currentRate;
	}
}
