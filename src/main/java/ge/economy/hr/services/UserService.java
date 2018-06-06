package ge.economy.hr.services;

import ge.economy.hr.dao.SysgroupDao;
import ge.economy.hr.dto.SysGroupDTO;
import ge.economy.hr.model.SysGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nino lomineishvili
 */
@Service
public class UserService {

    @Autowired
    private SysgroupDao sysGroupDao;

    public List<SysGroupDTO> getSysGroups() {
        return SysGroupDTO.parseToList(sysGroupDao.getAll(SysGroup.class));
    }

}
