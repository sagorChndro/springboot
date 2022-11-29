package com.sagor.springcrudrestapiemailsecur.controller;

import com.sagor.springcrudrestapiemailsecur.annotations.ApiController;
import com.sagor.springcrudrestapiemailsecur.dto.ProductDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;
import com.sagor.springcrudrestapiemailsecur.service.ProductService;
import com.sagor.springcrudrestapiemailsecur.util.ResponseBuilder;
import com.sagor.springcrudrestapiemailsecur.util.UrlConstraint;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiController
@RequestMapping(UrlConstraint.ProductManagement.ROOT)
public class ProductController {
    // controller e service er relation ache ei karone ProductService class ta inject
    //korte hobe
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(UrlConstraint.ProductManagement.CREATE)
    public Response create(@Valid @RequestBody ProductDto productDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseBuilder.getFailureResponse(result, "Bean Binding result");
        }
        return productService.create(productDto);
    }

    @PutMapping(UrlConstraint.ProductManagement.UPDATE)
    public Response update(@PathVariable("id") Long id, @Valid @RequestBody ProductDto productDto, BindingResult result){
        if(result.hasErrors()){
            return ResponseBuilder.getFailureResponse(result, "Bean Binding Error");
        }
        return productService.update(id, productDto);
    }

    @DeleteMapping(UrlConstraint.ProductManagement.DELETE)
    public Response delete(@PathVariable("id") Long id){

        return productService.delete(id);
    }

    @GetMapping(UrlConstraint.ProductManagement.GET)
    public Response get(@PathVariable("id") Long id){
        return productService.get(id);
    }

    @GetMapping(UrlConstraint.ProductManagement.GET_ALL)
    public Response getAll(){
        return productService.getAll();
    }
}
