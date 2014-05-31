import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.jaxws.JaxwsHandler;
import service.CurrencyRateService;
import service.FoodService;
import service.OrderService;
import service.UserService;
import transport.JsonMessageBodyWriter;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Main {
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://0.0.0.0/").port(8080).build();
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				this.bind(JsonMessageBodyWriter.class);
			}
		});
		ResourceConfig rc = new PackagesResourceConfig("service");
		IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
		HttpServer server = null;
		try {
			server = GrizzlyServerFactory.createHttpServer(getBaseURI() + "services/", rc, ioc);

			HttpHandler foodHttpHandler = new JaxwsHandler(injector.getInstance(FoodService.class));
			HttpHandler orderHttpHandler = new JaxwsHandler(injector.getInstance(OrderService.class));
			HttpHandler currencyHttpHandler = new JaxwsHandler(injector.getInstance(CurrencyRateService.class));
			HttpHandler userHttpHandler = new JaxwsHandler(injector.getInstance(UserService.class));
			NetworkListener networkListener = new NetworkListener("jaxws-listener", "0.0.0.0", 8081);
			server.getServerConfiguration().addHttpHandler(foodHttpHandler, "/food");
			server.getServerConfiguration().addHttpHandler(orderHttpHandler, "/order");
			server.getServerConfiguration().addHttpHandler(currencyHttpHandler, "/currencyRate");
			server.getServerConfiguration().addHttpHandler(userHttpHandler, "/user");
			server.addListener(networkListener);

			server.start();
			Thread.currentThread().join();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				server.stop();
			}
		}
	}
}
