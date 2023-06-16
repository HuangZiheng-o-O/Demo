package com.example;

import com.example.common.util.UUIDUtil;
import com.example.dao.ProjectEntityMapper;
import com.example.dao.UserEntityMapper;
import com.example.dao.entity.ProjectEntity;
import com.example.dao.entity.UserEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
@SpringBootTest
public class Test2ApplicationTests {
    Logger log = Logger.getLogger(Test2ApplicationTests.class);
    @Test
    public void queryProjectList() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectName("Project 1");
        List<ProjectEntity> list = projectEntityMapper.queryProjectList(projectEntity);
        if (CollectionUtils.isEmpty(list)) {
            // 记录error级别的信息
            log.info(">>queryUserList用户列表查询测试失败");
        } else {
            System.out.println(list);
            // 记录info级别的信息
            log.info(">>queryUserList用户列表查询测试成功");
        }
    }

//    @Test
//    public void selectProjectInfo() throws Exception {
//        String resource = "mybatis-config.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
//        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
//        //调用projectMapper的方法
//        ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setProjectName("project1");
//        List<ProjectEntity> list = projectEntityMapper.queryProjectList(projectEntity);
//        if(CollectionUtils.isEmpty(list)){
//            // 记录error级别的信息
//        }else{
//            System.out.println(list);
//            // 记录info级别的信息
//            log.info(">>qselectUserInfo用户登录测试成功");
//        }
//    }

    @Test
    public void insert() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setUserId("1");
        projectEntity.setProjectName("LS");
        int i = projectEntityMapper.insert(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.info(">>insert项目插入测试失败");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>insert项目插入测试成功");
        }
    }

    @Test
    public void deleteProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        //构造一个id为 9  的projectEntity对象
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("9");
        int i = projectEntityMapper.deleteProjectById(projectEntity);
        if(i==0){
            // 记录error级别的信息
            log.info(">>delete项目删除测试失败");
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>delete项目删除测试成功");
        }
    }



    @Test
    public void modifyProjectById() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //创建ProjectMapper对象，mybatis自动生成mapper代理对象
        ProjectEntityMapper projectEntityMapper = sqlSession.getMapper(ProjectEntityMapper.class);
        //调用projectMapper的方法
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId("8");
        projectEntity.setCreatedBy("admin");
        projectEntity.setProjectName("Project 8");
        projectEntity.setProjectContent("Project 8 description");
        projectEntity.setLastUpdatedBy("admin");
        //modifyProject
        int i = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        if(i==0){
            // 记录error级别的信息
            System.out.println(i);
        }else{
            System.out.println(i);
            // 记录info级别的信息
            log.info(">>modify项目修改测试成功");
        }
    }
}
