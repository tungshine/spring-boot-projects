package com.tungshine.sfyp.service;

import com.github.pagehelper.PageHelper;
import com.tungshine.sfyp.mapper.ProductMapper;
import com.tungshine.sfyp.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by TangXu on 2016/12/2.
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    ProductMapper productMapper;

    /**
     * 分页获取商品列表
     *
     * @param pageNo
     * @return
     */
    public List<Product> productList(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<Product> products = productMapper.getProductList();
        return products;
    }

}
