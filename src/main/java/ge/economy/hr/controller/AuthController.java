package ge.economy.hr.controller;

import ge.economy.hr.controller.auth.LoginInterceptor;
import ge.economy.hr.dto.PersonalDTO;
import ge.economy.hr.error.IncorrectAuthorizationExeption;
import ge.economy.hr.misc.Response;
import ge.economy.hr.model.Personal;
import ge.economy.hr.services.LDAPService;
import ge.economy.hr.services.PersonalService;
import ge.economy.hr.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author invisible
 */
@Controller
@RequestMapping
public class AuthController {

    public static final int HR = 15;
    @Autowired
    private LDAPService ldapService;
    @Autowired
    private VoteService voteService;
    @Autowired
    private PersonalService personalService;

    @RequestMapping(value = "/index", method = {RequestMethod.POST})
    public String login(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password) throws IncorrectAuthorizationExeption {
        //ldapService.syncronizePersonalModify(username, password);
        ldapService.getOrganisation(username);
        PersonalDTO user = ldapService.ldapAuth(username, password);
        if (user != null) {
            user.setRights(personalService.selectRightsByPersonal(user.getId()));
            request.getSession().setAttribute(LoginInterceptor.AUTHENTICATED_USER, user);
            if (user.getStructureId() == HR) {
                return "redirect:home";
            } else if (voteService.checkPersonalHasTest(user.getId(), user.getOrganisationId())) {
                return "redirect:vote";
            } else {
                return "redirect:info";
            }
        } else {
            return "redirect:index";
        }
    }

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String login(HttpServletRequest request) {
        Personal user = (Personal) request.getSession().getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        if (user == null) {
            return "index";
        } else {
            return "info";
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(HttpSession session) {
        session.removeAttribute(LoginInterceptor.AUTHENTICATED_USER);
        session.invalidate();
        return "redirect:index";
    }

    @RequestMapping(value = "/status", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Response status(HttpSession session) {
        PersonalDTO u = (PersonalDTO) session.getAttribute(LoginInterceptor.AUTHENTICATED_USER);
        return new Response.Builder().putData("authorised", u != null).putData("user", u).build();
    }
}
