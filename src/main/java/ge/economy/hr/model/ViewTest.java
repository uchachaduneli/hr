package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_test", schema = "hr")
public class ViewTest {
    private Integer id;
    private String name;
    private String description;
    private Integer activeStatusId;
    private String activeStatusName;
    private Integer typeId;
    private String typeName;
    private Integer organisationId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "active_status_id", nullable = true)
    public Integer getActiveStatusId() {
        return activeStatusId;
    }

    public void setActiveStatusId(Integer activeStatusId) {
        this.activeStatusId = activeStatusId;
    }

    @Basic
    @Column(name = "active_status_name", nullable = false, length = 100)
    public String getActiveStatusName() {
        return activeStatusName;
    }

    public void setActiveStatusName(String activeStatusName) {
        this.activeStatusName = activeStatusName;
    }

    @Basic
    @Column(name = "type_id", nullable = false)
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "type_name", nullable = false, length = 150)
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Basic
    @Column(name = "organisation_id", nullable = false)
    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewTest viewTest = (ViewTest) o;

        if (id != viewTest.id) return false;
        if (typeId != viewTest.typeId) return false;
        if (organisationId != viewTest.organisationId) return false;
        if (name != null ? !name.equals(viewTest.name) : viewTest.name != null) return false;
        if (description != null ? !description.equals(viewTest.description) : viewTest.description != null)
            return false;
        if (activeStatusId != null ? !activeStatusId.equals(viewTest.activeStatusId) : viewTest.activeStatusId != null)
            return false;
        if (activeStatusName != null ? !activeStatusName.equals(viewTest.activeStatusName) : viewTest.activeStatusName != null)
            return false;
        if (typeName != null ? !typeName.equals(viewTest.typeName) : viewTest.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (activeStatusId != null ? activeStatusId.hashCode() : 0);
        result = 31 * result + (activeStatusName != null ? activeStatusName.hashCode() : 0);
        result = 31 * result + typeId;
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + organisationId;
        return result;
    }
}
