package sg.fwd.techTest.be.servlet3;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import sg.fwd.techTest.be.config.SpringWebConfig;


/*
Author : Gilang Permadi Khasani
Description : Setup Initializer Spring web config, servlet
Version : 0.1
Last Update : 17-05-2021
 */
public class MyWebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { SpringWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

}