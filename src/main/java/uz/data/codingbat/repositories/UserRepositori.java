package uz.data.codingbat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.data.codingbat.entities.User;

public interface UserRepositori extends JpaRepository<User, Integer> {
    boolean existsUserByEmail(String email);

    boolean existsUserByPassword(String password);

    boolean existsUserByEmailAndIdNot(String email, Integer id);

    boolean existsUserByPasswordAndIdNot(String password, Integer id);
}
