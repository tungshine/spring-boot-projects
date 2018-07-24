package com.tungshine.sfyp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @ Author: TungShine
 * @ Description:
 * @ Date: Create in 15:38 2018/7/19
 * @ Modified By:
 */
@Getter
@Setter
public class Product implements Serializable {
    private static final long serialVersionUID = 572091108550152849L;

    private Integer id;
    private String name;
    private String no;
    private Integer isDel;
}
