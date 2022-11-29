package com.sagor.springcrudrestapiemailsecur.service.impl;

import com.sagor.springcrudrestapiemailsecur.dto.ProductDto;
import com.sagor.springcrudrestapiemailsecur.dto.Response;
import com.sagor.springcrudrestapiemailsecur.model.Product;
import com.sagor.springcrudrestapiemailsecur.repository.ProductRepository;
import com.sagor.springcrudrestapiemailsecur.service.ProductService;
import com.sagor.springcrudrestapiemailsecur.util.ResponseBuilder;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Implementation class e obossoi @Service annotation dite hobe
@Service("productService")
public class ProductServiceImpl implements ProductService {
    // repositoryr maddhome operation korte hobe
    // amra jeheto dto through te data nicchi sei jonno dto k entity te data convert
    // korte hobe er jonno amader Model Mapper ba Object Mapper intigreat korte hobe
    // Model Mapper er jonno ekta Bean Configuration(BeanConfig) class create korte hobe
    private final ProductRepository productRepository;
    private final String root ="Product";
    private final ModelMapper modelMapper;
    private final MailService mailService;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, MailService mailService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.mailService = mailService;
    }

    // eikhane Response builder theke success and failure response korte hobe
    // jodi create update delete get and getAll thik thak bhabe hoy

    @Override
    public Response create(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        product = productRepository.save(product);
        if(product !=null){
            return ResponseBuilder.getSuccessResponse(HttpStatus.CREATED, root + " is Successfully Created", null);
        }
        String[] to = {"subinoy@abc.com"};
        mailService.sendNonHtmlMail(to, "Test Mail", "Hello Subinoy");
        return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
    }

    @Override
    public Response update(Long id, ProductDto productDto) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            // Null gula k shey avoid korbe
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(productDto, product);
            product = productRepository.save(product);
            if(product!= null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" updated successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");

        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+ " is not found");
    }

    @Override
    public Response delete(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            product.setIsActive(false);
            product = productRepository.save(product);
            if(product != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" deleted successfully", null);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, " Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" is not found");
    }

    @Override
    public Response get(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id);
        if(product != null){
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            if(product != null){
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieve successfully", productDto);
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error Occurs");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root+" is not found");
    }

    @Override
    public Response getAll() {
        List<Product> products = productRepository.findAllByIsActiveTrue();
        List<ProductDto> productDtos = this.getProducts(products);
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root+" retrieve all successfully", productDtos);
    }

    List<ProductDto> getProducts(List<Product> products){
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            productDtos.add(productDto);
        });
        return productDtos;
    }
}
