package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "structure")
public class Structure {
    private Integer id;
    private Integer parentId;
    private Integer organisationId;
    private String name;
    private String description;
    private String ldapKey;
    private Integer visible;

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
    @Column(name = "parent_id", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "organisation_id", nullable = true)
    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "ldap_key", nullable = true, length = 50)
    public String getLdapKey() {
        return ldapKey;
    }

    public void setLdapKey(String ldapKey) {
        this.ldapKey = ldapKey;
    }

    @Basic
    @Column(name = "visible", nullable = true)
    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Structure structure = (Structure) o;

        if (id != structure.id) return false;
        if (parentId != null ? !parentId.equals(structure.parentId) : structure.parentId != null) return false;
        if (organisationId != null ? !organisationId.equals(structure.organisationId) : structure.organisationId != null)
            return false;
        if (name != null ? !name.equals(structure.name) : structure.name != null) return false;
        if (description != null ? !description.equals(structure.description) : structure.description != null)
            return false;
        if (ldapKey != null ? !ldapKey.equals(structure.ldapKey) : structure.ldapKey != null) return false;
        if (visible != null ? !visible.equals(structure.visible) : structure.visible != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (organisationId != null ? organisationId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (ldapKey != null ? ldapKey.hashCode() : 0);
        result = 31 * result + (visible != null ? visible.hashCode() : 0);
        return result;
    }
}
