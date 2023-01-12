package net.javaguides.springboot.web.dto;

public class UserRegistrationDto {

	private String email;
	private String password;

	private int money;
	
	public UserRegistrationDto(){}
	
	
	public UserRegistrationDto(String email, String password, int money) {
		super();
		this.email = email;
		this.password = password;
		this.money = money;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

}

