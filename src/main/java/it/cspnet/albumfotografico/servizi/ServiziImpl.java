package it.cspnet.albumfotografico.servizi;

import it.cspnet.albumfotografico.dao.AlbumDao;
import it.cspnet.albumfotografico.dao.FotoDao;
import it.cspnet.albumfotografico.dao.UtenteDao;
import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Foto;
import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;
import java.util.Set;
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
    @Override
    public void creaUtente(Utente utente) throws UtenteGiaPresenteException {
        if (null != this.utenteDao.findOne(utente.getUsername())) {
            throw new UtenteGiaPresenteException();
        } else {
            this.utenteDao.save(utente);
        }
    }
    @Override
    public Utente login(String username, String password) throws UserNotFoundException, WrongPasswordException, Exception {
        Utente u = utenteDao.findOne(username);
        if (u != null) {
            if (password.equals(u.getPassword())) {
                return u;
            } else {
                throw new WrongPasswordException();
            }
        } else {
            throw new UserNotFoundException();
        }
    }
    @Override
    public Collection<Album> listaAlbum(String username) {
        return this.utenteDao.findOne(username).getAlbums();
    }
    @Override
    public void salvaFoto(Foto foto, String nomeAlbum) {
        foto.setAlbum(albumDao.findOne(nomeAlbum));
        fotoDao.save(foto);
    }
    @Override
    public Collection<Foto> listaFoto(String nomeAlbum) {
        Album album = albumDao.findOne(nomeAlbum);
        return fotoDao.findByAlbumEquals(album);
    }
    @Override
    public Collection<Utente> listaUtenti() {
        return utenteDao.findAll();
    }
    @Override
    public Collection<Album> albumUtente(String username) {
        Utente u = utenteDao.findOne(username);
        return albumDao.findByUtenteEquals(u);
    }

    @Override
    public Album cambiaProprieta(String nomeAlbum) {
        Album album = albumDao.findOne(nomeAlbum);
        if(album.getProprieta().equals("pubblico")){
            album.setProprieta("privato");
        }else{
            album.setProprieta("pubblico");
        }
        return albumDao.save(album);
    }
    @Override
    public Collection<Utente> listaNomi(String lettere) {
        return utenteDao.findByUsernameContaining(lettere);
    }
    @Override
    public void eliminaFotoAlbum(String nomeAlbum) {
        Album albumDaCancellare = albumDao.findOne(nomeAlbum);        
        for (Foto f : fotoDao.findByAlbumEquals(albumDaCancellare)){
            fotoDao.delete(f);
        }
    }

    @Override
    public void eliminaAlbum(String nome) {
        Album albumDaCancellare = albumDao.findOne(nome);
        albumDao.delete(albumDaCancellare); 
    }
}
