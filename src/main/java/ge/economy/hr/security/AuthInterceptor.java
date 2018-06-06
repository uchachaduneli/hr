package ge.economy.hr.security;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author ucha
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private UsersService usersService;
//
//    @Override
//    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws IOException {
//
//        String token = request.getHeader("token");
//        if (token != null && token.length() > 0) {
//            if (usersService.loginWithToken(token) != null) {
//                return true;
//            }
//        }
//        return false;
//
//        if (userId == null && request.getHeader("X-Requested-With") == null) {
//            if (uri.startsWith(request.getContextPath())) {
//                uri = uri.replace(request.getContextPath(), "");
//            }
//
//            if (uri.length() > 0 && !uri.equals("/")) {
//                response.sendRedirect("login");
//            } else {
//                response.sendRedirect("login");
//            }
//            return false;
//        } else if (userId == null) {
//            response.sendError(353, "სესიას გაუვიდა ვადა, გთხოვთ თავიდან გაიაროთ ავტორიზაცია");
//            return false;
//        }
//    }
}
