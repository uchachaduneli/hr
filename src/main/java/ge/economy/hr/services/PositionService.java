package ge.economy.hr.services;

import ge.economy.hr.dao.ParamValuePair;
import ge.economy.hr.dao.PositionDao;
import ge.economy.hr.dto.PositionDTO;
import ge.economy.hr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author invisible
 */
@Service
public class PositionService {

    @Autowired
    private PositionDao positionDao;

    public PositionDTO getPositionById(int positionId) {
        return PositionDTO.parse(positionDao.find(Position.class, positionId));
    }

    public PositionDTO getPositionByLdapKey(String key) {
        List<ParamValuePair> criterias = new ArrayList<>();
        criterias.add(new ParamValuePair("ldapKey", key));
        List<Position> pList = positionDao.getAllByParamValue(Position.class, criterias);
        Position pp = null;
        if (!pList.isEmpty()) {
            pp = pList.get(0);
            return PositionDTO.parse(pp);
        }
        return null;
    }
}
