package com.example.controller;

import com.example.beans.HttpResponseEntity;
import com.example.dao.entity.UserEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedHashMap;
import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "Content-Type")//解决跨域问题
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录验证。
     *
     * @param userEntity 用户实体对象，包含用户名和密码信息。
     * @return 返回登录验证结果的HttpResponseEntity对象。
     */
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<UserEntity> hasUser = userService.selectUserInfo(userEntity);
            if(CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser.get(0));
                httpResponseEntity.setMessage("用户名或者密码错误");
            }
            else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("登陆成功");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 查询用户列表。
     *
     * @param userEntity 用户实体对象，包含查询条件。
     * @return 返回查询结果的HttpResponseEntity对象。
     */
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserList(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            List<UserEntity> hasUser = userService.queryUserList(userEntity);
            if(CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser.get(0));
                httpResponseEntity.setMessage("无用户信息");
            }
            else{
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("登陆成功");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 添加用户。
     *
     * @param userEntity 用户实体对象，包含要添加的用户信息。
     * @return 返回添加结果的HttpResponseEntity对象。
     */
    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = userService.addUserInfo(userEntity);
            if(result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");
            }
            else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    /**
     * 修改用户信息。
     *
     * @param userEntity 用户实体对象，包含要修改的用户信息。
     * @return 返回修改结果的HttpResponseEntity对象。
     */
    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUserInfo(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = userService.modifyUserInfo(userEntity);
            if(result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            }
            else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    //用户删除
    @RequestMapping(value = "/deleteUserinfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUser(@RequestBody UserEntity userEntity){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try{
            int result = userService.deleteUserById(userEntity);
            if(result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            }
            else{
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }



    @RequestMapping(value = "/searchUserByName", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity searchUserByName(@RequestBody Object object ){
        System.out.println(object);
        //object是class java.util.LinkedHashMap
        //object是{pageNum=1, pageSize=10, userName=aaa}
        //但是我希望取到object中的userName，怎么办？
        java.util.LinkedHashMap linkedHashMap = (java.util.LinkedHashMap) object;
        String userName = linkedHashMap.get("userName").toString();

        System.out.println("1"+object);
        System.out.println("1");
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            System.out.println("2");
            System.out.println(object);
            List<UserEntity> hasUser = userService.searchUserByName(userName);
            System.out.println("3");
            if (CollectionUtils.isEmpty(hasUser)) {
//                private String code;//状态码
//                private Object data;//返回的数据
//                private String message;//状态消息
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser.get(0));
                httpResponseEntity.setMessage("无法加载用户");
                System.out.println("4");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("用户加载成功");
                System.out.println("5");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
