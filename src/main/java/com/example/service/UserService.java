package com.example.service;

import com.example.common.util.UUIDUtil;
import com.example.dao.UserEntityMapper;
import com.example.dao.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserEntityMapper userEntityMapper;
    //登陆
    public List<UserEntity> selectUserInfo(UserEntity userEntity){
        return userEntityMapper.selectUserInfo(userEntity);
    }

    //
    public List<UserEntity> queryUserList(UserEntity userEntity){
        return userEntityMapper.queryUserList(userEntity);
    }

    //创建用户
    public int addUserInfo(UserEntity userEntity){
        userEntity.setId(UUIDUtil.getOneUUID());
        int userResult = userEntityMapper.insert(userEntity);
        if(userResult != 0)
            return 3;//3代表用户存在
        else
            return userResult;
    }

    //修改用户信息
    public int modifyUserInfo(UserEntity userEntity){
        return userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    //删除用户信息
    public int deleteUserById(UserEntity userEntity){
        return userEntityMapper.deleteUserById(userEntity);
    }
}
