package com.example.EcommerceSpring.controllers;

//import org.springframework.stereotype.Component;
import com.example.EcommerceSpring.services.FakestoreCategoryService;
import com.example.EcommerceSpring.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Component// automatically registered as a bean in component scan
@RestController// is a Spring Boot annotation used to create RESTful web services (APIs)...{@Controller + @ResponseBody}.
@RequestMapping("api/categories")
public class CategoryController {

//    @GetMapping//This function will be triggered when client calls ("api/categories");
//    public String getCategory(){
//        return "Electronics";
//    }
//    @GetMapping("/count") // if we call a GET req on /api/categories/count
//    public int getCategoryCount(){
//        return 5;
//    }
//    @PostMapping
//    public String getPostreq() {
//        return "Post Electronics";
//     }



    //@Autowired --> It instructs the Spring container to automatically resolve and inject collaborating beans into your objects....not recommended because of final keyword
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService _categoryService) {
        this.categoryService = _categoryService;
    }

    //Spring will create an object of FakestoreCate..... as it marked as a @service, we just have to inject in using--->Constructor based injector or @autowired annotation

//    CategoryController (ICategoryService _categoryService){// triggered when the constructor of the category is called
//        this.categoryService = _categoryService;
//    }

    /* this mechanism is called as a dependency injection --> a software design pattern where an object is given its dependencies from an
     external source, rather than creating them itself. This process, a form of Inversion of Control, makes code more modular, reusable,
     and testable by decoupling objects and allowing dependencies to be injected via constructors, methods, or properties.*/


    @GetMapping
    public List<String> getAllCategories(){
        return this.categoryService.getCategoriesService();
    }

    // new CategoryController FakestoreCategoryService();
}
