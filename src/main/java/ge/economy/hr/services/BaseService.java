package ge.economy.hr.services;

import ge.economy.hr.dao.*;
import ge.economy.hr.dto.*;
import ge.economy.hr.error.InvalidDataExeption;
import ge.economy.hr.error.OperationNotPermittedExeption;
import ge.economy.hr.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author nino lomineishvili
 */
@Service
public class BaseService {

    @Autowired
    protected AnswerDao answerDao;
    @Autowired
    protected AnswerGroupDao answerGroupDao;
    @Autowired
    protected ViewAnswerGroupDao viewAnswerGroupDao;
    @Autowired
    protected AnswerTypeDao answerTypeDao;
    @Autowired
    protected QuestionTypeDao questionTypeDao;
    @Autowired
    protected QuestionDao questionDao;
    @Autowired
    protected QuestionPositionDao questionPositionDao;
    @Autowired
    protected ViewQuestionDao viewQuestionDao;
    @Autowired
    protected TestDao testDao;
    @Autowired
    protected TestActiveStatusDao testActiveStatusDao;
    @Autowired
    protected TestTypeDao testTypeDao;
    @Autowired
    protected ViewTestDao viewTestDao;

    public List<AnswerDTO> getAnswers() {
        return AnswerDTO.parseToList(answerDao.getAll(Answer.class));
    }

    public List<AnswerDTO> getAnswersByAnswerGroup(int answerGroupId) {
        return AnswerDTO.parseToList(answerDao.getAnswersByAnswerGroupId(answerGroupId));
    }

    public List<ViewAnswerGroupDTO> getAnswerGroups() {
        return ViewAnswerGroupDTO.parseToList(viewAnswerGroupDao.getAll(ViewAnswerGroup.class));
    }

    public List<AnswerTypeDTO> getAnswerTypes() {
        return AnswerTypeDTO.parseToList(answerTypeDao.getAll(AnswerType.class));
    }

    public List<QuestionTypeDTO> getQuestionTypes() {
        return QuestionTypeDTO.parseToList(questionTypeDao.getAll(QuestionType.class));
    }

    public List<ViewQuestionDTO> getQuestion() {
        return ViewQuestionDTO.parseToList(viewQuestionDao.getAllOrderedById());
    }

    public List<QuestionPositionDTO> getQuestionPositions() {
        return QuestionPositionDTO.parseToList(questionPositionDao.getAllOrderedById());
    }

    public List<ViewQuestionDTO> getQuestionByTest(int testId) {
        return ViewQuestionDTO.parseToList(viewQuestionDao.getQuestionByTest(testId));
    }

    public List<ViewTestDTO> getTest(int organisationId) {
        return ViewTestDTO.parseToList(viewTestDao.getViewTestByOrganizationId(organisationId));
    }

    public List<TestActiveStatusDTO> getTestActiveStatus() {
        return TestActiveStatusDTO.parseToList(testActiveStatusDao.getAll(TestActiveStatus.class));
    }

    public List<TestTypeDTO> getTestTypes() {
        return TestTypeDTO.parseToList(testTypeDao.getAll(TestType.class));
    }

    @Transactional(rollbackFor = Throwable.class)
    public Test mergeTest(Test o) {
        if (o.getId() != null && o.getId() > 0) {
            testDao.update(o);
        } else {
            testDao.create(o);
        }
        return o;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Answer mergeAnswer(Answer o) {
        if (o.getId() != null && o.getId() > 0) {
            answerDao.update(o);
        } else {
            answerDao.create(o);
        }
        return o;
    }

    @Transactional(rollbackFor = Throwable.class)
    public Question mergeQuestion(Question o) throws InvalidDataExeption {
        if (o.getAnswerGroupId() == 0) {
            throw new InvalidDataExeption("აირჩიეთ პასუხის ჯგუფი");
        }
        if (o.getId() != null && o.getId() > 0) {
            questionDao.update(o);
        } else {
            questionDao.create(o);
        }
        return o;
    }

    @Transactional(rollbackFor = Throwable.class)
    public AnswerGroup mergeAnswerGroup(AnswerGroup o) {
        if (o.getId() != null && o.getId() > 0) {
            answerGroupDao.update(o);
        } else {
            answerGroupDao.create(o);
        }
        return o;
    }

    @Transactional(rollbackFor = Throwable.class)
    public AnswerType mergeAnswerType(AnswerType o) {
        if (o.getId() != null && o.getId() > 0) {
            answerTypeDao.update(o);
        } else {
            answerTypeDao.create(o);
        }
        return o;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void removeTest(int id) throws OperationNotPermittedExeption {
        List<Question> ps = questionDao.getQuestionByTest(id);
        if (!ps.isEmpty()) {
            throw new OperationNotPermittedExeption("Unable to remove Test , it is used by question");
        }
        Test tmp = testDao.find(Test.class, id);
        testDao.delete(tmp);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void removeQuestion(int id) throws OperationNotPermittedExeption {
        Question tmp = questionDao.find(Question.class, id);
        questionDao.delete(tmp);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void removeAnswerType(int id) throws OperationNotPermittedExeption {
        List<AnswerGroup> ps = answerGroupDao.getAnswerGroupByTypeId(id);
        if (!ps.isEmpty()) {
            throw new OperationNotPermittedExeption("Unable to remove Answer Type , it is used by answerGroup");
        }
        AnswerType tmp = questionDao.find(AnswerType.class, id);
        answerTypeDao.delete(tmp);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void removeAnswerGroup(int id) throws OperationNotPermittedExeption {
        List<Answer> ps = answerDao.getAnswersByAnswerGroupId(id);
        if (!ps.isEmpty()) {
            throw new OperationNotPermittedExeption("Unable to remove Answer Group , it is used by answer");
        }
        AnswerGroup tmp = questionDao.find(AnswerGroup.class, id);
        answerGroupDao.delete(tmp);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void removeAnswer(int id) throws OperationNotPermittedExeption {
        List<Answer> ps = answerDao.getAll(Answer.class);
        if (ps.size() < 2) {
            throw new OperationNotPermittedExeption("Unable to remove Answer, it is last answer");
        }
        Answer tmp = questionDao.find(Answer.class, id);
        answerDao.delete(tmp);
    }

    protected boolean isNewRecord(Object o) {
        Class c = o.getClass().getSuperclass();
        try {
            Field f = c.getDeclaredField("id");
            f.setAccessible(true);
            Integer i = (Integer) f.get(o);
            return !(i != null && i > 0);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            return true;
        }

    }
}
