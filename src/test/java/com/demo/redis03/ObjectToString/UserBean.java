package com.demo.redis03.ObjectToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBean implements Serializable {
	
//	private static final long serialVersionUID = 1L;
	
	private String name;
	private String pwd;
	private Long number;
	private String address;
	private Date updateDate;
	private List lists;
	private Map<String, Object> maps;
	
	public UserBean(){
		super();
		this.name="jerry";
		this.pwd="11";
		this.number=11L;
		this.address="西安市";
		this.updateDate=new Date();
		
		List<String> list=new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add("你好呀:"+i);
		}
		this.lists=list;
		
		Map<String, Object> map=new HashMap<String, Object>();
		for(int i=0;i<10;i++){
			map.put("我是map"+i, new Date());
		}
		maps=map;
	}
	
	@Override
	public String toString() {
		return "UserBean [name=" + name + ", pwd=" + pwd + ", number=" + number + ", address=" + address
				+ ", updateDate=" + updateDate + ", lists=" + lists + ", maps=" + maps + "]";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public List<String> getLists() {
		return lists;
	}
	public void setLists(List<String> lists) {
		this.lists = lists;
	}
	public Map<String, Object> getMaps() {
		return maps;
	}
	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}

}
