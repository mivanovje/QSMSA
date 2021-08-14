package mgivanov.qsmsa.service;

import mgivanov.qsmsa.entity.Greeting;
import mgivanov.qsmsa.exception.ElementNotFoundException;
import mgivanov.qsmsa.repository.GreetingRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GreetingService {
    private GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting save(Greeting greeting) {
        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAll() {
        return (List<Greeting>) greetingRepository.findAll();
    }

    public Greeting getById(Long id) {
        return greetingRepository.findById(id).orElseThrow(ElementNotFoundException::new);
    }

    public void delete(Long id) {
        greetingRepository.deleteById(id);
    }

    public Greeting update(Greeting greeting) {
        save(greeting);
        return greeting;
    }

}