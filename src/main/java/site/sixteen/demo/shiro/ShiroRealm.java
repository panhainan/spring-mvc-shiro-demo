package site.sixteen.demo.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import site.sixteen.demo.dao.UserDAO;
import site.sixteen.demo.entity.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        //2. 从 UsernamePasswordToken 中来获取 username
        String username = upToken.getUsername();

        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        log.info("从数据库中获取 username: {} 所对应的用户信息.", username);
        User dbUser = UserDAO.fetchDataFromDB(username);
        log.info("数据库中 {} 所对应的用户信息为：{} ", username, dbUser);

        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if (dbUser == null) {
            throw new UnknownAccountException("用户不存在!");
        }

        //5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
        if (dbUser.getLocked()) {
            throw new LockedAccountException("用户被锁定!");
        }

        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的.
        //1). principal: 认证的实体信息. 可以是 username(通常), 也可以是数据表对应的用户的实体类对象.
        Object principal = dbUser.getUsername();
        //2). credentials: 密码.
        Object credentials = dbUser.getPassword();

        //3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
        String realmName = getName();
        //4). 盐值. 这里以用户名为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }


    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("授权中：{}", principals.getPrimaryPrincipal());
        // 1. 从PrincipalCollection中来获取登录用户的信息
        String username = String.valueOf(principals.getPrimaryPrincipal());
        // 2. 利用登录的用户信息获取当前用户的角色或权限（可能需要查询数据库）
        User DbUser = UserDAO.fetchDataFromDB(username);
        // 3. 创建SimpleAuthorizationInfo，并设置roles属性
        Set<String> roles = new HashSet<>();
        for (String roleStr : DbUser.getRoles()) {
            roles.add(roleStr);
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        // 4. 返回SimpleAuthorization对象
        return simpleAuthorizationInfo;
    }



}
