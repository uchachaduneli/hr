package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "organisation")
public class Organisation {
    private Integer id;
    private String name;
    private String mail;
    private String domain;
    private String dcName;
    private Integer parentTreeId;
    private String ldapServerUrl;
    private String username;
    private String password;

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
    @Column(name = "name", nullable = false, length = 500)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "mail", nullable = false, length = 100)
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Basic
    @Column(name = "domain", nullable = false, length = 100)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Basic
    @Column(name = "dc_name", nullable = false, length = 100)
    public String getDcName() {
        return dcName;
    }

    public void setDcName(String dcName) {
        this.dcName = dcName;
    }

    @Basic
    @Column(name = "parent_tree_id", nullable = false)
    public Integer getParentTreeId() {
        return parentTreeId;
    }

    public void setParentTreeId(Integer parentTreeId) {
        this.parentTreeId = parentTreeId;
    }

    @Basic
    @Column(name = "ldap_server_url", nullable = false, length = 100)
    public String getLdapServerUrl() {
        return ldapServerUrl;
    }

    public void setLdapServerUrl(String ldapServerUrl) {
        this.ldapServerUrl = ldapServerUrl;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 100)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organisation that = (Organisation) o;

        if (id != that.id) return false;
        if (parentTreeId != that.parentTreeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) return false;
        if (dcName != null ? !dcName.equals(that.dcName) : that.dcName != null) return false;
        if (ldapServerUrl != null ? !ldapServerUrl.equals(that.ldapServerUrl) : that.ldapServerUrl != null)
            return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (dcName != null ? dcName.hashCode() : 0);
        result = 31 * result + parentTreeId;
        result = 31 * result + (ldapServerUrl != null ? ldapServerUrl.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
