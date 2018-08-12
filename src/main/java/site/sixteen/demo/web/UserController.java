package site.sixteen.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import site.sixteen.demo.dao.UserDAO;
import site.sixteen.demo.entity.User;
import site.sixteen.demo.exception.UserLoginException;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Slf4j
@Controller
public class UserController {

    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
    @GetMapping("/manage")
    public String manage(){
        return "manage";
    }
    @GetMapping("/user")
    public String user(Model model){
        Subject subject = SecurityUtils.getSubject();
        log.info("用户查看user界面：{}",subject.getPrincipal());
        User user = UserDAO.fetchDataFromDB(String.valueOf(subject.getPrincipal()));
        model.addAttribute("user",user);
        return "user";
    }
    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user) throws UserLoginException {
        Subject currentSubject = SecurityUtils.getSubject();
        if (!currentSubject.isAuthenticated()) {
            UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            try {
                // 执行登录.
                currentSubject.login(upToken);
            } catch (UnknownAccountException e) {
                // 若没有指定的账户, 则 shiro 将会抛出 UnknownAccountException 异常.
                //一般建议提示用户名密码错误
                throw new UserLoginException("用户不存在！");
            } catch (IncorrectCredentialsException e) {
                // 若账户存在, 但密码不匹配, 则 shiro 会抛出 IncorrectCredentialsException 异常。
                //一般建议提示用户名密码错误
                throw new UserLoginException("用户密码错误！");
            } catch (LockedAccountException e) {
                // 用户被锁定的异常 LockedAccountException
                throw new UserLoginException("用户已被锁定！");
            } catch (AuthenticationException e) {
                // 所有认证时异常的父类.
                throw new UserLoginException(e.getLocalizedMessage());
            }

        }
        return "redirect:/index";
    }
}
