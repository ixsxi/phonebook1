package com.javaex.vo;

public class PersonVo {

	
	
	//필드
	private int personId;
	private String Name;
	private String Hp;
	private String company;

	
	
	//생성자
	public PersonVo() {
		super();
	}



	public PersonVo(int personId) {
		super();
		this.personId = personId;
	}



	public PersonVo(String name, String hp, String company) {
		super();
		Name = name;
		Hp = hp;
		this.company = company;
	}



	public PersonVo(int personId, String name, String hp, String company) {
		super();
		this.personId = personId;
		Name = name;
		Hp = hp;
		this.company = company;
	}

	
	//겟셋 메소드
	
	
	public int getPersonId() {
		return personId;
	}



	public void setPersonId(int personId) {
		this.personId = personId;
	}



	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getHp() {
		return Hp;
	}



	public void setHp(String hp) {
		Hp = hp;
	}



	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}


	
	
	//메소드 일반
	
	
	@Override
	public String toString() {
		return "PersonVo [personId=" + personId + ", Name=" + Name + ", Hp=" + Hp + ", company=" + company + "]";
	}
	
}
