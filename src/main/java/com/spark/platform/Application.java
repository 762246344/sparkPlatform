package com.spark.platform;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Lianjia on 2016/12/26.
 */


//@RestController
//@EnableAutoConfiguration
//@ComponentScan("com.lianjia.data.clusterportal")
//public class Application {
//
//    @Autowired
//    private CityService cityService;
//
//    @RequestMapping("/")
//    String home() {
//        return "Hello World!";
//    }
//
//    @RequestMapping("/now")
//    String hehe() {
//        return "现在时间：" + (new Date()).toLocaleString();
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//
//}

@RestController
@EnableScheduling
@EnableAutoConfiguration
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    public ModelAndView home() {
        return new ModelAndView("/static/spark.html");
    }
}
