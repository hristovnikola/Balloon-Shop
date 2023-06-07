package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.dataholder.DataHolder;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ManufacturerRepository {

    public List<Manufacturer> findAll(){
        return DataHolder.manufacturers;
    }


    public Optional<Manufacturer> findById(Long manufacturerId) {
        return DataHolder.manufacturers
                .stream()
                .filter(i->i.getId().equals(manufacturerId))
                .findFirst();
    }
}
