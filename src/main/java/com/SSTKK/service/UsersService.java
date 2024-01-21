package com.SSTKK.service;
import com.SSTKK.model.UsersModel;
import com.SSTKK.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UsersModel registrationuser(String login, String password ,String email){
        if (login == null && password == null){
            return null;
        }else
        {
            if (usersRepository.findFirstByLogin(login).isPresent()){
                System.out.println("Duplicate login");
                return null;
            }
            UsersModel usersModel = new UsersModel();
            usersModel.setLogin(login);
            usersModel.setPassword(password);
            usersModel.setEmail(email);
            usersModel.setRole(UsersModel.Role.USER);
            return usersRepository.save(usersModel);
        }
    }
    public  UsersModel authenticate(String login,String password){
        return usersRepository.findByLoginAndPassword(login,password).orElse(null);
    }
    public boolean deleteUser(Integer idUser){
        usersRepository.deleteById(idUser);
        return usersRepository.findById(idUser) != null ? true : false;
    }
    public List<UsersModel> getAllUsers(){
        return usersRepository.findAll();
    }

    public void updateUser(UsersModel updateUser){
        usersRepository.updateUserWithId(updateUser.getId(),updateUser.getPassword(),updateUser.getLogin(),updateUser.getEmail());
    }
}
