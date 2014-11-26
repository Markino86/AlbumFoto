package it.cspnet.albumfotografico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UTENTI")
public class Utente implements Serializable {

    @Id
    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;
    @Column(name = "PASSWORD", nullable = false, length = 20)
    private String password;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "utente")
    private Set<Album> albums = new HashSet<Album>();
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "utente")
    private Set<Commento> commenti = new HashSet<Commento>();

    public Utente() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utente other = (Utente) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(Set<Commento> commenti) {
        this.commenti = commenti;
    }

  

}
