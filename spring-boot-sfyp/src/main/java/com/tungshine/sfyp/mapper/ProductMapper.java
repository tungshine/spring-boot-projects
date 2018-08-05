package com.tungshine.sfyp.mapper;

import com.tungshine.sfyp.model.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author TangXu
 * @Date 2018/8/5
 */
@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM t_product")
    List<Product> getProductList();
}
