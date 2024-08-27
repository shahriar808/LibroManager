package com.shahriar.Library.controller;

import com.shahriar.Library.entity.Category;
import com.shahriar.Library.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController( CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }
    @GetMapping("/categories/add")
    public String addCategory(Category category) {
        return "add-category";
    }
    @PostMapping("/categories/save")
    public String saveCategory(Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-category";
        }
        categoryService.saveCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }
    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model) {
        model.addAttribute("categories", categoryService.findCategoryById(id));
        return "update-category";
    }
    @PostMapping("/categories/save-update/{id}")
    public String saveUpdateCategory(@PathVariable Long id, Category category, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-category";
        }
        categoryService.saveCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }
    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id, Model model) {
        categoryService.deleteCategory(id);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

}
