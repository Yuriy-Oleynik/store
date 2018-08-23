package ua.webstore.auth;

import org.apache.commons.lang3.StringUtils;
import ua.webstore.auth.ejb.AuthenticationManagerBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthBean implements Serializable {

    private boolean loggedIn;
    private String login;
    private String password;

    @EJB
    private AuthenticationManagerBean authenticationManagerBean;

    public boolean isLoggedIn() {

        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {

        this.loggedIn = loggedIn;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void doLogin() {

        if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password)) {
            loggedIn = false;
            return;
        }

        loggedIn = authenticationManagerBean.loginAsUser(login, password);
    }

}
