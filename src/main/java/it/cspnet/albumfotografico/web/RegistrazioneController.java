package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrazioneController {

    @Autowired
    Servizi servizi;

    @RequestMapping(value = "/registrati", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult registrazioneUtente(@RequestBody Utente utente) {
        JsonResult jsr = new JsonResult();
        try {
            jsr.setCodice(0);
            servizi.creaUtente(utente);
            jsr.setMessaggio("ok");
            String path = "C:/Albums";
            //creo la directory personale dell' utente
            File dir = new File(path + "/" + utente.getUsername());
            // if the directory does not exist, create it
            if (!dir.exists()) {
                System.out.println("creating directory: " + dir.getName());
                boolean result = false;

                try {
                    dir.mkdir();
                    result = true;
                } catch (SecurityException se) {

                }
                if (result) {
                    System.out.println("DIR created");
                }
            }
        } catch (UtenteGiaPresenteException ex) {
            jsr.setCodice(1);
            jsr.setMessaggio("Utente gia' presente");
        } finally {
            return jsr;
        }
    }

}
