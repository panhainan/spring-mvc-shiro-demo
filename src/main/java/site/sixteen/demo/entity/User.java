package site.sixteen.demo.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    private Integer id;
    private String username;
    private String password;
    private Boolean locked;

    private String[] roles;

}
