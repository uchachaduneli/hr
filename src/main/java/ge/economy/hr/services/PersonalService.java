package ge.economy.hr.services;

import ge.economy.hr.dao.*;
import ge.economy.hr.dto.PersonalDTO;
import ge.economy.hr.dto.PersonalDocumentDTO;
import ge.economy.hr.dto.ViewPersonalDTO;
import ge.economy.hr.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author invisible
 */
@Service
public class PersonalService {

    @Autowired
    private PersonalDao personalDao;
    @Autowired
    private ViewPersonalDao viewPersonalDao;
    @Autowired
    private PersonalDocumentDao documentDao;
    @Autowired
    private PositionDao positionDao;
    @Autowired
    private StructureDao structureDao;
    @Autowired
    private VotePairDao votePairDao;
    @Autowired
    private SysGroupPermissionDao sysGroupPermissionDao;
    @Autowired
    private SysPermissionDao sysPermissionDao;
    @Autowired
    private FileService fileServices;

    private static final int ACTIVE_STATUS = 1;
    private static final int INACTIVE_STATUS = 2;

    private static final String UPLOAD_DIR = "C:\\Program Files\\glassfish4\\glassfish\\domains\\domain1\\docroot\\upload\\";

    public PersonalDTO getPersonalById(int id) {
        PersonalDTO p = PersonalDTO.parse(personalDao.find(Personal.class, id));
        p.setPositionName(((Position) positionDao.find(Position.class, p.getPositionId())).getName());
        p.setStructureName(((Structure) structureDao.find(Structure.class, p.getStructureId())).getName());
        p.setDocuments(PersonalDocumentDTO.parseToList(documentDao.getPersonalDocumentByPersonalId(id)));
        return p;
    }

    public List<String> selectRightsByPersonal(int personalId) {
        Personal p = personalDao.find(Personal.class, personalId);
        List<String> rights = new ArrayList<>();
        List<SysGroupPermission> groupPermissions = sysGroupPermissionDao.getSysGroupPermissionByGroupId(p.getGroupId());
        for (SysGroupPermission _p : groupPermissions) {
            rights.add(((SysPermission) sysPermissionDao.find(SysPermission.class, _p.getPermissionId())).getName());
        }
        return rights;
    }

    public List<PersonalDTO> getPersonalByTest(int testId) {
        List<VotePair> pairs = votePairDao.getVotePairByTestId(testId);
        List<Personal> personals = new ArrayList<>();
        Personal p;
        for (VotePair v : pairs) {
            p = personalDao.find(Personal.class, v.getCandidateId());
            if (!personals.contains(p)) {
                personals.add(p);
            }
        }
        return PersonalDTO.parseToList(personals);
    }

