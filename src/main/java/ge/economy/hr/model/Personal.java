package ge.economy.hr.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "personal")
public class Personal {
    private Integer id;
    private Integer organisationId;
    private Integer structureId;
    private Integer positionId;
    private String firstName;
    private String lastName;
    private String mail;
    private Date birthDate;
    private String pidNumber;
    private String phoneNumber;
    private String address;
    private Integer groupId;
    private Integer statusId;

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
    @Column(name = "organisation_id", nullable = false)
    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    @Basic
    @Column(name = "structure_id", nullable = false)
    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    @Basic
    @Column(name = "position_id", nullable = false)
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "mail", nullable = true, length = 50)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "birth_date", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "pid_number", nullable = true, length = 50)
    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }

    @Basic
    @Column(name = "phone_number", nullable = true, length = 250)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 500)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "status_id", nullable = true)
    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personal personal = (Personal) o;

        if (id != personal.id) return false;
        if (organisationId != personal.organisationId) return false;
        if (structureId != personal.structureId) return false;
        if (positionId != personal.positionId) return false;
        if (firstName != null ? !firstName.equals(personal.firstName) : personal.firstName != null) return false;
        if (lastName != null ? !lastName.equals(personal.lastName) : personal.lastName != null) return false;
        if (mail != null ? !mail.equals(personal.mail) : personal.mail != null) return false;
        if (birthDate != null ? !birthDate.equals(personal.birthDate) : personal.birthDate != null) return false;
        if (pidNumber != null ? !pidNumber.equals(personal.pidNumber) : personal.pidNumber != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(personal.phoneNumber) : personal.phoneNumber != null)
            return false;
        if (address != null ? !address.equals(personal.address) : personal.address != null) return false;
        if (groupId != null ? !groupId.equals(personal.groupId) : personal.groupId != null) return false;
        if (statusId != null ? !statusId.equals(personal.statusId) : personal.statusId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + organisationId;
        result = 31 * result + structureId;
        result = 31 * result + positionId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (pidNumber != null ? pidNumber.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + (statusId != null ? statusId.hashCode() : 0);
        return result;
    }
}
