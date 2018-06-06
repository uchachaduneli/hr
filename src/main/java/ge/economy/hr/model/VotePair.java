package ge.economy.hr.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "vote_pair", schema = "hr")
public class VotePair {
    private Integer id;
    private Integer testId;
    private Integer voterId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer transactionId;
    private Timestamp insertDate;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "test_id", nullable = true)
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "voter_id", nullable = true)
    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    @Basic
    @Column(name = "candidate_id", nullable = true)
    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    @Basic
    @Column(name = "department_id", nullable = true)
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "transaction_id", nullable = true)
    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "insert_date", nullable = true, insertable = false, updatable = false)
    public Timestamp getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Timestamp insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotePair votePair = (VotePair) o;

        if (id != votePair.id) return false;
        if (testId != null ? !testId.equals(votePair.testId) : votePair.testId != null) return false;
        if (voterId != null ? !voterId.equals(votePair.voterId) : votePair.voterId != null) return false;
        if (candidateId != null ? !candidateId.equals(votePair.candidateId) : votePair.candidateId != null)
            return false;
        if (departmentId != null ? !departmentId.equals(votePair.departmentId) : votePair.departmentId != null)
            return false;
        if (transactionId != null ? !transactionId.equals(votePair.transactionId) : votePair.transactionId != null)
            return false;
        if (insertDate != null ? !insertDate.equals(votePair.insertDate) : votePair.insertDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (testId != null ? testId.hashCode() : 0);
        result = 31 * result + (voterId != null ? voterId.hashCode() : 0);
        result = 31 * result + (candidateId != null ? candidateId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (transactionId != null ? transactionId.hashCode() : 0);
        result = 31 * result + (insertDate != null ? insertDate.hashCode() : 0);
        return result;
    }
}
