package com.frame.controller.login;

import com.frame.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by FANZONGYUAN on 2015/12/2.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger((LoginController.class));
    private static final String LOGIN_PAGE = "login";
    private static final String MAIN_PAGE = "main";

    /**
     * 跳转登录页面
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        return LOGIN_PAGE;
    }

    /**
     * 登录请求验证
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map, Model moudle){
        String msg = "";
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        //获取HttpSession中的验证码
//		 String sessionVerifyCode = (String)request.getSession().getAttribute(Constants.SessionKey.Captcha);
        //获取用户请求表单中输入的验证码
//		 String submitVerifyCode = WebUtils.getCleanParam(request, "captcha");
//		logger.info("用户[" + username + "]登录时输入的验证码为[" + submitVerifyCode + "], HttpSession中的验证码为[" + sessionVerifyCode + "]");
//		 if (StringUtils.isEmpty(submitVerifyCode) || !StringUtils.equals(sessionVerifyCode.toLowerCase(), submitVerifyCode.toLowerCase())){
//			 msg = "验证码不正确";
//			 map.put("msg", msg);
//			 return LOGIN_PAGE;
//		 }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + username + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            msg = ("未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            msg = ("密码不正确");
        }catch(LockedAccountException lae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            msg = ("账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            msg = ("用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            msg = ("用户名或密码不正确");
        }
        //验证是否登录成功
        if (currentUser.isAuthenticated()){
            logger.info("用户[" + username + "]登录认证通过,开始进行菜单组装");
            //menu(request);
            logger.info("用户[" + username + "]菜单组装结束，返回到系统主界面");
            return MAIN_PAGE;
        } else {
            token.clear();
            map.put("msg", msg);
            return LOGIN_PAGE;
        }
    }
    /**
     *
     * Description:
     * 得到功能菜单
     * @param
     * @return void
     * @throws Exception if has error
     * @Author Administrator
     * @Create Date: 2014-3-21 上午11:40:15
     */
    /*public void menu(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        User user = userService.getUser(username);
        List<Role> userRoles = userRoleService.getUserRoles(user.getUsername());
        List<Integer> userRolesList = new ArrayList<Integer>();
        for (Role userRole : userRoles) {
            userRolesList.add(userRole.getId());
        }
        if(null!=userRolesList && userRolesList.size()>0){
            Resource menuResource = getMenuResource(userRolesList);
            request.getSession().setAttribute("menuResource", menuResource);
        }
        //更新上次登录时间
//		userService.upLastLoginTime(user.getUsername());
        request.getSession().setAttribute(Constants.SessionKey.CURRENT_USER, user);
    }

    *//**
     *
     * Description:
     * 组装menu
     * @param
     * @return Resource
     * @throws Exception if has error
     * @Author Administrator
     * @Create Date: 2014-3-21 上午11:40:01
     *//*
    private Resource getMenuResource(List<Integer> userRolesList) {
        // 得到所有权限
        Set<String> permissionSet = Sets.newHashSet();
        List<RolePermission> rolePermissions = rolePermissionService.getRolePermissions(userRolesList);
        List<String> roleResourceList = new ArrayList<String>();
        String roleResourceArr[] = null;
        for (RolePermission rolePermission : rolePermissions) {
            Set<String> tmp = Sets.newHashSet(rolePermission.getPermission());
            permissionSet.addAll(tmp);

            //拆分权限获取资源集合，如：Log:Query 获取出Log字符
            roleResourceArr = rolePermission.getPermission().split(":");
            roleResourceList.add(roleResourceArr[0]);
        }

        // 组装菜单,只获取二级菜单
        //Module rootModule = moduleService.get(1L);
        Resource rootResource = resourceService.getMenuTree(removeDuplicate(roleResourceList));
        List<Resource> list1 = Lists.newArrayList();
        for (Resource m1 : rootResource.getChildren()) {
            List<Resource> list2 = Lists.newArrayList();
            for (Resource m2 : m1.getChildren()) {
                list2.add(m2);
            }
            m1.setChildren(list2);
            list1.add(m1);
        }
        rootResource.setChildren(list1);

        return rootResource;
    }

    *//**
     *
     * Description:
     * 去掉list中重复的值
     * @param
     * @return List<String>
     * @throws Exception if has error
     * @Author Administrator
     * @Create Date: 2014-1-20 上午10:01:01
     *//*
    public static List<String> removeDuplicate(List<String> list){
        HashSet<String> hashSet = new HashSet<String>(list);
        list.clear();
        list.addAll(hashSet);
        return list;
    }*/

    /**
     *
     * Description:
     * 用户注销
     * @param
     * @return String
     * @throws Exception if has error
     * @Author Administrator
     * @Create Date: 2014-1-16 下午05:52:34
     */
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return LOGIN_PAGE;
    }
}