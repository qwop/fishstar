package com.tan.test.bean;



public class Person {
	/** 姓名,*/
	private String name;
	// 年龄.
	private int age;
	private float height; // '身高.
	/**
	 * 体重
	 */
	private double weight;
	
    private String strareapath; // 地区路径
    // private String strareaname;// 区域名称（所属区域）

    
    /**
     * 关联： strspid; //合作伙伴ID 联系人信息
     * 
     */
    private String strleaderman; // 公司领导姓名
    
	
	public String getName() {
		return name;
	}
	public void setName( String name ) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge( int age ) {
		this.age = age;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight( float height ) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight( double weight ) {
		this.weight = weight;
	}
	public String getStrareapath() {
		return strareapath;
	}
	public void setStrareapath(String strareapath) {
		this.strareapath = strareapath;
	}
	public String getStrleaderman() {
		return strleaderman;
	}
	public void setStrleaderman(String strleaderman) {
		this.strleaderman = strleaderman;
	}
	public static void main( String[] args ) {
		Person dest = new Person();
		Person src = new Person();
		// 获取姓名,.
		System.out.printf( "%-20s %-10s\n", "姓名,\t" , src.getName() );
		// 获取年龄.
		System.out.printf( "%-20s %-10s\n", "年龄\t" , src.getAge() );
		// 获取身高.
		System.out.printf( "%-20s %-10s\n", "身高\t" , src.getHeight() );
		// 获取体重.
		System.out.printf( "%-20s %-10s\n", "体重\t" , src.getWeight() );
		// 获取地区路径.
		System.out.printf( "%-20s %-10s\n", "地区路径\t" , src.getStrareapath() );
		// 获取关联： strspid; //合作伙伴ID 联系人信息.
		System.out.printf( "%-20s %-10s\n", "关联： strspid; //合作伙伴ID 联系人信息\t" , src.getStrleaderman() );
	}
	
	
}