
package it.cspnet.albumfotografico.servizi;

import it.cspnet.albumfotografico.exception.UserNotFoundException;
import it.cspnet.albumfotografico.exception.UtenteGiaPresenteException;
import it.cspnet.albumfotografico.exception.WrongPasswordException;
import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Commento;
import it.cspnet.albumfotografico.model.Foto;
import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;

public interface Servizi {

    public void creaAlbum(Album album);
    
    public void creaUtente(Utente utente) throws UtenteGiaPresenteException;

    public Utente login(String username, String password) throws UserNotFoundException, WrongPasswordException, Exception;

    public Collection<Album> listaAlbum(String username);

    public void salvaFoto(Foto foto, String nomeAlbum);

    public Collection<Foto> listaFoto(String nomeAlbum);

    public Collection<Utente> listaUtenti();

    public Collection<Album> albumUtente(String username);

    public Album cambiaProprieta(String nomeAlbum);

    public Collection<Utente> listaNomi(String lettere);
    
    public Commento lasciaCommento(String commento, String nomeAlbum, String username);

    public Collection<Commento> visualizzaCommenti(String nomeAlbum);
       
    public void eliminaAlbum(String nome);

    public void eliminaFotoAlbum(String nomeAlbum);

    public void eliminaSingleFoto(String nomeFoto);
}
