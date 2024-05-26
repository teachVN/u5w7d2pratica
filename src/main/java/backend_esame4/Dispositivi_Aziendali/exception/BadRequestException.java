package backend_esame4.Dispositivi_Aziendali.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super (message);
    }
}
