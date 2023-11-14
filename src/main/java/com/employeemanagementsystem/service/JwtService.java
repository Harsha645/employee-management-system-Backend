package com.employeemanagementsystem.service;

import com.employeemanagementsystem.dto.request.LoginRequest;
import com.employeemanagementsystem.dto.response.LoginResponse;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.repisitory.EmployeeRepo;
import com.employeemanagementsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService  implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Employee employee = employeeRepo.findByEmail(username).get();

        if(employee != null){
            return new org.springframework.security.core.userdetails.User(
                    employee.getEmail(),
                    employee.getPassword(),
                    getAuthority(employee)
            );
        }else{
            throw new UsernameNotFoundException("User not found with username: "+username);
        }
    }

    private Set getAuthority(Employee employee){
        System.out.println(employee);
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

//        for(Role role: user.getRole()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
//        }

        //lambda function for above foreach loop
        employee.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });

        return authorities;
    }

    public LoginResponse createJwtToken(LoginRequest loginRequest) throws Exception{
        String username = loginRequest.getEmail();
        String userPassword = loginRequest.getPassword();

        authenticate(username,userPassword);
        UserDetails userDetails = loadUserByUsername(username);
        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        Employee employee = employeeRepo.findByEmail(username).get();

        LoginResponse loginResponse = new LoginResponse(
                employee,
                newGeneratedToken
        );

        return loginResponse;
    }

    private void authenticate(String userName, String userPassword) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (BadCredentialsException ex){
            throw new Exception("INVALID_CREDENTIALS",ex);
        }
    }


}
