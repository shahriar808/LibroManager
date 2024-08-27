package com.shahriar.Library.controller;

import com.shahriar.Library.entity.Publisher;
import com.shahriar.Library.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController {
    private final PublisherService publisherService;
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    public String publishers(Model model) {
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }
    @GetMapping("/publishers/add")
    public String addPublisher(Publisher publisher) {
        return "add-publisher";
    }
    @PostMapping("/publishers/save")
    public String savePublisher(Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-publisher";
        }
        publisherService.savePublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }
    @GetMapping("/publishers/update/{id}")
    public String updatePublisher(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findPublisherById(id));
        return "update-publisher";
    }
    @PostMapping("/publishers/save-update/{id}")
    public String savePublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "update-publisher";
        }
        publisherService.savePublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "publishers";
    }
    @GetMapping("/publishers/delete/{id}")
    public String deletePublisher(@PathVariable Long id, Model model) {
        publisherService.deletePublisher(id);
        model.addAttribute("publishers", publisherService.findAllPublishers());
        return "redirect:/publishers";
    }
}
