package com.tungshine.sfyp.controller;

import com.tungshine.sfyp.model.Product;
import com.tungshine.sfyp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author TangXu
 * @Date 2018/8/5
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "product/list/{pageNo}", method = RequestMethod.GET)
    public List<Product> productList(@PathVariable Integer pageNo) {
        return productService.productList(pageNo, 2);
    }
}
