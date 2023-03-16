package dev.rahulrajesh.PicPayData.services;

import dev.rahulrajesh.PicPayData.model.Login;
import dev.rahulrajesh.PicPayData.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LoginService {
    private final LoginRepository loginRepository;
    private final LoginCreatorDestroyer loginCreatorDestroyer;
    @Autowired
    public LoginService(LoginRepository loginRepository, LoginCreatorDestroyer loginCreatorDestroyer) {
        this.loginRepository = loginRepository;
        this.loginCreatorDestroyer = loginCreatorDestroyer;
    }

    /**
     * currently not working
     * */
    public Login createLogin(Login login) {
        return loginRepository.save(login);
    }

    public Login createLoginJDBCTemplate(Login login) {
        loginCreatorDestroyer.createLogin(login.getUser(), login.getUsername(), login.getPassword());

        return login;
    }

    public Login deleteLoginJDBCTemplate(String username) {
        Login resultLogin = loginRepository.findById(username)
                .orElseThrow(NoSuchElementException::new);

        loginCreatorDestroyer.destroyLoginByUsername(username);

        return resultLogin;
    }

    public Login getLogin(String username) throws NoSuchElementException{
        return loginRepository.findById(username)
                .orElseThrow(NoSuchElementException::new);
    }
}
