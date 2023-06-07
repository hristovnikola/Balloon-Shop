package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserFullname implements Serializable {

    private String name;
    private String surname;
}