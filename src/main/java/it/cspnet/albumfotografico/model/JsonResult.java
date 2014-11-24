package it.cspnet.albumfotografico.model;

public class JsonResult {

    private Object risultato;
    private String messaggio;
    private int codice;

    public JsonResult() {
    }

    public Object getRisultato() {
        return risultato;
    }

    public void setRisultato(Object risultato) {
        this.risultato = risultato;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

}
