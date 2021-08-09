package mgivanov.qsmsa.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import mgivanov.qsmsa.model.Greeting;
import mgivanov.qsmsa.util.annotation.LogInOutParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    @LogInOutParameters
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        int a = (int) ( Math.random() * 2 );
        if(a==1){
            throw new RuntimeException();
        }
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/count")
    @LogInOutParameters
    public AtomicLong getCounter() {
        return counter;
    }


}
