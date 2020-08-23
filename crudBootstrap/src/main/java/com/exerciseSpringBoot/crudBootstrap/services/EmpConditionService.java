/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.services;

import com.exerciseSpringBoot.crudBootstrap.entities.Ccondition;
import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.repositories.CconditionRepository;
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
    
     @Autowired
    private CconditionRepository ccondition;
    
    public List<EmpCondition> getAll(){
        return empcondition.findAllConditions();
    }
     
    public List<Ccondition> getByUsername(String username){
        return ccondition.findbyUSERNAME(username);
    }
    
    public List<Ccondition> getBySorting(String name, int month, float temp1, float temp2){
        return ccondition.findBySorting(name, month, temp1, temp2);
    }
    
    public List<Ccondition> getBySorting1(float temp1, float temp2){
        return ccondition.findBySorting1(temp1, temp2);
    }
    
    public List<Ccondition> getBySorting2(String name, float temp1, float temp2){
        return ccondition.findBySorting2(name, temp1, temp2);
    }
    
    public List<Ccondition> getBySorting3(int month, float temp1, float temp2){
        return ccondition.findBySorting3(month, temp1, temp2);
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
       ccondition.saveinput(idemployee, date, temperature, idhealty);
    }
    
     public void deletedata(int idempcondition){
        empcondition.deletedata(idempcondition);
        ccondition.deletedata(idempcondition);
    }
    
    public EmpCondition get(int id){
        return empcondition.findById(id).get();
    }
    
    public void update(String idemployee, Date tanggal, float temperature, String idhealty, int idempcondition){
        empcondition.update(idemployee, tanggal, temperature, idhealty, idempcondition);
        ccondition.update(idemployee, tanggal, temperature, idhealty, idempcondition);
    }
    
    public List<Ccondition> getAllEmp(){
        return ccondition.findAllEmp();
    }
    
    public List<Ccondition> getIlness(){
        return ccondition.findIlness();
    }
    
    
}
