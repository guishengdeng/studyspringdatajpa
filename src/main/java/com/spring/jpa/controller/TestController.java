package com.spring.jpa.controller;

import com.spring.jpa.model.vo.PersonVoTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestController
 *
 * @author guisheng.deng
 * @date 2017年03月28日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping(value = "/person")
public class TestController {


    @RequestMapping(value = "/profile")
    public String redirectToPage(){
       return "test";
    }
    /**
     * 查询个人信息
     * @param pid
     * @param name
     * @param status
     * @return PersonTest
     * /{id}/{name}/{status}
     */
   /* @RequestMapping(value = "/profiles/{id}/{name}/{status}",method = RequestMethod.GET)
    @ResponseBody
    public  Map<String,List<PersonVoTest>> profile(@PathVariable(value="id") Integer pid, @PathVariable String name, @PathVariable boolean status){
        Map<String,List<PersonVoTest>> map=new HashMap<String,List<PersonVoTest>>();
        *//*map.put("personVoTest",new PersonVoTest(pid,name,status));*//*
        List<PersonVoTest> list=new ArrayList<PersonVoTest>();
        list.add(new PersonVoTest(1,"张三",false));
        list.add(new PersonVoTest(2,"李思",true));
        map.put("personVoTest",list);

        return map;
          //返回后的数据是这样的。
         //{"personVoTest":[{"id":1,"name":"张三","status":false},{"id":2,"name":"李思","status":true}]}
    }*/
    @RequestMapping(value = "/profiles/{id}/{name}/{status}",method = RequestMethod.GET)

    public @ResponseBody PersonVoTest profile(@PathVariable(value="id") Integer pid, @PathVariable String name, @PathVariable boolean status){
        //返回的数据{"id":1,"name":"李四","status":false}
        return new PersonVoTest(pid,name,status);
    }
    @RequestMapping(value = "/profiless/{id}/{name}/{status}",method = RequestMethod.GET)
    @ResponseBody
    public  List<PersonVoTest> profiless(@PathVariable(value="id") Integer pid, @PathVariable String name, @PathVariable boolean status){
        List<PersonVoTest> list=new ArrayList<PersonVoTest>();
        list.add(new PersonVoTest(pid,name,status));
        //返回的数据[{"id":1,"name":"李四","status":false}]
        return list;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public PersonVoTest login(@RequestBody PersonVoTest person) {
        return person;
    }
}