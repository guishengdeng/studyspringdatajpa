package com.spring.jpa.service;

import com.spring.jpa.model.MainMenu;
import com.spring.jpa.repository.MainMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MainMenuService
 *
 * @author guisheng.deng
 * @date 2017年04月01日
 * @reviewer
 * @description
 * @see
 */
@Service
public class MainMenuService {
   @Autowired
   private MainMenuRepository mainMenuRepository;
   //一条sql语句都没有写,直接使用的是CrudRepository接口里面的方法。
   public Iterable<MainMenu> getMainMenuList(){
       return mainMenuRepository.findAll();
   }
   public MainMenu getMainMenuById(Long id){
      return mainMenuRepository.findOne(id);
   }
   public void addOrUpdate(MainMenu mainMenu){
        mainMenuRepository.save(mainMenu);
   }
   public void  deleteById(Long id){
      mainMenuRepository.delete(id);
   }
}