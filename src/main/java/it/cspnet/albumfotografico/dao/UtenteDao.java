package it.cspnet.albumfotografico.dao;

import it.cspnet.albumfotografico.model.Utente;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("utenteDao")
public interface UtenteDao extends JpaRepository<Utente, String> {

    public Collection<Utente> findByUsernameContaining(String lettere);

}
