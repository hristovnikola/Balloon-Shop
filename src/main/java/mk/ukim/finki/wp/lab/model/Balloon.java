package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Balloon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Manufacturer manufacturer;

    @Enumerated(EnumType.STRING)
    private Type balloonType;

    public Balloon(String name, String description, Manufacturer manufacturer, Type balloonType) {
        this.name = name;
        this.description = description;
        this.manufacturer = manufacturer;
        this.balloonType = balloonType;
    }

    public Balloon(){

    }

}
