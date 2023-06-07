package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Type;

import java.util.List;
import java.util.Optional;

public interface BalloonService {

    List<Balloon> listAll();
    //List<Balloon> searchByNameOrDescription(String text);

    List<Balloon> filterAllBalloonsByType(String type);

    Optional<Balloon> save(String name, String description, Long manufacturer, Type balloonType);

    Optional<Balloon> edit(Long id, String name, String description, Long manufacturer, Type balloonType);
    void deleteById(Long id);

    Optional<Balloon> findById(Long id);

}
