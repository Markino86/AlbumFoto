package it.cspnet.albumfotografico.dao;

import it.cspnet.albumfotografico.model.Album;
import it.cspnet.albumfotografico.model.Foto;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("fotoDao")
public interface FotoDao extends JpaRepository<Foto, String> {

    public Collection<Foto> findByAlbumEquals(Album album);

    

}

