/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.services;

import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.repositories.EmpconditionRepository;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class EmpConditionService {
    
    @Autowired
    private EmpconditionRepository empcondition;
    
    public List<EmpCondition> getAll(){
        return empcondition.findAllConditions();
    }
     
    public List<EmpCondition> getByUsername(String username){
        return empcondition.findbyUSERNAME(username);
    }
    
    public List<EmpCondition> getByIlness(){
        return empcondition.findByIlness();
    }
    
    public List<EmpCondition> getBySorting(String name, int month, float temp1, float temp2){
        return empcondition.findBySorting(name, month, temp1, temp2);
    }
    
    public List<EmpCondition> getBySorting1(float temp1, float temp2){
        return empcondition.findBySorting1(temp1, temp2);
    }
    
    public List<EmpCondition> getBySorting2(String name, float temp1, float temp2){
        return empcondition.findBySorting2(name, temp1, temp2);
    }
    
    public List<EmpCondition> getBySorting3(int month, float temp1, float temp2){
        return empcondition.findBySorting3(month, temp1, temp2);
    }
    
     public List<EmpCondition> getByGrafik(String username){
        return empcondition.findByGrafik(username);
    }
    
    public boolean checktanggal(LocalDate tanggal, String username) {
        if (empcondition.hitung(tanggal, username) == 0) {
            return false;
        } else {
            return true;
        }
    }
     
   public void saveinput(String idemployee, LocalDate date, float temperature, String idhealty){
       empcondition.saveinput(idemployee, date, temperature, idhealty);
    }
    
     public void deletedata(int idempcondition){
        empcondition.deletedata(idempcondition);
    }
    
    public EmpCondition get(int id){
        return empcondition.findById(id).get();
    }
    
    public void update(String idemployee, Date date, float temperature, String idhealty, int idempcondition){
        empcondition.update(idemployee, date, temperature, idhealty, idempcondition);
    }
    
    
}
