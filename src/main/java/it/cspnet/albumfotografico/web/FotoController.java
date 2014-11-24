package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.servizi.Servizi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FotoController {

    @Autowired
    private Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }
    @RequestMapping(value = "/salvafoto",headers = "content-type=multipart/*", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult salvaFoto( MultipartHttpServletRequest  req) {
        
        MultipartFile file= req.getFile(req.getFileNames().next());

        JsonResult jsr = new JsonResult();
        
        return jsr;
    }    
}
