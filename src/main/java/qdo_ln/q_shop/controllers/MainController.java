package qdo_ln.q_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import qdo_ln.q_shop.entities.Product;
import qdo_ln.q_shop.services.CategoryService;
import qdo_ln.q_shop.services.ProductService;

import java.util.Map;

@Controller
public class MainController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public MainController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable int id){
        return productService.findById(id);
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam Map<String, String> param){
        Page<Product> page = productService.findAll(param);
        StringBuilder requestDefinition = productService.getRequestDefinition();
        model.addAttribute("page", page);
        model.addAttribute("requestDefinition", requestDefinition);
        return "index";
    }
}
