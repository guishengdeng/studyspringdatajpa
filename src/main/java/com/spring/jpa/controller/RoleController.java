package com.spring.jpa.controller;

import com.spring.jpa.model.Resource;
import com.spring.jpa.model.Role;
import com.spring.jpa.model.vo.RoleResourceVo;
import com.spring.jpa.service.ResourceService;
import com.spring.jpa.service.RoleService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLEncoder;
import java.util.Set;

/**
 * RoleController
 *
 * @author guisheng.deng
 * @date 2017年03月24日
 * @reviewer
 * @description
 * @see
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @RequestMapping(value="/rolelist")
    public String getRoleList(Model model){
         Iterable<Role> roles=roleService.getRoleList();
         model.addAttribute("roles",roles);
         return "rolelist";
    }
    @RequestMapping(value="/updaterole")
    @ResponseBody
    public String updateRole(String id){
        Long roleId=Long.parseLong(id);
        Role role=roleService.getRoleById(roleId);
        JSONObject json=new JSONObject();
        JsonConfig jsonc = new JsonConfig();
        jsonc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        json.put("role", JSONObject.fromObject(role, jsonc));
        json.put("resources", JSONArray.fromObject(resourceService.getResourceList(), jsonc));
        try{
            return URLEncoder.encode(json.toString(), "UTF-8");
        }catch(Exception e){
            return null;
        }

    }
    /*删除有些问题？待会儿做个调试，问题在于，user_role中的role_id引用了role表中的id*/
    @RequestMapping(value="/deleterole")
    public String deleteRole(String id){
        Long roleId=Long.parseLong(id);
        roleService.deleteRole(roleId);
        return "redirect:rolelist.action";
    }
    @RequestMapping(value="/add")
    @ResponseBody
    public String addJsp(){
        JSONObject json=new JSONObject();
        JsonConfig jsonc = new JsonConfig();
        jsonc.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        json.put("resources", JSONArray.fromObject(resourceService.getResourceList(), jsonc));
        try{
            return URLEncoder.encode(json.toString(), "UTF-8");
        }catch(Exception e){
            return null;
        }
    }

    @RequestMapping(value="/updateOrAddSubmit")
    public String updateSubmit(RoleResourceVo vo){
        Role role=new Role();
        role.setRole_id(Long.parseLong(vo.getRole_id()));
        role.setName(vo.getName());
        role.setDescription(vo.getDescription());
        Set<Resource> resourceSet=role.getResources();
        if(vo.getId()!=null){
            for(String resource_id:vo.getId()){
                Long reourceId=Long.parseLong(resource_id);
                Resource resource=resourceService.getResourceById(reourceId);
                resourceSet.add(resource);
            }
        }
        role.setResources(resourceSet);
        roleService.addOrUpdateSubimt(role);
        return "redirect:rolelist.action";
    }

}