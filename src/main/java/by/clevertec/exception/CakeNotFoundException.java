package by.clevertec.exception;

import java.util.UUID;

public class CakeNotFoundException extends RuntimeException {
    private CakeNotFoundException(String message) {
        super(message);
    }
    public static CakeNotFoundException getException (UUID cakeId){
       return new CakeNotFoundException("Not found id %s");

    }
}
