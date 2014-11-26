package it.cspnet.albumfotografico.servizi;

import it.cspnet.albumfotografico.dao.AlbumDao;
import it.cspnet.albumfotografico.dao.CommentoDao;
import it.cspnet.albumfotografico.dao.FotoDao;
import it.cspnet.albumfotografico.dao.UtenteDao;
import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Commento;
import it.cspnet.albumfotografico.model.Foto;
import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("servizi")
@Transactional
public class ServiziImpl implements Servizi {

    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private UtenteDao utenteDao;
    @Autowired
    private FotoDao fotoDao;
    @Autowired
    private CommentoDao commentoDao;

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void creaAlbum(Album album) {
        this.albumDao.save(album);
    }

    public void setFotoDao(FotoDao fotoDao) {
        this.fotoDao = fotoDao;
    }

    public void setUtenteDao(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
    }

    public void setCommentoDao(CommentoDao commentoDao) {
        this.commentoDao = commentoDao;
    }
    
    public void creaUtente(Utente utente) throws UtenteGiaPresenteException {
        if (null != this.utenteDao.findOne(utente.getUsername())) {
            throw new UtenteGiaPresenteException();
        } else {
            this.utenteDao.save(utente);
        }
    }

    public Utente login(String username, String password) throws UserNotFoundException, WrongPasswordException, Exception {
        Utente user = utenteDao.findOne(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            } else {
                throw new WrongPasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public Collection<Album> listaAlbum(String username) {
        return this.utenteDao.findOne(username).getAlbums();
    }

    public void salvaFoto(Foto foto, String nomeAlbum) {
        foto.setAlbum(albumDao.findOne(nomeAlbum));
        fotoDao.save(foto);
    }

    public Collection<Foto> listaFoto(String nomeAlbum) {
        Album album = albumDao.findOne(nomeAlbum);
        return fotoDao.findByAlbumEquals(album);
    }

    public Collection<Utente> listaUtenti() {
        return utenteDao.findAll();
    }

    public Collection<Album> albumUtente(String username) {
        Utente u = utenteDao.findOne(username);
        return albumDao.findByUtenteEquals(u);
    }

    public Collection<Utente> listaNomi(String lettere) {
        return utenteDao.findByUsernameContaining(lettere);
    }

    public Commento lasciaCommento(String commento, String nomeAlbum, String username) {
//        public Commento lasciaCommento(String commento, String username) {
        Album album = albumDao.findOne(nomeAlbum);
//            Album album = albumDao.findOne("pp2");
        Utente utente = utenteDao.findOne(username);
        Commento c = new Commento();
        c.setAlbum(album);        
        c.setUtente(utente);
        c.setTestoCommento(commento);
        commentoDao.save(c);
        return c;       
    }

}
