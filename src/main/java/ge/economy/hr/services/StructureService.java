package ge.economy.hr.services;

import ge.economy.hr.dao.OrganisationDao;
import ge.economy.hr.dao.ParamValuePair;
import ge.economy.hr.dao.StructureDao;
import ge.economy.hr.dto.OrganisationDTO;
import ge.economy.hr.dto.StructureDTO;
import ge.economy.hr.dto.ViewPersonalDTO;
import ge.economy.hr.model.Organisation;
import ge.economy.hr.model.Structure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author invisible
 */
@Service
public class StructureService {

    @Autowired
    private StructureDao structureDao;
    @Autowired
    private PersonalService personalService;
    @Autowired
    private OrganisationDao organisationDao;

    public List<StructureDTO> getChildByStructureId(int structureId) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("parentId", structureId));
        return StructureDTO.parseToList(structureDao.getAllByParamValue(Structure.class, criterias));
    }

    public StructureDTO getStructureById(int structureId) {
        return StructureDTO.parse(structureDao.find(Structure.class, structureId));
    }

    public List<StructureDTO> getAll(int organisationId) {
        return StructureDTO.parseToList(structureDao.getAll(organisationId));
    }

    public List<StructureDTO> getStructureTree(int organisationId) {
        List<StructureDTO> structures = getAll(organisationId);

        List<StructureDTO> tree = new LinkedList<>();

        Map<Integer, StructureDTO> map = new HashMap<>();

        List<ViewPersonalDTO> personals = personalService.getPersonalByParent(-1);
        Map<Integer, List<ViewPersonalDTO>> personalMap = new HashMap<>();
        for (ViewPersonalDTO p : personals) {
            List<ViewPersonalDTO> _list = personalMap.get(p.getStructureId());
            if (_list == null) {
                _list = new ArrayList<>();
            }
            _list.add(p);
            personalMap.put(p.getStructureId(), _list);
        }

        for (StructureDTO _item : structures) {

            List<ViewPersonalDTO> employees = personalMap.get(_item.getId());
            if (employees == null) {
                employees = new ArrayList<>();
            }

            if (_item.getNodes() == null) {
                _item.setNodes(new LinkedList<>());
            }

            employees.sort((ViewPersonalDTO a, ViewPersonalDTO b) -> {
                return a.getPosition().compareTo(b.getPosition());
            });

            _item.getNodes().addAll(employees);

            map.put(_item.getId(), _item);

            if (_item.getParentId() != 0) {
                map.get(_item.getParentId()).getNodes().add(_item);
            } else {
                tree.add(_item);
            }
        }
        return tree;
    }

    public List<StructureDTO> getAllStructure(int organisationId) {
        List<StructureDTO> structures = getAll(organisationId);

        List<StructureDTO> tree = new LinkedList<>();

        Map<Integer, StructureDTO> map = new HashMap<>();

        for (StructureDTO _item : structures) {
            if (_item.getNodes() == null) {
                _item.setNodes(new LinkedList<>());
            }

            map.put(_item.getId(), _item);

            if (_item.getParentId() != 0) {
                map.get(_item.getParentId()).getNodes().add(_item);
            } else {
                tree.add(_item);
            }
        }

        return tree;
    }

    public List<StructureDTO> getDepartmentStructure() {
        List<Integer> _arr = new ArrayList<>();
        _arr.add(0);
        _arr.add(1);

        List<StructureDTO> structures = StructureDTO.parseToList(structureDao.getDepartmentStructure(_arr));

        List<StructureDTO> tree = new LinkedList<>();

        Map<Integer, StructureDTO> map = new HashMap<>();

        for (StructureDTO _item : structures) {
            if (_item.getNodes() == null) {
                _item.setNodes(new LinkedList<>());
            }

            map.put(_item.getId(), _item);

            if (_item.getParentId() != 0) {
                map.get(_item.getParentId()).getNodes().add(_item);
            } else {
                tree.add(_item);
            }
        }

        return tree;
    }

    public OrganisationDTO getOrganisationByMail(String mail) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("mail", mail));
        List<Organisation> list = organisationDao.getAllByParamValue(Organisation.class, criterias);
        if (list.isEmpty()) return null;
        return OrganisationDTO.parse(list.get(0));
    }

}
