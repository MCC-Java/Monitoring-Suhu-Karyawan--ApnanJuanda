/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.controllers;

import com.exerciseSpringBoot.crudBootstrap.entities.Account;
import com.exerciseSpringBoot.crudBootstrap.entities.EmpCondition;
import com.exerciseSpringBoot.crudBootstrap.entities.Employee;
import com.exerciseSpringBoot.crudBootstrap.entities.Job;
import com.exerciseSpringBoot.crudBootstrap.entities.Profile;
import com.exerciseSpringBoot.crudBootstrap.entities.Sorting;
import com.exerciseSpringBoot.crudBootstrap.repositories.EmpconditionRepository;
import com.exerciseSpringBoot.crudBootstrap.services.AccountService;
import com.exerciseSpringBoot.crudBootstrap.services.EmpConditionService;
import com.exerciseSpringBoot.crudBootstrap.services.EmployeeService;
import com.exerciseSpringBoot.crudBootstrap.services.JobService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author ASUS
 */
@Controller
public class AppsController {
    @Autowired
    private EmpConditionService empConditionService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private JobService jobService;
    
    @Autowired
    private EmpconditionRepository ecr;
    
    //start of pageLogin
    @GetMapping("")
    public String awal(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }
    
    @GetMapping("/index")
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "index";
    }
    
    //mengecek isi dari form login, apakah sesuai di database 
    @RequestMapping("/check")
    public String checkLogin(@ModelAttribute(name = "account") Account account, Model model) {

        String username = account.getUsername();
        String password = account.getPassword();

        if (accountService.checkusername(username)) {
            if (password.equalsIgnoreCase(accountService.getpass(username))) {
                String role = accountService.getrole(username);
                if (role.equalsIgnoreCase("Karyawan")) {
                    return "redirect:/pagekaryawan/" + username; 
                } else {
                    return "redirect:/pageadmin";
                }
            } else {
                model.addAttribute("loginError", true);
                return "index";
            }
        } else {
            model.addAttribute("loginError", true);
            return "index";
        }
    }
    
    //end of pageLogin
    
    
    //start of pageadmin
    @GetMapping("/pageadmin")
    public String kondisi(Model model) {
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAllEmp());
        model.addAttribute("data", employeeService.getAll());
        return "pageadmin";
    }
    
    @GetMapping("/pageilness")
    public String ilness(Model model){
        model.addAttribute("ilness", empConditionService.getIlness());
        return "pageilness";
    }
    
    @GetMapping("/pageprofil")
    public String profil(Model model){
        model.addAttribute("profile", new Profile());
        model.addAttribute("job", jobService.getAll());
        model.addAttribute("profil", employeeService.getAllName());
        
        return "pageprofil";
    }
    
    @PostMapping("/saveProfil")
    public String saveProfil(@ModelAttribute(name = "profile") Profile profile, Model model){
        String idemployee = profile.getIdemployee();
        String idjob = profile.getIdjob();
        String name = profile.getName();
        String group = profile.getGroup();
        String role = profile.getRole();
        String password = "12345";
         String admin = "Admin";
       
        
        if(employeeService.check(idemployee)){
            model.addAttribute("profil", employeeService.getAll());
            return "redirect:/pageprofil";
        }
        else{
            if("A01".equals(idjob)){
                if("Admin".equals(role)){
                     employeeService.saveProfil(idemployee, idjob, name, group);
                     accountService.saveAccount(idemployee, admin, role);
                }
                else if("Karyawan".equals(role)){
                model.addAttribute("profil", employeeService.getAll());
                return "redirect:/pageprofil";
                }
            }
            else{
               if("Admin".equals(role)){
                    model.addAttribute("profil", employeeService.getAll());
                    return "redirect:/pageprofil";
               }
               else if("Karyawan".equals(role)){
               employeeService.saveProfil(idemployee, idjob, name, group);
               accountService.saveAccount(idemployee, password, role);
               }
            }
        }
       
      return "redirect:/pageprofil";
    }
    
    @RequestMapping("/pageakun")
    public ModelAndView pageakun(Model model){
        ModelAndView mav = new ModelAndView("pageakun");
        mav.addObject("accountt", new Account());
        model.addAttribute("akun", accountService.getAll());
        return mav;
    }
    
    @PostMapping("/updateRole")
    public String updateRole(@ModelAttribute(name = "accountt") Account accountt, Model model) {

       String username = accountt.getUsername();
       String role = accountt.getRole();
       String admin = "Admin";
       
       if("Admin".equals(role)){
           accountService.updateAccount(username, admin, role);
       }
       else if("Karyawan".equals(role)){
           String password = "12345";
           accountService.updateAccount(username, password, role);
       }
       
       
      return "redirect:/pageakun";
       
    }
    
    @GetMapping("/deleteAccount")
    public String deleteAccount(@ModelAttribute(name = "accountt") Account accountt, Model model) {
        String username = accountt.getUsername();
        accountService.deleteAccount(username);
        employeeService.deleteEmployee(username);
        return "redirect:/pageakun";
    }
    
    @GetMapping("/sort")
    public String sort(@ModelAttribute(name = "sorting") Sorting sorting, Model model){
        String name = sorting.getName();
        int month = sorting.getMonth();
        float temp1 = sorting.getTemp1();
        float temp2 = sorting.getTemp2();
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAll());
        model.addAttribute("data", employeeService.getAll());
        if("0".equals(name)){
            if(month==0){
               model.addAttribute("sortt", empConditionService.getBySorting1(temp1, temp2)); 
            }
            else{
               model.addAttribute("sortt", empConditionService.getBySorting3(month, temp1, temp2)); 
            }
        }
        else{
            if(month==0){
                model.addAttribute("sortt", empConditionService.getBySorting2(name, temp1, temp2)); 
            }
            else{
                model.addAttribute("sortt", empConditionService.getBySorting(name, month, temp1, temp2)); 
            }
        }
        return "pagesorting";
    }
    
    @GetMapping("/pagesorting")
    public String pagesorting(Model model) {
        model.addAttribute("sorting", new Sorting());
        model.addAttribute("conditions", empConditionService.getAll());
        model.addAttribute("data", employeeService.getAll());
        return "pagesorting";
    }
    
    @GetMapping("/pagegrafik")
    public String pagegrafik(Model model) {
        model.addAttribute("data", new Employee());
        model.addAttribute("list", employeeService.getAll());
        return "pagegrafik";
    }
    
