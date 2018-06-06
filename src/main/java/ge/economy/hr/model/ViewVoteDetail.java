package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_vote_detail", schema = "hr")
public class ViewVoteDetail {
    private Integer testId;
    private Integer questionTypeId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer voterId;
    private String candidateFirstName;
    private String candidateLastName;
    private String departmentName;
    private Integer score;
    private Integer answerId;

    @Id
    @Column(name = "test_id", nullable = false)
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    @Basic
    @Column(name = "question_type_id", nullable = false)
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
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
    @Column(name = "voter_id", nullable = false)
    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
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
    @Column(name = "department_name", nullable = true, length = 500)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "score", nullable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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

        ViewVoteDetail that = (ViewVoteDetail) o;

        if (testId != that.testId) return false;
        if (questionTypeId != that.questionTypeId) return false;
        if (voterId != that.voterId) return false;
        if (candidateId != null ? !candidateId.equals(that.candidateId) : that.candidateId != null) return false;
        if (departmentId != null ? !departmentId.equals(that.departmentId) : that.departmentId != null) return false;
        if (candidateFirstName != null ? !candidateFirstName.equals(that.candidateFirstName) : that.candidateFirstName != null)
            return false;
        if (candidateLastName != null ? !candidateLastName.equals(that.candidateLastName) : that.candidateLastName != null)
            return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        if (score != null ? !score.equals(that.score) : that.score != null) return false;
        if (answerId != null ? !answerId.equals(that.answerId) : that.answerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionTypeId;
        result = 31 * result + (candidateId != null ? candidateId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + voterId;
        result = 31 * result + (candidateFirstName != null ? candidateFirstName.hashCode() : 0);
        result = 31 * result + (candidateLastName != null ? candidateLastName.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        return result;
    }
}
