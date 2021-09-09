package mgivanov.qsmsa.rest;

import mgivanov.qsmsa.entity.Greeting;
import mgivanov.qsmsa.service.GreetingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greetings")
    public List<Greeting> getAllGreetings() {
        return greetingService.getAll();
    }

    @GetMapping("/greetings/{id}")
    public Greeting getGreeting(@PathVariable("id") Long id) {
        return greetingService.getById(id);
    }

    @PostMapping("/greetings")
    public Greeting create(@RequestBody Greeting greeting) {
        return greetingService.save(greeting);
    }

    @PutMapping("/greetings")
    public Greeting update(@RequestBody Greeting greeting) {
        return greetingService.update(greeting);
    }

    @DeleteMapping("/greetings/{id}")
    public void deleteGreeting(@PathVariable("id") Long id) {
        greetingService.delete(id);
    }
}
