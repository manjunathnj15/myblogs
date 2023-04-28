package com.myblogs.myblogs;



import com.myblogs.myblogs.entity.Role;
import com.myblogs.myblogs.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBlogRestApiApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);
    }

}
