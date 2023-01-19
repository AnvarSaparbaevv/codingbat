package uz.data.codingbat.services.implementss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.data.codingbat.entities.User;
import uz.data.codingbat.repositories.UserRepositori;
import uz.data.codingbat.templates.Result;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepositori userRepositori;

    public List<User> getAllUSers(){
        return userRepositori.findAll();
    }

    public User getByID(Integer id){
        Optional<User> byId = userRepositori.findById(id);
        return byId.orElse(null);
    }

    public Result addUser(User user){
        if (userRepositori.existsUserByEmail(user.getEmail())){
            return new Result("This Email already exists!", false, HttpStatus.CONFLICT);
        }
        if (userRepositori.existsUserByPassword(user.getPassword())){
            return new Result("This Password already exists!", false, HttpStatus.CONFLICT);
        }
        if (user.getPassword().length()<5){
            return new Result("The password should have at least 6 characters!", false, HttpStatus.CONFLICT);
        }
        User userNew=new User(null,user.getEmail(), user.getPassword());
        userRepositori.save(userNew);
        return new Result("Successfully saved!", true, HttpStatus.CREATED);
    }

    public Result updateUser(Integer id, User dto){
        Optional<User> byId = userRepositori.findById(id);
        if (byId.isEmpty()){
            return new Result("User not found!", false, HttpStatus.NOT_FOUND);
        }
        if (userRepositori.existsUserByEmailAndIdNot(dto.getEmail(),id)){
            return new Result("This Email already exists!", false, HttpStatus.CONFLICT);
        }
        if (userRepositori.existsUserByPasswordAndIdNot(dto.getPassword(),id)){
            return new Result("This Password already exists!", false, HttpStatus.CONFLICT);
        }
        if (dto.getPassword().length()<5){
            return new Result("The password should have at least 6 characters!", false, HttpStatus.CONFLICT);
        }
        User user = byId.get();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        userRepositori.save(user);
        return new Result("User updated!", true, HttpStatus.ACCEPTED);
    }

    public Result deleteUser(Integer id){
        Optional<User> byId = userRepositori.findById(id);
        if (byId.isEmpty()){
            return new Result("User not found!", false, HttpStatus.NOT_FOUND);
        }
        userRepositori.deleteById(id);
        return new Result("User deleted!", true, HttpStatus.ACCEPTED);
    }

}
