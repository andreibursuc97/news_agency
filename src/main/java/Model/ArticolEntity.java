package Model;

import javax.persistence.*;

@Entity
@Table(name = "articol", schema = "agentie_presa", catalog = "")
@NamedQueries({
        @NamedQuery(name="ArticolEntity.afiseazaArticole",query = "FROM ArticolEntity "),
        @NamedQuery(name="ArticolEntity.getId",query = "select art.id FROM ArticolEntity as art where art.titlu=:titlu "),
        @NamedQuery(name="ArticolEntity.getMaxId",query = "select max(art.id) FROM ArticolEntity as art")
})

public class ArticolEntity {
    private int id;
    private String titlu;
    private String abstractArticol;
    private String autor;
    private String continut;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "titlu")
    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    @Basic
    @Column(name = "abstract_articol")
    public String getAbstractArticol() {
        return abstractArticol;
    }

    public void setAbstractArticol(String abstractArticol) {
        this.abstractArticol = abstractArticol;
    }

    @Basic
    @Column(name = "autor")
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Basic
    @Column(name = "continut")
    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticolEntity that = (ArticolEntity) o;

        if (id != that.id) return false;
        if (titlu != null ? !titlu.equals(that.titlu) : that.titlu != null) return false;
        if (abstractArticol != null ? !abstractArticol.equals(that.abstractArticol) : that.abstractArticol != null)
            return false;
        if (autor != null ? !autor.equals(that.autor) : that.autor != null) return false;
        if (continut != null ? !continut.equals(that.continut) : that.continut != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titlu != null ? titlu.hashCode() : 0);
        result = 31 * result + (abstractArticol != null ? abstractArticol.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (continut != null ? continut.hashCode() : 0);
        return result;
    }
}
