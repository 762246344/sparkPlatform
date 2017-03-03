package com.lianjia.data.clusterportal.service;

import com.github.pagehelper.PageHelper;
import com.lianjia.data.clusterportal.mapper.CityMapper;
import com.lianjia.data.clusterportal.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:09
 */

@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAll(City city) {
        if (city.getPage() != null && city.getRows() != null) {
            PageHelper.startPage(city.getPage(), city.getRows(), "id");
        }
        return cityMapper.selectAll();
    }

    public City getById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    public void save(City country) {
        if (country.getId() != null) {
            cityMapper.updateByPrimaryKey(country);
        } else {
            cityMapper.insert(country);
        }
    }

    public List<City> getAll1(){
        return cityMapper.getAll1();
    }
}
