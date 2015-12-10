package com.frame.shiro;

import com.frame.model.User;
import com.frame.service.system.UserService;
import com.frame.util.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * UserRealm
 *
 * @author FANZONGYUAN
 * @date 2015/12/4
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    public UserRealm(){
        super();
        setCredentialsMatcher(new CustomCredentialsMatcher());
    }

    /**
     * 获取认证信息
     * @param authcToken authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        if (username != null && !"".equals(username)){
            User user = userService.selectUserByName(token.getUsername());
            if (user != null) {
                if (user.getLocked().equals("0")) {
                    throw new DisabledAccountException();
                }else{
                    Subject subject = SecurityUtils.getSubject();
                    subject.getSession().setAttribute(Constants.CURRENT_USER, user);
                    return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),this.getName());
                }
            } else {
                return null;
            }
        }else{
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.fromRealm(getName()).iterator().next();
        User user = userService.selectUserByName(username);
       /* List<Role> userRoles = userRoleService.getUserRoles(user.getUsername());
        List<Integer> userRolesList = new ArrayList<Integer>();
        for (Role userRole : userRoles) {
            userRolesList.add(userRole.getId());
        }*/
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
       /* List<RolePermission> rolePermissions = rolePermissionService.getRolePermissions(userRolesList);
        if (!rolePermissions.isEmpty()) {

            String permission = "";
            for (RolePermission rolePermission : rolePermissions) {
                //基于Permission的权限信息,shiro权限字符串为：“当前资源英文名称:操作名英文名称”
                permission = rolePermission.getPermission();
                if(permission.indexOf(":")!=-1){ //判断字符串中是否包含:号，如果包含
                    info.addStringPermission(permission);
                }
            }
            return info;
        } else {
            return null;
        }*/
        return null;
    }

    /**
     * 更新用户授权信息缓存.
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清除所有用户授权信息缓存.
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
}
