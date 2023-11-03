package com.ojt.demo1.web.controller;

import com.ojt.demo1.web.form.RegisterUserForm;
import com.ojt.demo1.web.form.UserForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    public List<RegisterUserForm> userList = new ArrayList<>();
    // this list is for storing data (DB)

    @RequestMapping("/")
//    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {
        // in real-world -> mostly come from db
        UserForm userForm1 = new UserForm(null, 20);
        UserForm userForm2 = new UserForm("Mary", null);
        UserForm userForm3 = new UserForm("Tina", 18);
        List<UserForm> userFormList = new ArrayList<>();
        userFormList.add(userForm1);
        userFormList.add(userForm2);
        userFormList.add(userForm3);
        model.addAttribute("userList", userFormList);
        return "home";
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    public String hello(@PathVariable(value = "id") Integer userId,
                        @RequestParam(value = "s", required = false) String search) {
        System.out.println("the ID is == " + userId);
        System.out.println("the search is == " + search);
        return "hello";
    }

    @GetMapping("/profile")
    //@RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(@Value("${api.key}") String prop) {
        System.out.println("property value is => " + prop);
        return "user/profile";
    }

    //@RequestMapping(value = "/profile", method = RequestMethod.POST)
    //@PostMapping("/something")

    @GetMapping("/user/create")
    // RESTful naming
    public String createUser(Model model) {
        var user = new RegisterUserForm();
        List<String> cities = new ArrayList<>();
        cities.add("Yangon");
        cities.add("Mandalay");
        cities.add("MawlaMyaing");
        model.addAttribute("user", user);
        model.addAttribute("cities", cities);
        return "user/create";
    }

    @PostMapping("/user/create")
    public String registerUser(@ModelAttribute RegisterUserForm registerUserForm, Model model) {
//        System.out.println("user name --> " + registerUserForm.getUsername());
//        System.out.println("ph no. --> " + registerUserForm.getPhoneNumber());
//        System.out.println("married? --> " + registerUserForm.isMarried());
        userList.add(registerUserForm);
//        for (var user : userList) {
//            System.out.println("-----------------------------------");
//            System.out.println("user name : " + user.getUsername());
//            System.out.println("user ph no : " + user.getPhoneNumber());
//            System.out.println("-----------------------------------");
//        }
        model.addAttribute("userList", userList);

//        System.out.println("user creation****");
        return "user/detail";
    }

}
