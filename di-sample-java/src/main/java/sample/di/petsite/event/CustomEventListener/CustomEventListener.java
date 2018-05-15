package sample.di.petsite.event.CustomEventListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

public class CustomEventListener implements ApplicationListener<E> {
	public void onApplicationEvent(ApplicationEvent event) {
		if(event instanceof ContextRefreshedEvent) {
			System.out.println("*** ContextRefreshedEvent ***");
		} else if(event instanceof ContextClosedEvent) {
			System.out.println("*** ContextClosedEvent ***");
		} else if(event instanceof RequestHandledEvent) {
			System.out.println("*** RequestHandledEvent! ***");
		} else {
			System.out.println("*** Event? ***");
		}
	}
}
