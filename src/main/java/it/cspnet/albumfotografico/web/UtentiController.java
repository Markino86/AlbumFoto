package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UtentiController {
    @Autowired
    Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }
    
    @RequestMapping(value = "/listaUtenti", method = RequestMethod.GET)
    public @ResponseBody JsonResult listaUtenti() {
        JsonResult jsr = new JsonResult();
        Collection<Utente> utenti = null;
        try {
            utenti =  servizi.listaUtenti();
            jsr.setRisultato(utenti);
            jsr.setCodice(0);
        } catch (Exception ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Nessun utente presente sul server!!");
        }
        return jsr;
    }
}
