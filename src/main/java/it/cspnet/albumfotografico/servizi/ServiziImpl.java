package it.cspnet.albumfotografico.servizi;

import it.cspnet.albumfotografico.dao.AlbumDao;
import it.cspnet.albumfotografico.dao.UtenteDao;
import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
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

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public void creaAlbum(Album album) {
        this.albumDao.save(album);
    }

    public void setUtenteDao(UtenteDao utenteDao) {
        this.utenteDao = utenteDao;
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

}
