package mgivanov.qsmsa.rest;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import mgivanov.qsmsa.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();
    private final Map<Long, Greeting> greetings = new HashMap<>();

    //заполним тестовыми данными
    public GreetingController() {
        counter.set(5);
        greetings.put(1L, new Greeting(1, String.format(template, "Max")));
        greetings.put(2L, new Greeting(2, String.format(template, "Bruce")));
        greetings.put(3L, new Greeting(3, String.format(template, "Petr")));
        greetings.put(4L, new Greeting(4, String.format(template, "Jone Dou")));
        greetings.put(5L, new Greeting(5, String.format(template, "Ludvig")));
    }

    @GetMapping("/greetings")
    public List<Greeting> getAllGreetings() {
        return new LinkedList<>(greetings.values());
    }

    @GetMapping("/greetings/{id}")
    public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
        Greeting greeting = greetings.get(id);
        return new ResponseEntity<>(greeting, greeting == null ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }


    @PostMapping("/greetings")
    public Greeting create(@RequestBody Greeting greeting) {
        long tCounter = counter.incrementAndGet();
        Greeting tGreeting = new Greeting(tCounter, String.format(template, greeting.getContent()));
        greetings.put(tCounter, tGreeting);
        return tGreeting;
    }

    @PutMapping("/greetings")
    public ResponseEntity<Greeting> update(@RequestBody Greeting greeting) {
        Greeting tGreeting = greetings.get(greeting.getId());
        HttpStatus status = HttpStatus.OK;

        if (tGreeting == null) {
            status = HttpStatus.NO_CONTENT;
        } else {
            greetings.put(greeting.getId(), greeting);
        }
        return new ResponseEntity<>(greeting, status);
    }

    @DeleteMapping("/greetings/{id}")
    public void deleteGreeting(@PathVariable("id") Long id) {
        greetings.remove(id);
    }

}
