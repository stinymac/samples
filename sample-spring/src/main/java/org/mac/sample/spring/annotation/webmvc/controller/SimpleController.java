/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.spring.annotation.webmvc.controller;

import org.mac.sample.spring.annotation.webmvc.service.SimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A controller
 *
 * @auther mac
 * @date 2018-11-06
 */
@Controller
public class SimpleController {
    @Autowired
    private SimpleService simpleService;

    @RequestMapping("/{name}")
    @ResponseBody
    public String sample(@PathVariable(name = "name") String name){
        return simpleService.hello(name);
    }

    @RequestMapping("/")
    public String welcome(){
        return "views/welcome";
    }
}
