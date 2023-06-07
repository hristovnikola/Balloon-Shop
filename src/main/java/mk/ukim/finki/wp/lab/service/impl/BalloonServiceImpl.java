package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.model.exceptions.BalloonNotFoundException;
import mk.ukim.finki.wp.lab.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.BalloonRepository;
import mk.ukim.finki.wp.lab.repository.impl.ManufacturerRepository;
import mk.ukim.finki.wp.lab.repository.jpa.BalloonRepositoryJPA;
import mk.ukim.finki.wp.lab.repository.jpa.ManufacturerRepositoryJPA;
import mk.ukim.finki.wp.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepositoryJPA balloonRepository;
    private final ManufacturerRepositoryJPA manufacturerRepository;

    public BalloonServiceImpl(BalloonRepositoryJPA balloonRepository, ManufacturerRepositoryJPA manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    //@Override
    //public List<Balloon> searchByNameOrDescription(String text) {
        //return balloonRepository.findAllByNameOrDescription(text);
    //}

    //@Override
    public List<Balloon> filterAllBalloonsByType(String type) {
        return balloonRepository.findAllByBalloonType(Type.valueOf(type));
    }

    @Override
    @Transactional
    public Optional<Balloon> save(String name, String description, Long manufacturerId, Type balloonType) {
        Manufacturer manufacturer1 = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.balloonRepository.deleteByName(name);
        return Optional.of(this.balloonRepository.save(new Balloon(name, description, manufacturer1, balloonType)));
    }

    @Override
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturerId, Type balloonType) {
        Balloon balloon = this.balloonRepository.findById(id).orElseThrow(() -> new BalloonNotFoundException(id));

        balloon.setName(name);
        balloon.setDescription(description);
        balloon.setBalloonType(balloonType);
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        balloon.setManufacturer(manufacturer);
        return Optional.of(this.balloonRepository.save(balloon));
    }

    @Override
    public void deleteById(Long id) {
        this.balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }

}
