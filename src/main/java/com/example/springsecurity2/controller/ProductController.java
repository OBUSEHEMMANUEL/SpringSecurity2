package com.example.springsecurity2.controller;

import com.example.springsecurity2.dto.AuthRequest;
import com.example.springsecurity2.dto.Product;
import com.example.springsecurity2.model.UserInfo;
import com.example.springsecurity2.service.JwtService;
import com.example.springsecurity2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
@Autowired
    private ProductService productService;

@Autowired
 private JwtService jwtService;

@Autowired
private AuthenticationManager authenticationManager;

@GetMapping("/welcome")
    public String welcome(){
        return "Welcome this endpoint is not secured";
    }
    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){
    return productService.addUser(userInfo);
    }

@GetMapping("/all")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllProduct(){
    return productService.getProducts();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id){
    return productService.getProduct(id);
    }


@PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

    if (authentication.isAuthenticated()) {
        return jwtService.generateToken(authRequest.getUsername());
    } else {
        throw new UsernameNotFoundException("User Not Found");
    }
}
}
