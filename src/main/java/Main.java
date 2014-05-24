import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.guice.spi.container.GuiceComponentProviderFactory;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.URI;

public class Main {
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost/").port(8080).build();
	}

	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
			}
		});
		ResourceConfig rc = new PackagesResourceConfig("service");
		IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
		HttpServer server = null;
		try {
			server = GrizzlyServerFactory.createHttpServer(getBaseURI() + "services/", rc, ioc);
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
