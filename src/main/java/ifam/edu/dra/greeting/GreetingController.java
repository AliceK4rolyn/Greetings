package ifam.edu.dra.greeting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	List<Greeting> greeting = new ArrayList<>();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Greeting newGreeting = new Greeting(counter.incrementAndGet(), String.format(template, name));
		greeting.add(newGreeting);
		return newGreeting;

	}

	@GetMapping("/greetings")
	public List<Greeting> getGreetings() {
		return greeting;
	}

	@GetMapping("/greetings/{id}")
	public Greeting getGreetingById(@PathVariable int id) {

		if (id >= 0 && id < greeting.size()) {
			return greeting.get(id);
		} else {
			throw new IndexOutOfBoundsException("Índice Inválido");
		}

	}

}
