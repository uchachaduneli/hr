package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "question")
public class Question {
    private Integer id;
    private Integer testId;
    private String title;
    private Integer questionTypeId;
    private String description;
    private Integer answerGroupId;
    private Integer positionId;

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
    @Column(name = "test_id", nullable = false)
    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
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
    @Column(name = "question_type_id", nullable = false)
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
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
    @Column(name = "answer_group_id", nullable = true)
    public Integer getAnswerGroupId() {
        return answerGroupId;
    }

    public void setAnswerGroupId(Integer answerGroupId) {
        this.answerGroupId = answerGroupId;
    }

    @Basic
    @Column(name = "position_id", nullable = true)
    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (id != question.id) return false;
        if (testId != question.testId) return false;
        if (questionTypeId != question.questionTypeId) return false;
        if (title != null ? !title.equals(question.title) : question.title != null) return false;
        if (description != null ? !description.equals(question.description) : question.description != null)
            return false;
        if (answerGroupId != null ? !answerGroupId.equals(question.answerGroupId) : question.answerGroupId != null)
            return false;
        if (positionId != null ? !positionId.equals(question.positionId) : question.positionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + questionTypeId;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (answerGroupId != null ? answerGroupId.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        return result;
    }
}
