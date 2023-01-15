package net.javaguides.springboot.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserDetailsWithMoney extends User {
	private int money;

	public UserDetailsWithMoney(String username, String password, Collection<? extends GrantedAuthority> authorities, int money) {
		super(username, password, authorities);
		this.money = money;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
