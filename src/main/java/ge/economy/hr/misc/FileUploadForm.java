package ge.economy.hr.misc;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author invisible
 */
public class FileUploadForm {

    private MultipartFile hrRecord;
    private MultipartFile cv;
    private MultipartFile diploma;
    private MultipartFile idCard;
    private MultipartFile convictionCertificate;
    private MultipartFile drugReference;
    private MultipartFile oathEmployee;
    private MultipartFile receiptOfEmployee;
    private MultipartFile orderOfAppointment;
    private MultipartFile orderOfPosition;
    private MultipartFile orderOfPromotion;
    private MultipartFile maternityLeave;
    private MultipartFile impositionOfDuties;
    private MultipartFile orderOfDismissal;
    private MultipartFile certificates;

    public MultipartFile getIdCard() {
        return idCard;
    }

    public void setIdCard(MultipartFile idCard) {
        this.idCard = idCard;
    }

    public MultipartFile getConvictionCertificate() {
        return convictionCertificate;
    }

    public void setConvictionCertificate(MultipartFile convictionCertificate) {
        this.convictionCertificate = convictionCertificate;
    }

    public MultipartFile getHrRecord() {
        return hrRecord;
    }

    public void setHrRecord(MultipartFile hrRecord) {
        this.hrRecord = hrRecord;
    }

    public MultipartFile getCv() {
        return cv;
    }

    public void setCv(MultipartFile cv) {
        this.cv = cv;
    }

    public MultipartFile getDiploma() {
        return diploma;
    }

    public void setDiploma(MultipartFile diploma) {
        this.diploma = diploma;
    }

    public MultipartFile getDrugReference() {
        return drugReference;
    }

    public void setDrugReference(MultipartFile drugReference) {
        this.drugReference = drugReference;
    }

    public MultipartFile getOathEmployee() {
        return oathEmployee;
    }

    public void setOathEmployee(MultipartFile oathEmployee) {
        this.oathEmployee = oathEmployee;
    }

    public MultipartFile getReceiptOfEmployee() {
        return receiptOfEmployee;
    }

    public void setReceiptOfEmployee(MultipartFile receiptOfEmployee) {
        this.receiptOfEmployee = receiptOfEmployee;
    }

    public MultipartFile getOrderOfAppointment() {
        return orderOfAppointment;
    }

    public void setOrderOfAppointment(MultipartFile orderOfAppointment) {
        this.orderOfAppointment = orderOfAppointment;
    }

    public MultipartFile getOrderOfPosition() {
        return orderOfPosition;
    }

    public void setOrderOfPosition(MultipartFile orderOfPosition) {
        this.orderOfPosition = orderOfPosition;
    }

    public MultipartFile getOrderOfPromotion() {
        return orderOfPromotion;
    }

    public void setOrderOfPromotion(MultipartFile orderOfPromotion) {
        this.orderOfPromotion = orderOfPromotion;
    }

    public MultipartFile getMaternityLeave() {
        return maternityLeave;
    }

    public void setMaternityLeave(MultipartFile maternityLeave) {
        this.maternityLeave = maternityLeave;
    }

    public MultipartFile getImpositionOfDuties() {
        return impositionOfDuties;
    }

    public void setImpositionOfDuties(MultipartFile impositionOfDuties) {
        this.impositionOfDuties = impositionOfDuties;
    }

    public MultipartFile getOrderOfDismissal() {
        return orderOfDismissal;
    }

    public void setOrderOfDismissal(MultipartFile orderOfDismissal) {
        this.orderOfDismissal = orderOfDismissal;
    }

    public MultipartFile getCertificates() {
        return certificates;
    }

    public void setCertificates(MultipartFile certificates) {
        this.certificates = certificates;
    }

}
