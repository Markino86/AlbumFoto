
package it.cspnet.albumfotografico.servizi;

import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;

public interface Servizi {

    public void creaAlbum(Album album);
    public void creaUtente(Utente utente) throws UtenteGiaPresenteException;

    public Utente login(String username, String password) throws UserNotFoundException, WrongPasswordException, Exception;

    public Collection<Album> listaAlbum(String username);
   
}
