package com.example.service;
import com.example.common.util.UUIDUtil;
import com.example.dao.ProjectEntityMapper;
import com.example.dao.entity.ProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectEntityMapper projectEntityMapper;

    public List<ProjectEntity> queryProjectList(ProjectEntity projectEntity) {
        List<ProjectEntity> projectEntityList = projectEntityMapper.queryProjectList(projectEntity);
        return projectEntityList;
    }

    public int addProjectInfo(ProjectEntity projectEntity) {
        projectEntity.setId(UUIDUtil.getOneUUID());
        projectEntity.setCreationDate(new Date());
        int projectResult = projectEntityMapper.insert(projectEntity);
        if (projectResult != 0) {
            return 3;
        } else {
            return projectResult;
        }
    }

    public int modifyProjectInfo(ProjectEntity projectEntity) {
        int projectResult = projectEntityMapper.updateByPrimaryKeySelective(projectEntity);
        return projectResult;
    }

    public int deleteProjectById(ProjectEntity projectEntity) {
        int projectResult = projectEntityMapper.deleteProjectById(projectEntity);
        return projectResult;
    }
}
