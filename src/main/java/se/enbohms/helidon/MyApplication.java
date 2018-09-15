package se.enbohms.helidon;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/")
public class MyApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> setOfClasses = new HashSet<>();
		setOfClasses.add(HelloEndpoint.class);
		return setOfClasses;
	}
}
