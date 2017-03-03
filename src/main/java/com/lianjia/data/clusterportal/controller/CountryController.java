package com.lianjia.data.clusterportal.controller;

import com.github.pagehelper.PageInfo;
import com.lianjia.data.clusterportal.model.City;
import com.lianjia.data.clusterportal.model.Country;
import com.lianjia.data.clusterportal.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author zhaoguoxian
 * @since 2015-12-19 11:10
 */

@RestController
@RequestMapping("/countries")
public class CountryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryService countryService;

    @RequestMapping
    public PageInfo<Country>  getAll(Country country) {
        logger.info("test12");
        try {
            logger.info("test123");
            List<Country> countryList = countryService.getAll(country);
            logger.info("test1234");
            return new PageInfo<Country>(countryList);
        }catch(Exception e){
            logger.info("test1");
        }

        return null;
    }

}
