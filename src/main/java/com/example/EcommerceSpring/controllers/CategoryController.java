package com.example.EcommerceSpring.controllers;

//import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

//@Component// automatically registered as a bean in component scan
@RestController// is a Spring Boot annotation used to create RESTful web services (APIs)...{@Controller + @ResponseBody}.
@RequestMapping("api/categories")
public class CategoryController {

    @GetMapping//This function will be triggered when client calls ("api/categories");
    public String getCategory(){
        return "Electronics";
    }
    @GetMapping("/count") // if we call a GET req on /api/categories/count
    public int getCategoryCount(){
        return 5;
    }
    @PostMapping
    public String getPostreq() {
        return "Post Electronics";
     }
}
