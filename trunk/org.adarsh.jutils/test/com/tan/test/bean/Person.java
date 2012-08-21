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
	public static void main(final String[] args ) {
		Person vo = new Person();
		Person po = new Person();
		// 获取姓名
		System.out.println( "姓名:\t" + po.getName() );
		// 获取年龄
		System.out.println( "年龄:\t" + po.getAge() );
		// 获取身高
		System.out.println( "身高:\t" + po.getHeight() );
		// 获取体重
		System.out.println( "体重:\t" + po.getWeight() );
	}
	
	
	public void dummy() {
		Person vo = new Person();
		Person po = new Person();
		// 获取姓名
		po.getName();
		// 获取年龄
		po.getAge();
		// 获取身高
		po.getHeight();
		// 获取体重
		po.getWeight();
	}
	
	public void dummySet() {
		Person dest = new Person();
		Person src = new Person();
		// 设置姓名
		dest.setName(src.getName());
		// 设置年龄
		dest.setAge(src.getAge());
		// 设置身高
		dest.setHeight(src.getHeight());
		// 设置体重
		dest.setWeight(src.getWeight());
	}
	public void dummyHtml() {/**
	<table class="">
		<thead>
			<tr>
				<th>姓名, <a href="#">姓名,</a></th>
				<th>年龄 <a href="#">年龄</a></th>
				<th>身高 <a href="#">身高</a></th>
				<th>体重 <a href="#">体重</a></th>
	
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${Person.name}</td>
				<td>${Person.age}</td>
				<td>${Person.height}</td>
				<td>${Person.weight}</td>
	
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">test</td>
	
			</tr>
		</tfoot>
	</table>
	*/}
	

}