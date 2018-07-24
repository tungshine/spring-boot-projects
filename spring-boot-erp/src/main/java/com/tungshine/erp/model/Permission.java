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
public class Permission implements Serializable {
	private static final long serialVersionUID = 9120513648185779805L;
	private Long id;
	private String name;
	private String url;

	@Override
	public String toString() {
		return getName();
	}
}
