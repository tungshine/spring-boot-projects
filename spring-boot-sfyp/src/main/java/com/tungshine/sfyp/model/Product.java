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

    private Integer pro_id;
    private String pro_name;
    private String pro_no;
    private String pro_desc;
    private Integer isDel;
}
