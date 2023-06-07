package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalloonRepositoryJPA extends JpaRepository<Balloon, Long> {

    List<Balloon> findAllByBalloonType(Type balloonType);

    //List<Balloon> findAllByNameOrDescription(String text);

    void deleteByName(String name);
}
