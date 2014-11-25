package it.cspnet.albumfotografico.dao;

import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("albumDao")
public interface AlbumDao extends JpaRepository<Album, String> {

    public Collection<Album> findByUtenteEquals(Utente u);

}
