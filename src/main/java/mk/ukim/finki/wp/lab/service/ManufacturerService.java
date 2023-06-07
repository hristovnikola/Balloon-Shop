package mk.ukim.finki.wp.lab.service;


import mk.ukim.finki.wp.lab.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    public List<Manufacturer> findAll();

    public <Optional>Manufacturer save(String name, String country, String address);
}
