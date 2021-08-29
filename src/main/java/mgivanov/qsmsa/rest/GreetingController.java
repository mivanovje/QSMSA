package mgivanov.qsmsa.rest;

import mgivanov.qsmsa.entity.Greeting;
import mgivanov.qsmsa.service.GreetingServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingServiceImpl greetingServiceImpl;

    public GreetingController(GreetingServiceImpl greetingServiceImpl) {
        this.greetingServiceImpl = greetingServiceImpl;
    }

    @GetMapping("/greetings")
    public List<Greeting> getAllGreetings() {
        return greetingServiceImpl.getAll();
    }

    @GetMapping("/greetings/{id}")
    public Greeting getGreeting(@PathVariable("id") Long id) {
        return greetingServiceImpl.getById(id);
    }

    @PostMapping("/greetings")
    public Greeting create(@RequestBody Greeting greeting) {
        return greetingServiceImpl.save(greeting);
    }

    @PutMapping("/greetings")
    public Greeting update(@RequestBody Greeting greeting) {
        return greetingServiceImpl.update(greeting);
    }

    @DeleteMapping("/greetings/{id}")
    public void deleteGreeting(@PathVariable("id") Long id) {
        greetingServiceImpl.delete(id);
    }
}
