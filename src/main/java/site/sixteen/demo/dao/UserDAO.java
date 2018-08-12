package site.sixteen.demo.dao;

import site.sixteen.demo.entity.User;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
public class UserDAO {
    /**
     * 模拟从数据库取数据
     *
     * @param username
     * @return
     */
    public static User fetchDataFromDB(String username) {
        //这里假定数据库中有一下数据:
        // 1,"test","47ec2dd791e31e2ef2076caf64ed9b3d"(原串"123456"),false,roles{"user"}
        // 2,"admin","a66abb5684c45962d887564f08346e8d"(原串"123456"),false,roles{"admin","user"}
        // 3,"tourist","33d2ebfc6787cdb030d04dc940f8f4ed"(原串"123456"),true,roles{"tourist"}
        User dbUser = null;
        switch (username) {
            case "test":
                dbUser = new User(1, "test", "47ec2dd791e31e2ef2076caf64ed9b3d", false, new String[]{"user"});
                break;
            case "admin":
                dbUser = new User(2, "admin", "a66abb5684c45962d887564f08346e8d", false, new String[]{"admin","user"});
                break;
            case "tourist":
                dbUser = new User(3, "tourist", "33d2ebfc6787cdb030d04dc940f8f4ed", true, new String[]{"tourist"});
                break;
            default:
                break;
        }
        return dbUser;
    }
}
