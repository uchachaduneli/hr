package ge.economy.hr.controller;

import ge.economy.hr.dto.SysGroupDTO;
import ge.economy.hr.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ucha
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/get-groups")
    @ResponseBody
    public List<SysGroupDTO> getSysGroup() {
        return userService.getSysGroups();
    }

}
