package com.lianjia.data.clusterportal.service;

import com.github.pagehelper.PageHelper;
import com.lianjia.data.clusterportal.mapper.UserInfoMapper;
import com.lianjia.data.clusterportal.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2016-01-31 21:42
 */
@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public List<UserInfo> getAll(UserInfo UserInfo) {
        if (UserInfo.getPage() != null && UserInfo.getRows() != null) {
            PageHelper.startPage(UserInfo.getPage(), UserInfo.getRows(), "id");
        }
        return userInfoMapper.selectAll();
    }

    public UserInfo getById(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        userInfoMapper.deleteByPrimaryKey(id);
    }

    public void save(UserInfo country) {
        if (country.getId() != null) {
            userInfoMapper.updateByPrimaryKey(country);
        } else {
            userInfoMapper.insert(country);
        }
    }
}
