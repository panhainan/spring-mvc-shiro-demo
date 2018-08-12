package site.sixteen.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Slf4j
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UserLoginException.class)
    public String handleUserLoginException(Exception e, RedirectAttributes redirectAttributes) {
        log.info("异常：{}",e.getMessage());
        redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
        return "redirect:/login";
    }

}