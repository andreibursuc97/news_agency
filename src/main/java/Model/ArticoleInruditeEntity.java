package Model;

import javax.persistence.*;

@Entity
@Table(name = "articole_inrudite", schema = "agentie_presa", catalog = "")
public class ArticoleInruditeEntity {
    private int id;
    private Integer idArticol1;
    private Integer idArticol2;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "id_articol1")
    public Integer getIdArticol1() {
        return idArticol1;
    }

    public void setIdArticol1(Integer idArticol1) {
        this.idArticol1 = idArticol1;
    }

    @Basic
    @Column(name = "id_articol2")
    public Integer getIdArticol2() {
        return idArticol2;
    }

    public void setIdArticol2(Integer idArticol2) {
        this.idArticol2 = idArticol2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticoleInruditeEntity that = (ArticoleInruditeEntity) o;

        if (id != that.id) return false;
        if (idArticol1 != null ? !idArticol1.equals(that.idArticol1) : that.idArticol1 != null) return false;
        if (idArticol2 != null ? !idArticol2.equals(that.idArticol2) : that.idArticol2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idArticol1 != null ? idArticol1.hashCode() : 0);
        result = 31 * result + (idArticol2 != null ? idArticol2.hashCode() : 0);
        return result;
    }
}
