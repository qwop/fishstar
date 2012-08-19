package com.tan.test.bean;



public class Person {
	/** 姓名*/
	private String name;
	// 年龄
	private int age;
	private float height; // 身高
	/**
	 * 体重
	 */
	private double weight;
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
}