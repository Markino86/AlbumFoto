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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ALBUM")
public class Album implements Serializable {

    @Id
    @Column(name = "NOME", nullable = false)
    private String nome;
    @Column(name="PROPRIETA",nullable = false)
    private String proprieta;
    @ManyToOne
    @JoinColumn(name = "USERNAME", nullable = false)
    private Utente utente;
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "album")
    private Set<Foto> foto = new HashSet<Foto>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "album")
    private Set<Commento> commenti = new HashSet<Commento>();

    public Album() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getProprieta() {
        return proprieta;
    }

    public void setProprieta(String proprieta) {
        this.proprieta = proprieta;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        return hash;
    }

    public Set<Foto> getFoto() {
        return foto;
    }

    public void setFoto(Set<Foto> foto) {
        this.foto = foto;
    }

    public Set<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(Set<Commento> commenti) {
        this.commenti = commenti;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }

}
