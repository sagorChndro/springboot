package com.sagor.springcrudrestapiemailsecur.service;

import com.sagor.springcrudrestapiemailsecur.model.Address;
import com.sagor.springcrudrestapiemailsecur.model.Employee;
import com.sagor.springcrudrestapiemailsecur.model.Role;
import com.sagor.springcrudrestapiemailsecur.model.User;
import com.sagor.springcrudrestapiemailsecur.repository.AddressRepository;
import com.sagor.springcrudrestapiemailsecur.repository.EmployeeRepository;
import com.sagor.springcrudrestapiemailsecur.repository.RoleRepository;
import com.sagor.springcrudrestapiemailsecur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DbInit {
    // repository interface gula inject ba autowired korte hobe

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${login.username}")
    private String username;
    @Value("${login.password}")
    private String password;
    private final AddressRepository addressRepository;

    public DbInit(UserRepository userRepository,EmployeeRepository employeeRepository, AddressRepository addressRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

   // @PostConstruct  //ei annotation ta application run houwar sathe sathe init method ta run korabe
    public void init(){
//        Address address = new Address();
//        address.setCityName("Mirpur-12, Dhaka");
//        address.setCountryName("Bangladesh");
//        //address = addressRepository.save(address); //eita korte hobe na abar jodi
//        // cascadetype merge hoy tahole ei save method ta korte dite hobe na hole shey
//        // exception dekhabe
//        Employee employee = new Employee();
//        employee.setName("Bikash Chandro Mondol");
//        //employee.setAddress(address);
//        employee.setAddresses(Arrays.asList(address));
//        employee = employeeRepository.save(employee);
//        address.setEmployee(employee);
//        address = addressRepository.save(address);
//        System.out.println("Employee Id is : "+employee.getId());
//        System.out.println("Address Id is : "+address.getId());

            // delete processing
//        Employee employee = employeeRepository.findById(Long.valueOf(1)).get();
//        employeeRepository.delete(employee);
        String roleName = "ROLE_ADMIN";
        int roleExistCount = roleRepository.countByName(roleName);
        Role role = null;
        if(roleExistCount == 1){
            role = roleRepository.findByName(roleName);
        }else {
            role = new Role();
            role.setName(roleName);
            role = roleRepository.save(role);
        }
        User user = userRepository.findByUsernameAndIsActiveTrue(username);
        if(user == null){
            user = new User();
            user.setEmail("abc@ab.com");
            user.setName(username);
            user.setPassword(passwordEncoder.encode(password));
        }
        user.setRoles(Arrays.asList(role));
        user = userRepository.save(user);
    }
}
