package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.servizi.Servizi;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentoController {
    @Autowired
    private Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }
    
    @RequestMapping(value="/visualizzaCommenti", method = RequestMethod.GET)
    public @ResponseBody
    JsonResult visualizzaCommenti(HttpServletRequest req){
        JsonResult js = new JsonResult();
        String nomeAlbum = req.getParameter("nomeAlbum");
        try {
            js.setRisultato(servizi.visualizzaCommenti(nomeAlbum));
            js.setCodice(0);
            return js;
        }
        catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Errore sul server!!");
            return js;
        }
    }
    
}
