

package it.cspnet.albumfotografico.exception;

public class UtenteGiaPresenteException extends Exception{

    public UtenteGiaPresenteException() {
    }

    public UtenteGiaPresenteException(String message) {
        super(message);
    }

    public UtenteGiaPresenteException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtenteGiaPresenteException(Throwable cause) {
        super(cause);
    }

    public UtenteGiaPresenteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
