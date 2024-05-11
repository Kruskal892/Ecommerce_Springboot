package com.ecommerce.admin.controller;


import com.ecommerce.library.dto.CustomerDto;
import com.ecommerce.library.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final CustomerService customerService;

    @GetMapping("/users")
    public String user(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        List<CustomerDto> customers = customerService.allCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/users/{pageNo}")
    public String allProducts(@PathVariable("pageNo") int pageNo, Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Page<CustomerDto> customers = customerService.getAllCustomer(pageNo);
        model.addAttribute("title", "Manage Products");
        model.addAttribute("products", customers);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", customers.getTotalPages());
        return "products";
    }

    @RequestMapping(value = "/delete-user", method = {RequestMethod.PUT, RequestMethod.GET})
    public String deleteUser(Long id, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        } else {
            customerService.deleteById(id);
            return "redirect:/orders";
        }
    }
}
