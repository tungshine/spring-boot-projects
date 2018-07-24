package com.tungshine.erp.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 17:12 2018/5/3
 * @Modified By:
 */
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 690792600710173942L;
	private Long id;

	private String name;

	private String password;

	private List<String> roleNameList;

	private List<String> permissionNameList;

	@Override
	public String toString() {
		return "User" + getId() + "name is : " + getName();
	}
}
