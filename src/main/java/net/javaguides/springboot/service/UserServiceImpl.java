package net.javaguides.springboot.service;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Role;
import net.javaguides.springboot.model.UserWithMoney;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}


	@Override
	public UserWithMoney save(UserRegistrationDto registrationDto) {
		UserWithMoney user=new UserWithMoney(
							registrationDto.getEmail(),
							registrationDto.getPassword(),
							registrationDto.getMoney(),
							passwordEncoder.encode(registrationDto.getPassword()),
							Arrays.asList(new Role("ROLE_USER")));
		
		return userRepository.save(user);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		
		UserWithMoney user=userRepository.findByEmail(username);
		
		if(user==null)
		{
			throw new UsernameNotFoundException("Invalid Username or password");			
		}
		return new UserWithMoney(user.getEmail(),  user.getPassword(),mapRolesToAuthorities(user.getRoles()), user.getMoney());
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	
	}
}
