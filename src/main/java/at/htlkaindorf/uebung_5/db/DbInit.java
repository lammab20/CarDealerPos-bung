package at.htlkaindorf.uebung_5.db;

import at.htlkaindorf.uebung_5.entities.CarDealership;
import at.htlkaindorf.uebung_5.entities.MyUser;
import at.htlkaindorf.uebung_5.repos.CarDealershipRepository;
import at.htlkaindorf.uebung_5.repos.MyUserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DbInit implements ApplicationRunner {
    private final CarDealershipRepository carDealershipRepository;
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("classpath:autoDealer.json")
    Resource resource;

    private final ObjectMapper objectMapper;

    public DbInit(ObjectMapper objectMapper, PasswordEncoder passwordEncoder, CarDealershipRepository carDealershipRepository, MyUserRepository myUserRepository) {
        this.objectMapper = objectMapper;
        this.carDealershipRepository = carDealershipRepository;
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        List<CarDealership> carDealerships = objectMapper.readerForListOf(CarDealership.class)
                .readValue(resource.getFile());

        carDealerships.forEach(c-> {
            carDealershipRepository.save(c);
        });

        List<MyUser> user = myUserRepository.findAll();
        user.forEach(u -> {
            u.setPassword(passwordEncoder.encode(String.valueOf(u.getYearOfBirth())));
            myUserRepository.save(u);
        });
    }
}
