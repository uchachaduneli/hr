package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_max_score", schema = "hr")
public class ViewMaxScore {
    private Integer testId;
    private Integer questionTypeId;
    private Integer questionId;
    private Integer positionId;
    private Integer maxScore;

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
    @Column(name = "question_id", nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "position_id", nullable = true)
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "max_score", nullable = true)
    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewMaxScore that = (ViewMaxScore) o;

        if (testId != that.testId) return false;
        if (questionTypeId != that.questionTypeId) return false;
        if (questionId != that.questionId) return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
        if (maxScore != null ? !maxScore.equals(that.maxScore) : that.maxScore != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionTypeId;
        result = 31 * result + questionId;
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (maxScore != null ? maxScore.hashCode() : 0);
        return result;
    }
}
