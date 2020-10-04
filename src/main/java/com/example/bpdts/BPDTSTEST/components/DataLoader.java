package com.example.bpdts.BPDTSTEST.components;

import com.example.bpdts.BPDTSTEST.models.User;
import com.example.bpdts.BPDTSTEST.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    UserRepository userRepository;
    CreateAPI createAPI;

    public DataLoader() {

    }

    public void run(ApplicationArguments args) {
//        User user1 = new User("Andrew", "Watson", "awatson.work@gmail.com", "1.234.567.89", 55.1270, 1.5103);
//        userRepository.save(user1);
//
//        User user2 = new User("Eleanor", "Poole", "eleanorbpoole@gmail.com", "2.345.678.91.0", 23.5645, 1.2345);
//        userRepository.save(user2);

//        createAPI.getApi();
    }

}
