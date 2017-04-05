package com.spring.jpa.controller;

import com.spring.jpa.model.MenuItem;
import com.spring.jpa.model.Resource;
import com.spring.jpa.model.vo.ResourceVo;
import com.spring.jpa.service.MenuItemService;
import com.spring.jpa.service.ResourceService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * ResourceController
 *
 * @author guisheng.deng
 * @date 2017年03月24日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping(value="/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private MenuItemService menuItemService;
    @RequestMapping(value = "/resourcelist")
    public String getResourceList(Model model){
        Iterable<Resource> resources=resourceService.getResourceList();
        model.addAttribute("resources",resources);
        return "resourcelist";
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public Map<String,Object> addResource(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("menuItems",menuItemService.getMenuItems());
        return map;
    }
    @RequestMapping(value="/updateresource")
    @ResponseBody
    public String updateResource(String id){
        Long resourceId=Long.parseLong(id);
        Resource resource=resourceService.getResourceById(resourceId);
        JSONObject json=new JSONObject();
        JsonConfig jsonConfig=new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        json.put("resource", JSONObject.fromObject(resource, jsonConfig));
        json.put("currentMenuItem", JSONObject.fromObject(resource.getMenuItem(), jsonConfig));
        json.put("menuItems", JSONArray.fromObject(menuItemService.getMenuItems(),jsonConfig));
        try{
            return URLEncoder.encode(json.toString(), "UTF-8");
        }catch(Exception e){
            return null;
        }
    }
    @RequestMapping(value="/updateOrAddSubmit")
    public String updateOrAddSubmit(ResourceVo vo){
        Resource resource=new Resource();
        resource.setDescription(vo.getDescription());
        resource.setId(vo.getId());
        if(vo.getMenuitem_id()!=null){
           MenuItem menuItem= menuItemService.getMenuItemById(vo.getMenuitem_id());
           resource.setMenuItem(menuItem);
        }
        resource.setResourcename(vo.getResourcename());
        resource.setLinkedaddress(vo.getLinkedaddress());
        resourceService.addOrUpdate(resource);
        return "redirect:resourcelist.action";
    }
    @RequestMapping(value="/deleteresource")
    public String deleteResource(String id){
        Long resourceId=Long.parseLong(id);
        resourceService.deleteResource(resourceId);
        return "redirect:resourcelist.action";
    }
}