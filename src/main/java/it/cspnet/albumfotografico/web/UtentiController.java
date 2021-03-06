package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        try {
            Set<Album> albums = new HashSet<Album>();
            for(Album a : servizi.albumUtente(username) ){
                if(a.getProprieta().equals("privato"))
                    albums.remove(a);
                else
                    albums.add(a);
            } 
            if(albums.isEmpty()){
                jsr.setCodice(1);
                jsr.setMessaggio("Non ci sono album presenti per questo utente");
                return jsr;
            }
            jsr.setRisultato(albums);
            jsr.setCodice(0);
        } catch (Exception ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Errore sul server!!");
        }
        return jsr;
    }
    @RequestMapping(value = "/nomiUtenti", method = RequestMethod.GET)
    public @ResponseBody JsonResult nomiUtenti(HttpServletRequest req) {
        String lettere = req.getParameter("lettere");
        JsonResult jsr = new JsonResult();
        Collection<Utente> utenti = null;
        try {
            utenti =  servizi.listaNomi(lettere);
            jsr.setRisultato(utenti);
            jsr.setCodice(0);
        } catch (Exception ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Errore sul server!!");
        }
        return jsr;
    }
}
