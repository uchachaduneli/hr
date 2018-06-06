package ge.economy.hr.controller;

import ge.economy.hr.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author nino lomineishvili
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileServices;

    @RequestMapping("/get-file-as-base64")
    @ResponseBody
    public String getPersonalFile(@RequestParam int identifier, @RequestParam String group) throws IOException {
        return new String(fileServices.readFile(group, identifier + ""));
    }

    @RequestMapping("/get-file")
    @ResponseBody
    public void getPersonalFile(HttpServletResponse response, @RequestParam String identifier, @RequestParam String group) throws IOException {
        response.getOutputStream().write(fileServices.readFile(group, identifier));
    }

}
