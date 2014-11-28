package it.cspnet.albumfotografico.web;

import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Commento;
import it.cspnet.albumfotografico.model.JsonResult;
import it.cspnet.albumfotografico.model.Utente;
import it.cspnet.albumfotografico.servizi.Servizi;
import java.io.File;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlbumController {

    @Autowired
    private Servizi servizi;

    public void setServizi(Servizi servizi) {
        this.servizi = servizi;
    }

    @RequestMapping(name = "/creaAlbum", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult creaAlbum(@RequestBody Album album) {
        JsonResult jsonResult = new JsonResult();
        String path = "C:/Albums/" + album.getUtente().getUsername() + "/";
        File dir = new File(path + album.getNome());
        if (!dir.exists()) {
            System.out.println("creating directory: " + dir.getName());
            boolean result = false;
            try {
                dir.mkdir();
                result = true;
            } catch (SecurityException se) {
                jsonResult.setCodice(1);
                jsonResult.setMessaggio("Security error");
                return jsonResult;
            }
            if (result) {
                System.out.println("DIR created");
            }
        }
        jsonResult.setCodice(0);
        servizi.creaAlbum(album);
        jsonResult.setMessaggio("Album creato correttamente");
        return jsonResult;
    }

    @RequestMapping(name = "/listaAlbum", method = RequestMethod.GET)
    public @ResponseBody
    JsonResult listaAlbum(HttpServletRequest req) {
        JsonResult jsonResult = new JsonResult();
        Collection<Album> albums = null;
        String username = req.getParameter("username");
        try {
            albums = servizi.listaAlbum(username);
            if(albums.isEmpty()){
                jsonResult.setCodice(1);
                jsonResult.setMessaggio("Non hai ancora creato nessun album");
                return jsonResult;
            }
            jsonResult.setRisultato(albums);
            jsonResult.setCodice(0);
            jsonResult.setMessaggio("ok");
            return jsonResult;
        } catch (Exception ex) {
            jsonResult.setMessaggio("errore caricamento");
            jsonResult.setCodice(1);
            return jsonResult;
        }   
    }

    @RequestMapping(name = "/cambiaProprieta", method = RequestMethod.PUT)
    public @ResponseBody
    JsonResult cambiaProprieta(@RequestBody Album album) {
        JsonResult js = new JsonResult();

        try {
            Album albumDaModificare = servizi.cambiaProprieta(album.getNome());
            js.setCodice(0);
            js.setMessaggio("Proprietà cambiata correttamente!");
            js.setRisultato(albumDaModificare);
        } catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Impossibile cambiare la proprietà di visualizzazione");
        } finally {
            return js;
        }
    }

    @RequestMapping(value = "/inviaCommento", method = RequestMethod.POST)
    public @ResponseBody
    JsonResult inviaCommento(@RequestBody String commento, HttpServletRequest req) {
        JsonResult jsonResult = new JsonResult();
        String nomeAlbum = req.getParameter("nomeAlbum");
        String username = req.getParameter("username");
        try {
            Commento c = servizi.lasciaCommento(commento, nomeAlbum, username);
            jsonResult.setRisultato(c);
            jsonResult.setCodice(0);
            jsonResult.setMessaggio("ok");
        } catch (Exception ex) {
            jsonResult.setMessaggio("errore caricamento");
            jsonResult.setCodice(1);
        } finally {
            return jsonResult;
        }
    }

    @RequestMapping(name = "/eliminaAlbum", method = RequestMethod.DELETE)
    public @ResponseBody
    JsonResult deleteAlbum(HttpServletRequest req) {
        JsonResult js = new JsonResult();
        String nomeAlbum = req.getParameter("nomeAlbum");
        String username = req.getParameter("username");
        try {
            String path = "C:/Albums/" + username + "/" + nomeAlbum;
            File dir = new File(path);
            for (File f : dir.listFiles()) {
                f.delete();
            }
            dir.delete();
            servizi.eliminaFotoAlbum(nomeAlbum);
            servizi.eliminaCommentiAlbum(nomeAlbum);
            servizi.eliminaAlbum(nomeAlbum);
            js.setCodice(0);
            js.setMessaggio("Album eliminato correttamente");
        } catch (Exception ex) {
            js.setCodice(1);
            js.setMessaggio("Problemi con il server");
        } finally {
            return js;
        }
    }
}
