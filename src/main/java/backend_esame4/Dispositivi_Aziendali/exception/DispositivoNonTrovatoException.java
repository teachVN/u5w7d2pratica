package backend_esame4.Dispositivi_Aziendali.exception;

public class DispositivoNonTrovatoException extends RuntimeException{
    public DispositivoNonTrovatoException(String message){
        super(message);
    }
}