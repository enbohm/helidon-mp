package se.enbohms.helidon;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.Configuration.SenderConfiguration;
import io.jaegertracing.internal.samplers.ConstSampler;
import io.opentracing.Tracer;

/**
 * Factory class which produces {@link Tracer} instances.
 * 
 * @author enbohm
 *
 */
@ApplicationScoped
public class TracerFactory {

	@Inject
	@ConfigProperty(name = "jaeger.host")
	private String jaegerHost;

	@Inject
	@ConfigProperty(name = "jeager.port", defaultValue = "5775")
	private Integer jaegerPort;

	@Produces
	@ApplicationScoped
	public Tracer getTracer() {
		SamplerConfiguration samplerConfig = new SamplerConfiguration().withType(ConstSampler.TYPE).withParam(1);
		SenderConfiguration senderConfig = new SenderConfiguration().withAgentHost(jaegerHost)
				.withAgentPort(jaegerPort);
		ReporterConfiguration reporterConfig = new ReporterConfiguration().withLogSpans(true).withFlushInterval(1000)
				.withMaxQueueSize(1000).withSender(senderConfig);
		return new Configuration("helidon-mp").withSampler(samplerConfig).withReporter(reporterConfig).getTracer();
	}
}
