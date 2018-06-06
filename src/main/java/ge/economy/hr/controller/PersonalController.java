package ge.economy.hr.controller;

import ge.economy.hr.controller.auth.LoginInterceptor;
import ge.economy.hr.dto.PersonalDTO;
import ge.economy.hr.dto.StructureDTO;
import ge.economy.hr.dto.ViewPersonalDTO;
import ge.economy.hr.misc.FileUploadForm;
import ge.economy.hr.misc.Response;
import ge.economy.hr.model.PersonalDocument;
import ge.economy.hr.services.LDAPService;
import ge.economy.hr.services.PersonalService;
import ge.economy.hr.services.PositionService;
import ge.economy.hr.services.StructureService;
import ge.economy.hr.services.tree.PersonalNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author invisible
 */
@Controller
@RequestMapping("/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;
    @Autowired
    private StructureService structureService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private LDAPService lDAPService;

    private static final String UPLOAD_DIR = "C:\\Program Files\\glassfish4\\glassfish\\domains\\domain1\\docroot\\upload\\";
//    private static final String UPLOAD_DIR = "/var/webapp/uploads/";

    @RequestMapping("/get")
    @ResponseBody
    public Object getPersonal(@RequestParam("node") int node) {
        List<Object> nodes = new ArrayList<>();
        List<PersonalDTO> p = personalService.getPersonalByStructureId(node);
        p.stream().forEach((s) -> {
            s.setText(s.getFirstName() + " " + s.getLastName() + " (" + s.getPositionName() + ")");
            s.setStructureName(structureService.getStructureById(s.getStructureId()).getName());
            s.setPositionName(positionService.getPositionById(s.getPositionId()).getName());
            nodes.add(s);
        });
        List<StructureDTO> st = structureService.getChildByStructureId(node);
        st.stream().forEach((s) -> {
            nodes.add(s);
        });
        return nodes;
    }

    @RequestMapping("/get-all-personal")
    @ResponseBody
    public List<PersonalDTO> getAllPersonal(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return personalService.getAllPersonal(p.getOrganisationId());
    }

    @RequestMapping("/syncronize-tree")
    @ResponseBody
    public void syncronazePersonal(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        lDAPService.syncronizeTreeByOrganisation(p.getMail());
    }

    public int getParentStructureId() {

        return 0;
    }

    @RequestMapping("/get-non-management-personal")
    @ResponseBody
    public List<PersonalDTO> getNonManagementPersonal(HttpServletRequest request) {
        //TODO es droebitia gadasaweria wesierad NINO
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        List<Integer> structures = new ArrayList<>();
        StructureDTO structure = structureService.getStructureById(p.getStructureId());
        if (structure.getParentId() > 1) {
            structures.add(structure.getParentId());
            List<StructureDTO> _st = structureService.getChildByStructureId(structure.getParentId());
            for (StructureDTO s : _st) {
                structures.add(s.getId());
            }
        }
        structures.add(p.getStructureId());
        List<StructureDTO> st = structureService.getChildByStructureId(p.getStructureId());
        for (StructureDTO s : st) {
            structures.add(s.getId());
        }
        return personalService.getNonManagementPersonal(structures);
    }

    private void getParentStructureId(int childStructureId) {
        StructureDTO st = structureService.getStructureById(childStructureId);
        if (st.getParentId() > 1) {
            childStructureId = st.getParentId();
            getParentStructureId(childStructureId);
        }
    }

    @RequestMapping("/get-personal-by-test")
    @ResponseBody
    public List<PersonalDTO> getPersonalByTest(@RequestParam int testId) {
        return personalService.getPersonalByTest(testId);
    }

    @RequestMapping("/get-all-structure")
    @ResponseBody
    public List<StructureDTO> getAllStructure(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return structureService.getAll(p.getOrganisationId());
    }

    @RequestMapping("/get-structure-tree")
    @ResponseBody
    public List<StructureDTO> getStructureTree(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return structureService.getStructureTree(p.getOrganisationId());
    }

    @RequestMapping("/get-tree-test")
    @ResponseBody
    public PersonalNode getTreeTest(HttpServletRequest request) {
        PersonalDTO p = (PersonalDTO) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        PersonalNode personalNode = new PersonalNode();
        List<StructureDTO> structure = structureService.getStructureTree(p.getOrganisationId());
        for (StructureDTO st : structure) {
            personalNode.setId(st.getId());
            personalNode.setText(st.getName());
            personalNode.addState(false, true);
            rec(st, personalNode);
        }
        return personalNode;
    }

    @RequestMapping("/get-department-tree")
    @ResponseBody
    public PersonalNode getDepartmentTree() {
        PersonalNode personalNode = new PersonalNode();
        List<StructureDTO> structure = structureService.getDepartmentStructure();
        for (StructureDTO st : structure) {
            personalNode.setId(st.getId());
            personalNode.setText(st.getName());
            personalNode.addState(false, true);
            rec(st, personalNode);
        }
        return personalNode;
    }

    public void rec(StructureDTO structure, PersonalNode personalNode) {
        for (Object node : structure.getNodes()) {
            if (node instanceof StructureDTO) {
                PersonalNode p = new PersonalNode();
                StructureDTO st = (StructureDTO) node;
                p.setId(st.getId());
                p.setText(st.getName());
                personalNode.getChildren().add(p);
                rec(st, p);
            } else {
                ViewPersonalDTO personal = (ViewPersonalDTO) node;
                PersonalNode node1 = new PersonalNode();
                node1.setId(personal.getId());
                node1.setText(personal.getFullName() + " -<b>" + personal.getPositionName() + "</b>");
                node1.setType("file");
                personalNode.getChildren().add(node1);
            }
        }
    }

    @RequestMapping("/get-structure-tree-test")
    @ResponseBody
    public List<StructureDTO> getStructureTreeTest() {
        return structureService.getStructureTree(1);
    }

    @RequestMapping("/get-personal-by-id")
    @ResponseBody
    public PersonalDTO getPersonalById(@RequestParam int personalId) {
        return personalService.getPersonalById(personalId);
    }

    @RequestMapping("/get-personal-info")
    @ResponseBody
    public PersonalDTO getPersonalInfo(HttpSession session) {
        PersonalDTO p = (PersonalDTO) session.getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return personalService.getPersonalById(p.getId());
    }

    @RequestMapping("/save-personal")
    @ResponseBody
    public void updatePersonal(@RequestBody ge.economy.hr.request.Personal personal) throws Exception {
        personalService.updatePersonal(personal);

    }

    @RequestMapping("/delete-file")
    @ResponseBody
    public void deletePersonalFile(@RequestParam int id) {
        personalService.deletePersonalFile(id);
    }

    @RequestMapping("/upload")
    @ResponseBody
    public Response save(FileUploadForm uploadForm, Model map, HttpServletRequest request) {
        try {
            int personalId = 1000;
            MultipartFile idCard = uploadForm.getIdCard();
            MultipartFile hrRecord = uploadForm.getHrRecord();
            MultipartFile cv = uploadForm.getCv();
            MultipartFile diploma = uploadForm.getDiploma();
            MultipartFile convictionCertificate = uploadForm.getConvictionCertificate();
            MultipartFile drugReference = uploadForm.getDrugReference();
            MultipartFile oathEmployee = uploadForm.getOathEmployee();
            MultipartFile receiptOfEmployee = uploadForm.getReceiptOfEmployee();
            MultipartFile orderOfAppointment = uploadForm.getOrderOfAppointment();
            MultipartFile orderOfPosition = uploadForm.getOrderOfPosition();
            MultipartFile orderOfPromotion = uploadForm.getOrderOfPromotion();
            MultipartFile maternityLeave = uploadForm.getMaternityLeave();
            MultipartFile impositionOfDuties = uploadForm.getImpositionOfDuties();
            MultipartFile orderOfDismissal = uploadForm.getOrderOfDismissal();
            MultipartFile certificates = uploadForm.getCertificates();
            uploadPersonalFile(idCard, personalId, PersonalDocumentType.IDCARD.getValue());
            uploadPersonalFile(hrRecord, personalId, PersonalDocumentType.HRRECORDSHEET.getValue());
            uploadPersonalFile(cv, personalId, PersonalDocumentType.CV.getValue());
            uploadPersonalFile(diploma, personalId, PersonalDocumentType.DIPLOMA.getValue());
            uploadPersonalFile(convictionCertificate, personalId, PersonalDocumentType.CONVICTIONCERTIFICATE.getValue());
            uploadPersonalFile(drugReference, personalId, PersonalDocumentType.DRUGREFERNCE.getValue());
            uploadPersonalFile(oathEmployee, personalId, PersonalDocumentType.OATHOFEMPLOYEE.getValue());
            uploadPersonalFile(receiptOfEmployee, personalId, PersonalDocumentType.RECEIPTOFEMPLOYEE.getValue());
            uploadPersonalFile(orderOfAppointment, personalId, PersonalDocumentType.ORDEROFAPPOINTMENT.getValue());
            uploadPersonalFile(orderOfPosition, personalId, PersonalDocumentType.ORDEROFPOSITION.getValue());
            uploadPersonalFile(orderOfPromotion, personalId, PersonalDocumentType.ORDEROFPROMOTION.getValue());
            uploadPersonalFile(maternityLeave, personalId, PersonalDocumentType.MATERNITYLEAVE.getValue());
            uploadPersonalFile(impositionOfDuties, personalId, PersonalDocumentType.IMPOSITIONOFDUTIES.getValue());
            uploadPersonalFile(orderOfDismissal, personalId, PersonalDocumentType.ORDEROFDISMISSAL.getValue());
            uploadPersonalFile(certificates, personalId, PersonalDocumentType.CERTIFICATES.getValue());

        } catch (Exception ex) {
            int a = 0;
            return Response.error(ex.getMessage());
        }
        return Response.success();
    }

    private void uploadPersonalFile(MultipartFile file, int personalId, int documentTypeId) throws FileNotFoundException, IOException {
        if (file != null && file.getSize() > 0) {
            String fileName = personalId + "_" + documentTypeId + "_" + file.getOriginalFilename();
            File directory = new File(UPLOAD_DIR + personalId);
            if (!directory.exists()) {
                directory.mkdir();
            }
            OutputStream outputStream = new FileOutputStream(directory + "\\" + fileName);
            InputStream inputStream = file.getInputStream();
            int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
            PersonalDocument doc = new PersonalDocument();
            doc.setPersonalId(personalId);
            doc.setName(fileName);
            doc.setDocumentTypeId(documentTypeId);
            //personalService.savePersonalDocument(doc);
            outputStream.close();
            inputStream.close();
        }
    }

    @RequestMapping("/getDocument")
    @ResponseBody
    public Response getPersonalDocument(@RequestParam("personalId") int personalId) {
        Response r = new Response();
        Map<String, Object> data = new HashMap<>();
        data.put("documents", personalService.getPersonalDocument(personalId));
        r.setData(data);
        return r;
    }

    @RequestMapping(value = "/getFile")
    @ResponseBody
    public void getUploadedFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        String file = request.getParameter("file");
        String personalId = request.getParameter("personalId");
        String filePath = UPLOAD_DIR + personalId + "\\" + file;

        try {
            processFile(response, filePath);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            }
        }
    }

    private void processFile(HttpServletResponse response, String path) throws IOException {
        try {
            response.addHeader("Content-Type", "text/plain");
            InputStream inputStream = new FileInputStream(new File(path));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (IOException ex) {
            throw ex;
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
