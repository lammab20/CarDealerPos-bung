package at.htlkaindorf.uebung_5.repos;

import at.htlkaindorf.uebung_5.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {
    MyUser findByName(String name);
}
