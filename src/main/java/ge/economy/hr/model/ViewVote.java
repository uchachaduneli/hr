package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_vote", schema = "hr")
public class ViewVote {
    private Integer testId;
    private Integer questionTypeId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer questionId;
    private Integer answerId;
    private String candidateFirstName;
    private String candidateLastName;
    private String departmentName;
    private String title;
    private String text;
    private Integer score;
    private Integer voterId;
    private Long sumScore;
    private long countAnswer;

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
    @Column(name = "question_id", nullable = true)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "answer_id", nullable = true)
    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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
    @Column(name = "title", nullable = false, length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "text", nullable = false, length = 500)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
    @Column(name = "voter_id", nullable = false)
    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    @Basic
    @Column(name = "sum_score", nullable = true, precision = 0)
    public Long getSumScore() {
        return sumScore;
    }

    public void setSumScore(Long sumScore) {
        this.sumScore = sumScore;
    }

    @Basic
    @Column(name = "count_answer", nullable = false)
    public long getCountAnswer() {
        return countAnswer;
    }

    public void setCountAnswer(long countAnswer) {
        this.countAnswer = countAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewVote viewVote = (ViewVote) o;

        if (testId != viewVote.testId) return false;
        if (questionTypeId != viewVote.questionTypeId) return false;
        if (voterId != viewVote.voterId) return false;
        if (countAnswer != viewVote.countAnswer) return false;
        if (candidateId != null ? !candidateId.equals(viewVote.candidateId) : viewVote.candidateId != null)
            return false;
        if (departmentId != null ? !departmentId.equals(viewVote.departmentId) : viewVote.departmentId != null)
            return false;
        if (questionId != null ? !questionId.equals(viewVote.questionId) : viewVote.questionId != null) return false;
        if (answerId != null ? !answerId.equals(viewVote.answerId) : viewVote.answerId != null) return false;
        if (candidateFirstName != null ? !candidateFirstName.equals(viewVote.candidateFirstName) : viewVote.candidateFirstName != null)
            return false;
        if (candidateLastName != null ? !candidateLastName.equals(viewVote.candidateLastName) : viewVote.candidateLastName != null)
            return false;
        if (departmentName != null ? !departmentName.equals(viewVote.departmentName) : viewVote.departmentName != null)
            return false;
        if (title != null ? !title.equals(viewVote.title) : viewVote.title != null) return false;
        if (text != null ? !text.equals(viewVote.text) : viewVote.text != null) return false;
        if (score != null ? !score.equals(viewVote.score) : viewVote.score != null) return false;
        if (sumScore != null ? !sumScore.equals(viewVote.sumScore) : viewVote.sumScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionTypeId;
        result = 31 * result + (candidateId != null ? candidateId.hashCode() : 0);
        result = 31 * result + (departmentId != null ? departmentId.hashCode() : 0);
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        result = 31 * result + (candidateFirstName != null ? candidateFirstName.hashCode() : 0);
        result = 31 * result + (candidateLastName != null ? candidateLastName.hashCode() : 0);
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + voterId;
        result = 31 * result + (sumScore != null ? sumScore.hashCode() : 0);
        result = 31 * result + (int) (countAnswer ^ (countAnswer >>> 32));
        return result;
    }
}
