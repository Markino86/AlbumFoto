package it.cspnet.albumfotografico.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENTI")
public class Commento implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_COMMENTO")
    private long idCommento;
    
    @Column(name="COMMENTO",nullable = false,length = 300)
    private String testoCommento;
    
    @ManyToOne
    @JoinColumn(name ="NOME_ALBUM",nullable = false)
    private Album album;
    
    @ManyToOne
    @JoinColumn(name="USERNAME",nullable = false)
    private Utente utente;

    public Commento() {
    }

    public long getIdCommento() {
        return idCommento;
    }

    public void setIdCommento(long idCommento) {
        this.idCommento = idCommento;
    }

    public String getTestoCommento() {
        return testoCommento;
    }

    public void setTestoCommento(String testoCommento) {
        this.testoCommento = testoCommento;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.idCommento ^ (this.idCommento >>> 32));
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
        final Commento other = (Commento) obj;
        if (this.idCommento != other.idCommento) {
            return false;
        }
        return true;
    }
    
    
    
}
