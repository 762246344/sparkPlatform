
package com.spark.platform.controller;

import com.github.pagehelper.PageInfo;
import com.spark.platform.model.City;
import com.spark.platform.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CityService cityService;

    @RequestMapping
    public PageInfo<City> getAll(City city) {
        List<City> countryList = cityService.getAll(city);
        return new PageInfo<City>(countryList);
    }

    @RequestMapping(value = "/getaspage/{page}/{rows}")
    public PageInfo<City> getAll(@PathVariable Integer page, @PathVariable Integer rows) {
        City city = new City();
        city.setPage(page);
        city.setRows(rows);
        List<City> countryList = cityService.getAll(city);
        return new PageInfo<City>(countryList);
    }

    @RequestMapping(value = "/getall1")
    public List<City> getAll1() {
        logger.info("ttt123");
        logger.info("ttt123111");
        logger.info("test1231114");
        logger.info("asd");
        List<City> countryList = cityService.getAll1();
        return (countryList);
    }


    @RequestMapping(value = "/add")
    public City add() {
        return new City();
    }

    @RequestMapping(value = "/view/{id}")
    public City view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        City city = cityService.getById(id);
        return city;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        cityService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(City city) {
        ModelMap result = new ModelMap();
        String msg = city.getId() == null ? "新增成功!" : "更新成功!";
        cityService.save(city);
        result.put("city", city);
        result.put("msg", msg);
        return result;
    }
}
