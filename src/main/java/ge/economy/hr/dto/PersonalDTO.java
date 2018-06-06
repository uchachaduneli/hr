/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.hr.misc.JsonDateSerializeSupport;
import ge.economy.hr.model.Personal;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonalDTO {
    private Integer id;
    private Integer organisationId;
    private Integer structureId;
    private Integer positionId;
    private String firstName;
    private String lastName;
    private String mail;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date birthDate;
    private String pidNumber;
    private String phoneNumber;
    private String address;
    private Integer groupId;
    private Integer statusId;
    private String text;
    private String fullName;
    private String positionName;
    private String structureName;
    private String file;
    private String fileName;
    private String hrRecord;
    private String hrRecordName;
    private String cv;
    private String cvName;
    private String diploma;
    private String diplomaName;
    private String idCard;
    private String idCardName;
    private String convictionCertificate;
    private String convictionCertificateName;
    private String drugReference;
    private String drugReferenceName;
    private String oathEmployee;
    private String oathEmployeeName;
    private String receiptOfEmployee;
    private String receiptOfEmployeeName;
    private String orderOfAppointment;
    private String orderOfAppointmentName;
    private String orderOfPosition;
    private String orderOfPositionName;
    private String orderOfPromotion;
    private String orderOfPromotionName;
    private String maternityLeave;
    private String maternityLeaveName;
    private String impositionOfDuties;
    private String impositionOfDutiesName;
    private String orderOfDismissal;
    private String orderOfDismissalName;
    private String certificates;
    private String certificatesName;
    private PositionDTO position;
    private List<PersonalDocumentDTO> documents;
    private List<String> rights;
    private boolean leaf = true;

    public static PersonalDTO parse(Personal obj) {
        PersonalDTO objDTO = new PersonalDTO();
        objDTO.setId(obj.getId());
        objDTO.setOrganisationId(obj.getOrganisationId());
        objDTO.setStructureId(obj.getStructureId());
        objDTO.setPositionId(obj.getPositionId());
        objDTO.setFirstName(obj.getFirstName());
        objDTO.setLastName(obj.getLastName());
        objDTO.setMail(obj.getMail());
        objDTO.setBirthDate(obj.getBirthDate());
        objDTO.setPidNumber(obj.getPidNumber());
        objDTO.setPhoneNumber(obj.getPhoneNumber());
        objDTO.setAddress(obj.getAddress());
        objDTO.setGroupId(obj.getGroupId());
        objDTO.setStatusId(obj.getStatusId());
        return objDTO;
    }

    public static List<PersonalDTO> parseToList(List<Personal> list) {

        List<PersonalDTO> dTOs = new ArrayList<PersonalDTO>();
        for (Personal p : list) {
            dTOs.add(PersonalDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Integer organisationId) {
        this.organisationId = organisationId;
    }

    public Integer getStructureId() {
        return structureId;
    }

    public void setStructureId(Integer structureId) {
        this.structureId = structureId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPidNumber() {
        return pidNumber;
    }

    public void setPidNumber(String pidNumber) {
        this.pidNumber = pidNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHrRecord() {
        return hrRecord;
    }

    public void setHrRecord(String hrRecord) {
        this.hrRecord = hrRecord;
    }

    public String getHrRecordName() {
        return hrRecordName;
    }

    public void setHrRecordName(String hrRecordName) {
        this.hrRecordName = hrRecordName;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName;
    }

    public String getDiploma() {
        return diploma;
    }

    public void setDiploma(String diploma) {
        this.diploma = diploma;
    }

    public String getDiplomaName() {
        return diplomaName;
    }

    public void setDiplomaName(String diplomaName) {
        this.diplomaName = diplomaName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getConvictionCertificate() {
        return convictionCertificate;
    }

    public void setConvictionCertificate(String convictionCertificate) {
        this.convictionCertificate = convictionCertificate;
    }

    public String getConvictionCertificateName() {
        return convictionCertificateName;
    }

    public void setConvictionCertificateName(String convictionCertificateName) {
        this.convictionCertificateName = convictionCertificateName;
    }

    public String getDrugReference() {
        return drugReference;
    }

    public void setDrugReference(String drugReference) {
        this.drugReference = drugReference;
    }

    public String getDrugReferenceName() {
        return drugReferenceName;
    }

    public void setDrugReferenceName(String drugReferenceName) {
        this.drugReferenceName = drugReferenceName;
    }

    public String getOathEmployee() {
        return oathEmployee;
    }

    public void setOathEmployee(String oathEmployee) {
        this.oathEmployee = oathEmployee;
    }

    public String getOathEmployeeName() {
        return oathEmployeeName;
    }

    public void setOathEmployeeName(String oathEmployeeName) {
        this.oathEmployeeName = oathEmployeeName;
    }

    public String getReceiptOfEmployee() {
        return receiptOfEmployee;
    }

    public void setReceiptOfEmployee(String receiptOfEmployee) {
        this.receiptOfEmployee = receiptOfEmployee;
    }

    public String getReceiptOfEmployeeName() {
        return receiptOfEmployeeName;
    }

    public void setReceiptOfEmployeeName(String receiptOfEmployeeName) {
        this.receiptOfEmployeeName = receiptOfEmployeeName;
    }

    public String getOrderOfAppointment() {
        return orderOfAppointment;
    }

    public void setOrderOfAppointment(String orderOfAppointment) {
        this.orderOfAppointment = orderOfAppointment;
    }

    public String getOrderOfAppointmentName() {
        return orderOfAppointmentName;
    }

    public void setOrderOfAppointmentName(String orderOfAppointmentName) {
        this.orderOfAppointmentName = orderOfAppointmentName;
    }

    public String getOrderOfPosition() {
        return orderOfPosition;
    }

    public void setOrderOfPosition(String orderOfPosition) {
        this.orderOfPosition = orderOfPosition;
    }

    public String getOrderOfPositionName() {
        return orderOfPositionName;
    }

    public void setOrderOfPositionName(String orderOfPositionName) {
        this.orderOfPositionName = orderOfPositionName;
    }

    public String getOrderOfPromotion() {
        return orderOfPromotion;
    }

    public void setOrderOfPromotion(String orderOfPromotion) {
        this.orderOfPromotion = orderOfPromotion;
    }

    public String getOrderOfPromotionName() {
        return orderOfPromotionName;
    }

    public void setOrderOfPromotionName(String orderOfPromotionName) {
        this.orderOfPromotionName = orderOfPromotionName;
    }

    public String getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(String maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public String getMaternityLeaveName() {
        return maternityLeaveName;
    }

    public void setMaternityLeaveName(String maternityLeaveName) {
        this.maternityLeaveName = maternityLeaveName;
    }

    public String getImpositionOfDuties() {
        return impositionOfDuties;
    }

    public void setImpositionOfDuties(String impositionOfDuties) {
        this.impositionOfDuties = impositionOfDuties;
    }

    public String getImpositionOfDutiesName() {
        return impositionOfDutiesName;
    }

    public void setImpositionOfDutiesName(String impositionOfDutiesName) {
        this.impositionOfDutiesName = impositionOfDutiesName;
    }

    public String getOrderOfDismissal() {
        return orderOfDismissal;
    }

    public void setOrderOfDismissal(String orderOfDismissal) {
        this.orderOfDismissal = orderOfDismissal;
    }

    public String getOrderOfDismissalName() {
        return orderOfDismissalName;
    }

    public void setOrderOfDismissalName(String orderOfDismissalName) {
        this.orderOfDismissalName = orderOfDismissalName;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getCertificatesName() {
        return certificatesName;
    }

    public void setCertificatesName(String certificatesName) {
        this.certificatesName = certificatesName;
    }

    public PositionDTO getPosition() {
        return position;
    }

    public void setPosition(PositionDTO position) {
        this.position = position;
    }

    public List<PersonalDocumentDTO> getDocuments() {
        return documents;
    }

    public void setDocuments(List<PersonalDocumentDTO> documents) {
        this.documents = documents;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(List<String> rights) {
        this.rights = rights;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
}
