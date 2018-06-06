package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_test_question_type", schema = "hr")
public class ViewTestQuestionType {
    private Integer testId;
    private Integer questionTypeId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewTestQuestionType that = (ViewTestQuestionType) o;

        if (testId != that.testId) return false;
        if (questionTypeId != that.questionTypeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = testId;
        result = 31 * result + questionTypeId;
        return result;
    }
}
