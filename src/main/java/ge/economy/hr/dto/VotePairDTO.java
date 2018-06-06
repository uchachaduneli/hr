/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge.economy.hr.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ge.economy.hr.model.VotePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @author uchachaduneli
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class VotePairDTO {
    private Integer id;
    private Integer testId;
    private Integer voterId;
    private Integer candidateId;
    private Integer departmentId;
    private Integer transactionId;

    public static VotePairDTO parse(VotePair obj) {
        VotePairDTO objDTO = new VotePairDTO();
        objDTO.setId(obj.getId());
        objDTO.setTestId(obj.getTestId());
        objDTO.setVoterId(obj.getVoterId());
        objDTO.setCandidateId(obj.getCandidateId());
        objDTO.setDepartmentId(obj.getDepartmentId());
        objDTO.setTransactionId(obj.getTransactionId());
        return objDTO;
    }

    public static List<VotePairDTO> parseToList(List<VotePair> list) {

        List<VotePairDTO> dTOs = new ArrayList<VotePairDTO>();
        for (VotePair p : list) {
            dTOs.add(VotePairDTO.parse(p));
        }
        return dTOs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}