    public List<PersonalDTO> getAllPersonal(int organisationId) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("statusId", ACTIVE_STATUS));
        criterias.add(new ParamValuePair("organisationId", organisationId));
        return PersonalDTO.parseToList(personalDao.getAllByParamValue(Personal.class, criterias));
    }

    public List<PersonalDTO> getNonManagementPersonal(List<Integer> structures) {
        List<Integer> _positions = new ArrayList<>();
        _positions.add(1);
        _positions.add(2);
        _positions.add(3);
        _positions.add(4);
        _positions.add(5);
        _positions.add(6);
        _positions.add(12);
        _positions.add(13);
        return PersonalDTO.parseToList(personalDao.getNonManagementPersonal(structures, _positions, ACTIVE_STATUS));
    }

    public List<ViewPersonalDTO> getPersonalByParent(int parentId) {
        List<ParamValuePair> criterias = new ArrayList<>();
        if (parentId != -1) {
            criterias.add(new ParamValuePair("structureId", parentId));
        }
        criterias.add(new ParamValuePair("statusId", ACTIVE_STATUS));
        return ViewPersonalDTO.parseToList(viewPersonalDao.getAllByParamValue(ViewPersonal.class, criterias));
    }

    public PersonalDTO getPersonalByMail(String mail) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("statusId", ACTIVE_STATUS));
        criterias.add(new ParamValuePair("mail", mail));
        List<Personal> pList = personalDao.getAllByParamValue(Personal.class, criterias);
        if (pList.isEmpty()) {
            return null;
        }
        return PersonalDTO.parse(pList.get(0));
    }

    public List<PersonalDTO> getPersonalByStructureId(int structureId) {
        List<PersonalDTO> personal = PersonalDTO.parseToList(personalDao.getPersonalByStructureId(structureId));
        for (PersonalDTO p : personal) {
            p.setPositionName(((Position) positionDao.find(Position.class, p.getPositionId())).getName());
        }
        return personal;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void savePersonal(ge.economy.hr.request.Personal p) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("mail", p.getMail()));
        List<Personal> pList = personalDao.getAllByParamValue(Personal.class, criterias);
        Personal pp = null;
        if (!pList.isEmpty()) {
            pp = pList.get(0);
        }
        if (pp != null) {
            pp.setPositionId(p.getPositionId());
            pp.setStructureId(p.getStructureId());
            pp.setStatusId(ACTIVE_STATUS);
            personalDao.update(pp);
        } else {
            personalDao.create(pp);
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void updatePersonal(ge.economy.hr.request.Personal p) throws Exception {
        if (p.getHrRecord() != null) {
            savePersonalDocument(p.getId(), p.getHrRecord(), PersonalDocumentType.HRRECORDSHEET.getValue(), p.getHrRecordName());
        }
        if (p.getCv() != null) {
            savePersonalDocument(p.getId(), p.getCv(), PersonalDocumentType.CV.getValue(), p.getCvName());
        }
        if (p.getDiploma() != null) {
            savePersonalDocument(p.getId(), p.getDiploma(), PersonalDocumentType.DIPLOMA.getValue(), p.getDiplomaName());
        }
        if (p.getIdCard() != null) {
            savePersonalDocument(p.getId(), p.getIdCard(), PersonalDocumentType.IDCARD.getValue(), p.getIdCardName());
        }
        if (p.getConvictionCertificate() != null) {
            savePersonalDocument(p.getId(), p.getConvictionCertificate(), PersonalDocumentType.CONVICTIONCERTIFICATE.getValue(), p.getConvictionCertificateName());
        }
        if (p.getDrugReference() != null) {
            savePersonalDocument(p.getId(), p.getDrugReference(), PersonalDocumentType.DRUGREFERNCE.getValue(), p.getDrugReferenceName());
        }
        if (p.getOathEmployee() != null) {
            savePersonalDocument(p.getId(), p.getOathEmployee(), PersonalDocumentType.OATHOFEMPLOYEE.getValue(), p.getOathEmployeeName());
        }
        if (p.getReceiptOfEmployee() != null) {
            savePersonalDocument(p.getId(), p.getReceiptOfEmployee(), PersonalDocumentType.RECEIPTOFEMPLOYEE.getValue(), p.getReceiptOfEmployeeName());
        }
        if (p.getOrderOfAppointment() != null) {
            savePersonalDocument(p.getId(), p.getOrderOfAppointment(), PersonalDocumentType.ORDEROFAPPOINTMENT.getValue(), p.getOrderOfAppointmentName());
        }
        if (p.getOrderOfPosition() != null) {
            savePersonalDocument(p.getId(), p.getOrderOfPosition(), PersonalDocumentType.ORDEROFPOSITION.getValue(), p.getOrderOfPositionName());
        }
        if (p.getOrderOfPromotion() != null) {
            savePersonalDocument(p.getId(), p.getOrderOfPromotion(), PersonalDocumentType.ORDEROFPROMOTION.getValue(), p.getOrderOfPromotionName());
        }
        if (p.getMaternityLeave() != null) {
            savePersonalDocument(p.getId(), p.getMaternityLeave(), PersonalDocumentType.MATERNITYLEAVE.getValue(), p.getMaternityLeaveName());
        }
        if (p.getImpositionOfDuties() != null) {
            savePersonalDocument(p.getId(), p.getImpositionOfDuties(), PersonalDocumentType.IMPOSITIONOFDUTIES.getValue(), p.getImpositionOfDutiesName());
        }
        if (p.getOrderOfDismissal() != null) {
            savePersonalDocument(p.getId(), p.getOrderOfDismissal(), PersonalDocumentType.ORDEROFDISMISSAL.getValue(), p.getOrderOfDismissalName());
        }
        if (p.getCertificates() != null) {
            savePersonalDocument(p.getId(), p.getCertificates(), PersonalDocumentType.CERTIFICATES.getValue(), p.getCertificatesName());
        }
        if (p.getFile() != null) {
            savePersonalDocument(p.getId(), p.getFile(), PersonalDocumentType.FOTOS.getValue(), p.getFileName());
        }
        Personal pp = personalDao.find(Personal.class, p.getId());
        pp.setOrganisationId(p.getOrganisationId());
        pp.setStructureId(p.getStructureId());
        pp.setPositionId(p.getPositionId());
        pp.setFirstName(p.getFirstName());
        pp.setLastName(p.getLastName());
        pp.setMail(p.getMail());
        pp.setBirthDate(p.getBirthDate());
        p.setPidNumber(p.getPidNumber());
        pp.setPhoneNumber(p.getPhoneNumber());
        pp.setAddress(p.getAddress());
        pp.setGroupId(p.getGroupId());
        pp.setStatusId(p.getStatusId());

        personalDao.update(pp);
    }

    @Transactional(rollbackFor = Throwable.class)
    public void savePersonalDocument(int personalId, String document, int documentTypeId, String title) throws Exception {
        Date d = new Date();
        String fileName = personalId + "_" + documentTypeId + "_" + Long.toString(d.getTime());
        fileServices.writeFile(personalId + "", fileName, document);
        PersonalDocument pDoc = new PersonalDocument();
        pDoc.setDocumentTypeId(documentTypeId);
        pDoc.setPersonalId(personalId);
        pDoc.setName(fileName);
        pDoc.setTitle(title == null ? fileName : title);
        documentDao.create(pDoc);
    }

    public void deleteUploadedFile(String fileName) {
        File file = new File(UPLOAD_DIR + fileName);
        file.delete();
    }

    @Transactional(rollbackFor = Throwable.class)
    public void deletePersonalFile(int id) {
        PersonalDocument pd = documentDao.find(PersonalDocument.class, id);
        if (pd != null) {
            fileServices.removeFile(pd.getPersonalId().toString(), pd.getName());
            documentDao.delete(pd);
        }
    }

    public List<PersonalDocumentDTO> getPersonalDocument(int personalId) {
        return PersonalDocumentDTO.parseToList(documentDao.getPersonalDocumentByPersonalId(personalId));
    }

    @Transactional(rollbackFor = Throwable.class)
    public void inactivePersonal(List<String> mails, int organisationId) {
        List<Personal> personals = personalDao.getInactivePersonal(mails, organisationId);
        for (Personal p : personals) {
            p.setStatusId(INACTIVE_STATUS);
            personalDao.update(p);
        }
    }

}

enum PersonalDocumentType {

    HRRECORDSHEET(1),
    CV(2),
    DIPLOMA(3),
    IDCARD(4),
    CONVICTIONCERTIFICATE(5),
    DRUGREFERNCE(6),
    OATHOFEMPLOYEE(7),
    RECEIPTOFEMPLOYEE(8),
    ORDEROFAPPOINTMENT(9),
    ORDEROFPOSITION(10),
    ORDEROFPROMOTION(11),
    MATERNITYLEAVE(12),
    IMPOSITIONOFDUTIES(13),
    ORDEROFDISMISSAL(14),
    CERTIFICATES(15),
    FOTOS(16);
    private final int value;

    private PersonalDocumentType(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }

}
