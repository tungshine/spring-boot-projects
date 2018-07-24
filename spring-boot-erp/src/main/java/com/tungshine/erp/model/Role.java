package com.tungshine.erp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:26 2018/5/4
 * @Modified By:
 */
@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = -4784736575351107015L;
    private Long id;
    private String name;
}
