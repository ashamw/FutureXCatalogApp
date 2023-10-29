package com.futurex.services.FutureXCatalogApp;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CatalogController {

    private static Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private  EurekaClient client;

    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "displayDefaultHome")
    public String getcatalogHome() {
        logger.info("Received request for home URL");
        String courseAppMessage = "";
      //  String courseAppUrl = "http://localhost:8080/";
        InstanceInfo nextServerFromEureka = client.getNextServerFromEureka("futurex-course-app",false);
        RestTemplate restTemplate=new RestTemplate();
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        courseAppMessage = restTemplate.getForObject(homePageUrl, String.class);
        return ("Welcome to futurex catalog" + courseAppMessage);
    }
     public String displayDefaultHome(){

        return ("Welcome to Futurex Catalog"+"please try later");
    }
     




    @RequestMapping("/catalog")
    public String getcatalogCourses() {
        logger.info("Received request for catalog");
        String courses = "";
       // String courseAppUrl = "http://localhost:8080/courses";
        InstanceInfo nextServerFromEureka = client.getNextServerFromEureka("futurex-course-app",false);
        RestTemplate restTemplate = new RestTemplate();
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        homePageUrl=homePageUrl+"/courses";
        logger.info("Sending request to Course Service for all courses");
        courses = restTemplate.getForObject(homePageUrl, String.class);
        return ("our courses are " + courses);
    }

    @RequestMapping("/firstCourse")
    public String getcatalog() {
        logger.info("Received request to get the first course");
        Course courses = new Course();
       // String courseAppUrl = "http://localhost:8080/1";
        InstanceInfo nextServerFromEureka = client.getNextServerFromEureka("futurex-course-app",false);
        String homePageUrl = nextServerFromEureka.getHomePageUrl();
        homePageUrl=homePageUrl+"/1";
        RestTemplate restTemplate = new RestTemplate();
        courses = restTemplate.getForObject(homePageUrl, Course.class);
        return ("our firstCourse is " + courses.getCourseName());
    }
}

  