//    @GetMapping("/viewgrafik")
//    public String viewgrafik(Model model) {
//        model.addAttribute("data", new Employee());
//        model.addAttribute("list", employeeService.getAll());
//        return "viewgrafik";
//    }
////    
    @RequestMapping("/viewgrafik")
    @ResponseBody
    public String viewgrafik(@ModelAttribute(name = "data") Employee data, Model model){
        String username = data.getName();
        List<EmpCondition> datalist = empConditionService.getByGrafik(username);
        model.addAttribute("datalistt", datalist);
        JsonArray jsonDate = new JsonArray();
        JsonArray jsonTemp = new JsonArray();
        JsonObject json = new JsonObject();
        datalist.forEach(dataa->{
            Date date = dataa.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
            String strDate = dateFormat.format(date);  
            jsonDate.add(strDate);
            jsonTemp.add(dataa.getTemperature());
        });
                
        json.add("date", jsonDate);
        json.add("temperature", jsonTemp);
        return json.toString();
        
    }
//end of pageadmin
    
    
//start of pagekaryawan
    @RequestMapping("/pagekaryawan/{username}")
    public ModelAndView pagekaryawan(Model model, @PathVariable(name = "username") String username){
        ModelAndView mav = new ModelAndView("pagekaryawan");
        String id = username;
        mav.addObject("empCondition", new EmpCondition());
        mav.addObject("emp", employeeService.getbyusername(username)); 
        mav.addObject("empcondition", empConditionService.getByUsername(username));
        return mav;
    }
    
    @PostMapping("/saveinput/{username}")
    public String saveinput(@ModelAttribute(name = "empCondition") EmpCondition empCondition, @PathVariable(name = "username") String username, Model model){
        float temperature = empCondition.getTemperature();
        LocalDate tanggal = java.time.LocalDate.now();
          if(empConditionService.checktanggal(tanggal, username)){
              model.addAttribute("empcondition", empConditionService.getByUsername(username));
              return "redirect:/pagegagal/" + username;
          }
          else{
           if(temperature<36.5){
               String idkes = "S01";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
           else if(temperature<37.5 && temperature>=36.5){
               String idkes = "S02";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
           else{
               String idkes = "S03";
               empConditionService.saveinput(username, tanggal, temperature, idkes);
           }
          } 

      return "redirect:/pagekaryawan/" + username;
    }
   
    @RequestMapping("/pagegagal/{username}")
    public ModelAndView pagegagal(Model model, @PathVariable(name = "username") String username){
        ModelAndView mav = new ModelAndView("pagegagal");
        mav.addObject("empCondition", new EmpCondition());
        mav.addObject("dAccount", new Account());
        mav.addObject("dEmployee", new Employee());
        mav.addObject("emp", employeeService.getbyusername(username)); //untuk keperluan profil
        mav.addObject("akun", accountService.getbyUsername(username));//untuk keperluan ubah password
        mav.addObject("empcondition", empConditionService.getByUsername(username)); //untuk keperluan datatables
        return mav;
    }
    
    @RequestMapping("/pageedit/{idempcondition}/{username}")
     public ModelAndView pageedit(@PathVariable(name = "idempcondition") int idempcondition, @PathVariable(name = "username") String username){
         ModelAndView mav = new ModelAndView("pageedit");
         mav.addObject("emp", employeeService.getbyusername(username));
         EmpCondition empCondition = empConditionService.get(idempcondition);
         mav.addObject("empCondition", empCondition);
         return mav;
     }
    
    @PostMapping("/update/{username}/{idempcondition}")
    public String update(@ModelAttribute(name = "empCondition") EmpCondition empCondition,@PathVariable(name = "username") String username, @PathVariable(name = "idempcondition") int idempcondition, Model model) {

       Date date = empCondition.getDate();
       Date tanggal = new Date(date.getTime()+ TimeUnit.DAYS.toMillis(1));
       float temperature = empCondition.getTemperature();
   
           if(temperature<36.5){
               String idkes = "S01";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           else if(temperature<37.5 && temperature>=36.5){
               String idkes = "S02";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           else{
               String idkes = "S03";
               empConditionService.update(username, tanggal, temperature, idkes, idempcondition);
           }
           
      return "redirect:/pagekaryawan/" + username;
       
    }
   
    @GetMapping("/delete/{idempcondition}/{idemployee}")
    public String delete(@PathVariable(name = "idempcondition") int idempcondition, @PathVariable(name = "idemployee") String idemployee) {
        String username = idemployee;
        empConditionService.deletedata(idempcondition);
        return "redirect:/pagekaryawan/" + username;
    }
    
    @RequestMapping("/updateprofil/{username}")
     public ModelAndView updateprofil(@PathVariable(name = "username") String username){
         ModelAndView mav = new ModelAndView("updateprofil");
         mav.addObject("emp", employeeService.getbyusername(username));
         Employee employee = employeeService.get(username);
         mav.addObject("employee", employee);
         mav.addObject("job", jobService.getAll());
         return mav;
     }
     
     
    @PostMapping("/UpdateProfil/{username}")
    public String UpdateProfil(@ModelAttribute(name = "employee") Employee employee,@PathVariable(name = "username") String username, Model model) {

        String id = employee.getId();
        Job idjob = employee.getIdjob();
        String idjobb = idjob.getId();
//        String jobtitle = idjob.getJobTitle();
        String name = employee.getName();
        String group = employee.getGroup();
        employeeService.UpdateProfil(id, idjobb, name, group);

        return "redirect:/pagekaryawan/" + username;
       
    }
    
//    @RequestMapping("/ubahpass/{username}")
//     public ModelAndView ubahpass(@PathVariable(name = "username") String username){
//         ModelAndView mav = new ModelAndView("ubahpass");
//         mav.addObject("emp", employeeService.getbyusername(username));
//         Account account = accountService.get(username);
//         mav.addObject("account", account);
//         return mav;
//     }
//     
//    @PostMapping("/UbahPass/{username}")
//    public String Ubahpass(@ModelAttribute(name = "account") Account account,@PathVariable(name = "username") String username, Model model) {
//    
//    String password = account.getPassword();
//    accountService.UbahPass(username, password);
//       
//    return "redirect:/pagekaryawan/" + username;
//       
//    }
    
    //end of pagekaryawan
    
    
    
}
