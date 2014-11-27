package it.cspnet.albumfotografico.dao;

import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Commento;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("commentoDao")
public interface CommentoDao extends JpaRepository<Commento, Long>{

    public Collection<Commento> findByAlbum(Album album);
    
}
