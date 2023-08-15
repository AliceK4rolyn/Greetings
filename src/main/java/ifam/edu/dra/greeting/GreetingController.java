package ifam.edu.dra.greeting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	List<Greeting> greeting = new ArrayList<>();
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		return new Greeting(counter.incrementAndGet(),String.format(template, name));
		
	}
	
	@GetMapping("/greetings")
	public List<Greeting> greeting(@RequestParam(value = "name" , defaultValue = "World") String name,
								   @RequestParam(value = "count", defaultValue = "1") int cont) {
		
		
		for(int i = 0; i < cont; i++) {
			greeting.add(new Greeting(counter.incrementAndGet(), String.format(template, name)));
		}
		
		return greeting;
	}
	
	@GetMapping("/greetings/{id}")
	public Greeting getGreetingById(@RequestParam(value = "id") int id) {
		
		if(id >= 0 && id < greeting.size()) {
			return greeting.get(id);
		} else {
			throw new IndexOutOfBoundsException("Índice Inválido");
		}
		
	}
	
}
