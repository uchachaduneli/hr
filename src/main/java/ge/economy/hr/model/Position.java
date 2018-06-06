package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {
    private Integer id;
    private String name;
    private Integer position;
    private String description;
    private String ldapKey;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "position", nullable = true)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 250)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ldap_key", nullable = true, length = 150)
    public String getLdapKey() {
        return ldapKey;
    }

    public void setLdapKey(String ldapKey) {
        this.ldapKey = ldapKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        if (id != position1.id) return false;
        if (name != null ? !name.equals(position1.name) : position1.name != null) return false;
        if (position != null ? !position.equals(position1.position) : position1.position != null) return false;
        if (description != null ? !description.equals(position1.description) : position1.description != null)
            return false;
        if (ldapKey != null ? !ldapKey.equals(position1.ldapKey) : position1.ldapKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ldapKey != null ? ldapKey.hashCode() : 0);
        return result;
    }
}
