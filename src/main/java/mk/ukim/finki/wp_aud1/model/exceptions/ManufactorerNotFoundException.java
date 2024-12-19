package mk.ukim.finki.wp_aud1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ManufactorerNotFoundException extends RuntimeException{
    public ManufactorerNotFoundException(Long id){
        super(String.format("Manufacturer with id %d was not found", id));
    }
}
