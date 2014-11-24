
package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @Autowired
    Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody JsonResult loginUtente(@RequestBody Utente utente) {
        JsonResult jsr = new JsonResult();
        Utente u = null;
        try {
            u =  servizi.login(utente.getUsername(), utente.getPassword());
            jsr.setRisultato(u);
            jsr.setCodice(0);
        } catch (UserNotFoundException ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Utente '" + utente.getUsername() + "' non trovato!");
        } catch (WrongPasswordException ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Password errata!");
        } catch (Exception ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Sito in manutenzione: riprovare più tardi");
        }
        return jsr;
    }
    
}
