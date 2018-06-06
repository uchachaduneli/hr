package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "vote")
public class Vote {
    private Integer id;
    private Integer testId;
    private Integer voterId;
    private Integer questionId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer answerId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "test_id", nullable = false)
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "voter_id", nullable = false)
    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    @Basic
    @Column(name = "question_id", nullable = true)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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
    @Column(name = "answer_id", nullable = true)
    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (id != vote.id) return false;
        if (testId != vote.testId) return false;
        if (voterId != vote.voterId) return false;
        if (questionId != null ? !questionId.equals(vote.questionId) : vote.questionId != null) return false;
        if (candidateId != null ? !candidateId.equals(vote.candidateId) : vote.candidateId != null) return false;
        if (departmentId != null ? !departmentId.equals(vote.departmentId) : vote.departmentId != null) return false;
        if (answerId != null ? !answerId.equals(vote.answerId) : vote.answerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + voterId;
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (candidateId != null ? candidateId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        return result;
    }
}
