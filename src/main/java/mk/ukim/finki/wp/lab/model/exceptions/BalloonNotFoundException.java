package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BalloonNotFoundException extends RuntimeException{

    public BalloonNotFoundException(Long id){
        super(String.format("Balloon with id %s was not found", id));
    }

}
