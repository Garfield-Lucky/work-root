package com.easy.controller;



import com.easy.api.provider.DemoService;
import com.easy.model.User;
import com.easy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @Description: TODO
 *
 * @param
 * @author Created by wuzhangwei on 2018/7/21 14:17
 */

@Controller
@RequestMapping("/user")
public class UserRestController extends BaseController{

    //private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Resource
    private UserService userService;
    @Resource
    private DemoService demoService;

    /**
     * @Description: 首页
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:12
     */
    @RequestMapping(value ="index")
    public String testindex(){

        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
        return "index";
    }


    /**
     * @Description: 打开page页面
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:21
     */
    @RequestMapping("/page")
    public ModelAndView page(Model model){
        // 页面位置 /WEB-INF/jsp/page/page1.jsp
//        log.info("***************************page1****************************");
        ModelAndView mav = new ModelAndView("page/page");
        mav.addObject("content", "hello");
        return mav;
    }


    /**
     * @Description: 通过用户名查找用户
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 18:01
     */
    @RequestMapping(value = "/findUserByName", method = RequestMethod.GET)
    @ResponseBody
    public User findOneUser(@RequestParam(value = "userName", required = true) String userName) {
//        log.info("***************************findOneUser****************************");
        return userService.findUserByName(userName);
    }



    /**
     * @Description: 打开用户管理列表页面
     *
     * @param
     * @author Created by wuzhangwei on 2018/7/22 9:21
     */
    @RequestMapping("/list")
    public ModelAndView list(Model model){
//        log.info("***************************list****************************");
        ModelAndView mav = new ModelAndView("user/listUser");
        return mav;
    }


//    /**
//     * @Description: 返回用户列表
//     *
//     * @param
//     * @author Created by wuzhangwei on 2018/8/19 18:04
//     */
//    //管理员查看用户列表（默认加载）
    @RequestMapping(value="/findUserList")
    @ResponseBody
    public List<User> findUserList(int page,int limit,String userName) {
       // log.info("*********findUserList page "+page+" pageSize "+limit+"**********************");


        Map map =new HashMap();
        map.put("page",page-1);
        map.put("pageSize",limit);
        map.put("userName",userName);

        List<User> listUser = userService.list(map);
        return listUser;
    }

    @RequestMapping("/add")
    public ModelAndView addUser(){
//        log.info("addUser");
        ModelAndView mav = new ModelAndView("user/addUser");
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(int id){
//        log.info("editUser");
        User user = userService.findById(id);
        ModelAndView mav = new ModelAndView("user/editUser");
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping("/view")
    public ModelAndView viewUser(int id){
//        log.info("viewUser");
        User user = userService.findById(id);
        ModelAndView mav = new ModelAndView("user/viewUser");
        mav.addObject("user",user);
        return mav;
    }


//    @RequestMapping(value="/editUser")
//    @ResponseBody
//    public String editUser(User user) {
//        JSONObject json = new JSONObject();
//        try {
//            System.out.println(user.toString());
//            int a = userService.edit(user);
//            System.out.println(a);
//            json.put("code","0");
//            json.put("msg","更新成功");
//        } catch (Exception e) {
//            log.error("更新用户失败" + user.toString(), e);
//            json.put("code","1");
//            json.put("msg","更新失败");
//        }
//        return json.toString();
//    }
//
//    @RequestMapping(value="/addUser")
//    @ResponseBody
//    public String addUser(User user) {
//        JSONObject json = new JSONObject();
//        try {
//            //把用户的密码按md5规则加密
//            Object obj = RSASignature.MD5.getMd5ByShiro(user.getPassword(),user.getUserName());
//            user.setPassword(obj.toString());
//            int a= userService.save(user);
//            System.out.println(a);
//            json.put("code","0");
//            json.put("msg","保存成功");
//        } catch (Exception e) {
//            log.error("添加用户失败" + user.toString(), e);
//            json.put("code","1");
//            json.put("msg","保存失败");
//        }
//        return json.toString();
//    }
//
//
//    /**
//     * @Description: 根据用户id删除用户
//     *
//     * @param
//     * @author Created by wuzhangwei on 2018/8/19 18:04
//     */
//    @RequestMapping(value="/delUser")
//    @ResponseBody
//    public String deletUser(int id) {
//        JSONObject json = new JSONObject();
//        try {
//            int a =userService.delete(id);
//            System.out.println(a);
//            json.put("code","0");
//            json.put("msg","删除成功");
//        } catch (Exception e) {
//            log.error("删除用户失败" + id, e);
//            json.put("code","1");
//            json.put("msg","删除失败");
//        }
//        return json.toString();
//    }


}
