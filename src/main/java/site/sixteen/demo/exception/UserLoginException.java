package site.sixteen.demo.exception;

import lombok.Getter;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Getter
public class UserLoginException extends Exception {

    private String message;

    public UserLoginException(String message) {
        this.message = message;
    }

}
