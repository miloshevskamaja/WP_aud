package mk.ukim.finki.wp_aud1.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(Long id,String username) {
        super(String.format("Product with id %d already exists in the shopping cart for user with username %s",id,username));
    }
}
