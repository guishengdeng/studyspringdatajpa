package com.spring.jpa.service;

import com.spring.jpa.model.Resource;
import com.spring.jpa.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ResourceService
 *
 * @author guisheng.deng
 * @date 2017年03月24日
 * @reviewer
 * @description
 * @see
 */
@Service
public class ResourceService {
     @Autowired
     private ResourceRepository resourceRepository;

     public Iterable<Resource> getResourceList(){
         return resourceRepository.findAll();
     }
     public Resource getResourceById(Long id){
          return resourceRepository.findOne(id);
     }

     public void deleteResource(Long id){
           resourceRepository.delete(id);
     }

     public void addOrUpdate(Resource resource){
            resourceRepository.save(resource);
     }
}