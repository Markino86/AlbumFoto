package it.cspnet.albumfotografico.dao;

import it.cspnet.albumfotografico.model.Commento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("commentoDao")
public interface CommentoDao extends JpaRepository<Commento, Long>{
    
}
