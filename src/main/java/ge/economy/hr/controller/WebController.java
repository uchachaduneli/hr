package ge.economy.hr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author invisible
 */
@Controller
@RequestMapping("")
public class WebController {

    @RequestMapping("/")
    public String index2() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/personal")
    public String personal() {
        return "personal";
    }

    @RequestMapping("/info")
    public String info() {
        return "info";
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/vote")
    public String vote() {
        return "vote";
    }

    @RequestMapping("/report")
    public String report() {
        return "report";
    }

    @RequestMapping("/testTree")
    public String testTree() {
        return "testTree";
    }

    @RequestMapping("/testTreeDepartment")
    public String testTreeDepartment() {
        return "testTreeDepartment";
    }

}
