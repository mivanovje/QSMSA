package mgivanov.qsmsa.service;

import mgivanov.qsmsa.entity.Greeting;

import java.util.List;

public interface GreetingService {

    Greeting save(Greeting greeting);
    List<Greeting> getAll();
    Greeting getById(Long id);
    void delete(Long id);
    Greeting update(Greeting greeting);
}
