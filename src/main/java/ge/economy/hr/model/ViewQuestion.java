package ge.economy.hr.model;

import javax.persistence.*;

@Entity
@Table(name = "view_question", schema = "hr")
public class ViewQuestion {
    private Integer id;
    private Integer testId;
    private String title;
    private String description;
    private Integer answerGroupId;
    private Integer questionTypeId;
    private String questionTypeName;
    private String answerGroupName;
    private Integer positionId;
    private String positionName;

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
    @Column(name = "title", nullable = false, length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "question_type_id", nullable = false)
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "question_type_name", nullable = false, length = 50)
    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    @Basic
    @Column(name = "answer_group_name", nullable = true, length = 100)
    public String getAnswerGroupName() {
        return answerGroupName;
    }

    public void setAnswerGroupName(String answerGroupName) {
        this.answerGroupName = answerGroupName;
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
    @Column(name = "position_name", nullable = false, length = 150)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewQuestion that = (ViewQuestion) o;

        if (id != that.id) return false;
        if (testId != that.testId) return false;
        if (questionTypeId != that.questionTypeId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (answerGroupId != null ? !answerGroupId.equals(that.answerGroupId) : that.answerGroupId != null)
            return false;
        if (questionTypeName != null ? !questionTypeName.equals(that.questionTypeName) : that.questionTypeName != null)
            return false;
        if (answerGroupName != null ? !answerGroupName.equals(that.answerGroupName) : that.answerGroupName != null)
            return false;
        if (positionId != null ? !positionId.equals(that.positionId) : that.positionId != null) return false;
        if (positionName != null ? !positionName.equals(that.positionName) : that.positionName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + testId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (answerGroupId != null ? answerGroupId.hashCode() : 0);
        result = 31 * result + questionTypeId;
        result = 31 * result + (questionTypeName != null ? questionTypeName.hashCode() : 0);
        result = 31 * result + (answerGroupName != null ? answerGroupName.hashCode() : 0);
        result = 31 * result + (positionId != null ? positionId.hashCode() : 0);
        result = 31 * result + (positionName != null ? positionName.hashCode() : 0);
        return result;
    }
}
