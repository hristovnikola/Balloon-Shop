package mk.ukim.finki.wp.lab.dataholder;

import mk.ukim.finki.wp.lab.model.Balloon;
import mk.ukim.finki.wp.lab.model.Manufacturer;
import mk.ukim.finki.wp.lab.model.Order;
import mk.ukim.finki.wp.lab.model.Type;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Balloon> balloons = new ArrayList<>();

    public static List<Manufacturer> manufacturers = new ArrayList<>();

    public static List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void init(){
        Manufacturer manufacturer = new Manufacturer("Nike", "USA", "NY NY");
        manufacturers.add(manufacturer);
        manufacturers.add(new Manufacturer("Adidas", "China", "Hong Kong"));
        manufacturers.add(new Manufacturer("Balenciaga", "France", "Paris"));
        manufacturers.add(new Manufacturer("Puma", "Spain", "Madrid"));
        manufacturers.add(new Manufacturer("Gucci", "England", "Manchester"));

        balloons.add(new Balloon("Blue Balloon", "Good balloon 1", manufacturer, Type.kocka));
        balloons.add(new Balloon("Red Balloon", "Good balloon 2", manufacturer, Type.kvadrat));
        balloons.add(new Balloon("Green Balloon", "Good balloon 3", manufacturer, Type.romb));
        balloons.add(new Balloon("Brown Balloon", "Good balloon 4", manufacturer, Type.topka));
        balloons.add(new Balloon("Pink Balloon", "Good balloon 5", manufacturer, Type.topka));
        balloons.add(new Balloon("Black Balloon", "Good balloon 6", manufacturer, Type.kocka));
        balloons.add(new Balloon("White Balloon", "Good balloon 7", manufacturer, Type.romb));
        balloons.add(new Balloon("Yellow Balloon", "Good balloon 8", manufacturer, Type.kocka));
        balloons.add(new Balloon("Orange Balloon", "Good balloon 9", manufacturer, Type.romb));
        balloons.add(new Balloon("Fluorescent Balloon", "Good balloon 10", manufacturer, Type.kvadrat));


    }
}
