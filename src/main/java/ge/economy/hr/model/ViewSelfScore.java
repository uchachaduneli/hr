package ge.economy.hr.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "view_self_score", schema = "hr")
public class ViewSelfScore {
    private Integer testId;
    private Integer questionTypeId;
    private String questionTypeName;
    private Integer candidateId;
    private Integer departmentId;
    private String departmentName;
    private String candidateFirstName;
    private String candidateLastName;
    private BigInteger maxScore;
    private long selfScore;
    private Integer candidateStructureId;

    @Id
    @Column(name = "test_id", nullable = true)
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "question_type_id", nullable = true)
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "question_type_name", nullable = true, length = 50)
    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
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
    @Column(name = "department_name", nullable = true, length = 500)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "candidate_first_name", nullable = true, length = 100)
    public String getCandidateFirstName() {
        return candidateFirstName;
    }

    public void setCandidateFirstName(String candidateFirstName) {
        this.candidateFirstName = candidateFirstName;
    }

    @Basic
    @Column(name = "candidate_last_name", nullable = true, length = 100)
    public String getCandidateLastName() {
        return candidateLastName;
    }

    public void setCandidateLastName(String candidateLastName) {
        this.candidateLastName = candidateLastName;
    }

    @Basic
    @Column(name = "max_score", nullable = true, precision = 0)
    public BigInteger getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigInteger maxScore) {
        this.maxScore = maxScore;
    }

    @Basic
    @Column(name = "self_score", nullable = false, precision = 0)
    public long getSelfScore() {
        return selfScore;
    }

    public void setSelfScore(long selfScore) {
        this.selfScore = selfScore;
    }

    @Basic
    @Column(name = "candidate_structure_id", nullable = true)
    public Integer getCandidateStructureId() {
        return candidateStructureId;
    }

    public void setCandidateStructureId(Integer candidateStructureId) {
        this.candidateStructureId = candidateStructureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewSelfScore that = (ViewSelfScore) o;

        if (selfScore != that.selfScore) return false;
        if (testId != null ? !testId.equals(that.testId) : that.testId != null) return false;
        if (questionTypeId != null ? !questionTypeId.equals(that.questionTypeId) : that.questionTypeId != null)
            return false;
        if (questionTypeName != null ? !questionTypeName.equals(that.questionTypeName) : that.questionTypeName != null)
            return false;
        if (candidateId != null ? !candidateId.equals(that.candidateId) : that.candidateId != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        if (candidateFirstName != null ? !candidateFirstName.equals(that.candidateFirstName) : that.candidateFirstName != null)
            return false;
        if (candidateLastName != null ? !candidateLastName.equals(that.candidateLastName) : that.candidateLastName != null)
            return false;
        if (maxScore != null ? !maxScore.equals(that.maxScore) : that.maxScore != null) return false;
        if (candidateStructureId != null ? !candidateStructureId.equals(that.candidateStructureId) : that.candidateStructureId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId != null ? testId.hashCode() : 0;
        result = 31 * result + (questionTypeId != null ? questionTypeId.hashCode() : 0);
        result = 31 * result + (questionTypeName != null ? questionTypeName.hashCode() : 0);
        result = 31 * result + (candidateId != null ? candidateId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (candidateFirstName != null ? candidateFirstName.hashCode() : 0);
        result = 31 * result + (candidateLastName != null ? candidateLastName.hashCode() : 0);
        result = 31 * result + (maxScore != null ? maxScore.hashCode() : 0);
        result = 31 * result + (int) (selfScore ^ (selfScore >>> 32));
        result = 31 * result + (candidateStructureId != null ? candidateStructureId.hashCode() : 0);
        return result;
    }
}
