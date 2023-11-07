package com.ojt.demo1.web.controller;

import com.ojt.demo1.bl.service.AuthorRepository;
import com.ojt.demo1.bl.service.BookRepository;
import com.ojt.demo1.bl.service.MyUserRepository;
import com.ojt.demo1.persistance.entity.Author;
import com.ojt.demo1.persistance.entity.Book;
import com.ojt.demo1.persistance.entity.MyUser;
import com.ojt.demo1.web.form.RegisterUserForm;
import com.ojt.demo1.web.form.UserForm;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class HomeController {
    public List<RegisterUserForm> userList = new ArrayList<>();

    @Autowired
    MyUserRepository myUserRepository;
    // this list is for storing data (DB)
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;

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
        var user = new MyUser();
        List<String> cities = new ArrayList<>();
        cities.add("Yangon");
        cities.add("Mandalay");
        cities.add("MawlaMyaing");
        model.addAttribute("user", user);
        model.addAttribute("cities", cities);
        return "user/create";
    }

    @PostMapping("/user/create")
    public String registerUser(@ModelAttribute MyUser user, Model model) {
//        System.out.println("user name --> " + user.getUsername());
//        System.out.println("ph no. --> " + user.getPhoneNumber());
//        System.out.println("married? --> " + user.getMarried());

//        for (var user : userList) {
//            System.out.println("-----------------------------------");
//            System.out.println("user name : " + user.getUsername());
//            System.out.println("user ph no : " + user.getPhoneNumber());
//            System.out.println("-----------------------------------");
//        }

//        userList.add(registerUserForm);
//        save in db
        // query ? session ? sessionFactory
//        model.addAttribute("userList", userList);
        myUserRepository.save(user);

//        System.out.println("user creation****");
        return "user/detail";
    }

    @GetMapping("/user/profile")
    public String getUser(Model model) {
//        var myUser = new MyUser();
//        myUser.setUsername("Fake user");
//        try{
//            MyUser myUser =  myUserRepository.findById(userId).get();
//            model.addAttribute("user", myUser);
//        } catch (Exception e) {
//            model.addAttribute("error", "there is no User");
//        }
        List<MyUser> userList = myUserRepository.findByCity("Mandalay");
        model.addAttribute("userList", userList);
        return "user/profile";
    }


    @GetMapping("/user/edit/{userId}")
    // RESTful naming
    public String editUser(Model model, @PathVariable Long userId) {
//        var user = new MyUser();
        MyUser user =  myUserRepository.findById(userId).get();
        List<String> cities = new ArrayList<>();
        cities.add("Yangon");
        cities.add("Mandalay");
        cities.add("MawlaMyaing");
        model.addAttribute("user", user);
        model.addAttribute("cities", cities);
        return "user/edit";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute MyUser user, Model model) {
//        var user = new MyUser();
        myUserRepository.save(user);
//        myUserRepository.update(user);
//        List<String> cities = new ArrayList<>();
//        cities.add("Yangon");
//        cities.add("Mandalay");
//        cities.add("MawlaMyaing");
//        model.addAttribute("user", user);
//        model.addAttribute("cities", cities);
        return "user/profile";
    }

    @GetMapping("/user/delete/{userId}")
    public String removeUser(@PathVariable Long userId) {
//        var user = new MyUser();
        myUserRepository.deleteById(userId);

//        var myUser =  myUserRepository.findById(userId).get();
//        myUserRepository.delete(myUser);
//        myUserRepository.update(user);
//        List<String> cities = new ArrayList<>();
//        cities.add("Yangon");
//        cities.add("Mandalay");
//        cities.add("MawlaMyaing");
//        model.addAttribute("user", user);
//        model.addAttribute("cities", cities);
        return "user/profile";
    }

    @GetMapping("/book/create")
    public void createBook() {
        Author author1 = new Author();
        author1.setName("Author 1");
        authorRepository.save(author1);

        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setTitle("Book One Title");
        book1.setBody("Book One Body");

        bookRepository.save(book1);
        System.out.println("book is saved ************");
    }
}
