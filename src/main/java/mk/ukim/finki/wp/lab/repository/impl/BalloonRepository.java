package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.dataholder.DataHolder;
import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Type;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {

    public List<Balloon> findAllBalloons() {
        return DataHolder.balloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return DataHolder.balloons.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> save(Long id, String name, String description, Manufacturer manufacturer, Type balloonType) {
        DataHolder.balloons.removeIf(i -> i.getId().equals(id));
        Balloon balloon = new Balloon(name, description, manufacturer, balloonType);
        DataHolder.balloons.add(balloon);
        return Optional.of(balloon);
    }

    public void deleteById(Long id) {
        DataHolder.balloons.removeIf(i -> i.getId().equals(id));
    }

    public Optional<Balloon> findById(Long id) {
        return DataHolder.balloons
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst();
    }

    public List<Balloon> findByType(String type){
        return DataHolder.balloons.stream().filter(r->r.getBalloonType().toString().contains(type)).collect(Collectors.toList());
    }
}
