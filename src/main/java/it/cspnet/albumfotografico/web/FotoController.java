package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.model.Foto;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.io.File;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FotoController {

    @Autowired
    private Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }
    @RequestMapping(value = "/salvafoto", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult salvaFoto(@RequestParam("file") MultipartFile file, @RequestParam("nomeAlbum") String nomeAlbum, @RequestParam("username") String username ) {
        JsonResult js = new JsonResult();
        try {
            String path = "C:/Albums/" + username + "/" + nomeAlbum + "/";
            File fileCreato = new File (path + file.getOriginalFilename());
            if(fileCreato.exists()){
                js.setCodice(2);
                js.setMessaggio("Il file:" + file.getOriginalFilename()+" è già presente nell'album" );
                return js;
            }
            file.transferTo(fileCreato);
            Foto foto = new Foto();
            foto.setNome("Albums/"+ username + "/" + nomeAlbum + "/" + file.getOriginalFilename());
            servizi.salvaFoto(foto, nomeAlbum);
            js.setCodice(0);
            js.setMessaggio("Complimenti hai caricato il file: " + file.getOriginalFilename());
            return js;
        }
        catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Errore sul server!!");
            return js;
        }
    }
    @RequestMapping(value = "/listaFoto", method = RequestMethod.GET)
    public @ResponseBody
    JsonResult listaFoto(HttpServletRequest req ) {
        JsonResult js = new JsonResult();
        String nomeAlbum = req.getParameter("nomeAlbum");
        Collection<Foto> foto = null;
        try {
            foto = servizi.listaFoto(nomeAlbum);
            if(foto.isEmpty()){
                js.setCodice(1);
                js.setMessaggio("Nessuna foto nell'album");
                return js;
            }
            js.setRisultato(foto);
            js.setCodice(0);
            return js;
        }catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Errore sul server!!");
            return js;
        }
    }
    
    @RequestMapping(value = "/eliminaFoto",method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult eliminaSingleFoto(HttpServletRequest req){
        JsonResult js = new JsonResult();
        String nomeFoto = req.getParameter("nomeFoto");
         try {
            servizi.eliminaSingleFoto(nomeFoto);
            String path = "C:/" + nomeFoto;
            File dir = new File(path);
            dir.delete();
            js.setCodice(0);
            js.setMessaggio("Foto eliminata con successo");
            return js;
        }
        catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Errore sul server!!");
            return js;
        }
    }
}
