package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
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
            jsr.setMessaggio("Errore sul server!!");
        }
        return jsr;
    }
    @RequestMapping(value = "/albumUtente", method = RequestMethod.GET)
    public @ResponseBody JsonResult albumUtente(HttpServletRequest req) {
        String username = req.getParameter("username");
        JsonResult jsr = new JsonResult();
        Collection<Album> albums = null;
        try {
            albums =  servizi.albumUtente(username);
            jsr.setRisultato(albums);
            jsr.setCodice(0);
        } catch (Exception ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Errore sul server!!");
        }
        return jsr;
    }
}
