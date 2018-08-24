package ua.webstore.auth.ejb;

import org.apache.commons.lang3.StringUtils;
import ua.webstore.auth.domain.Admin;
import ua.webstore.auth.domain.Credentials;
import ua.webstore.auth.domain.ShopUser;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class AuthenticationManagerBean {

    @PersistenceContext(unitName = "examplePU")
    private EntityManager entityManager;

    public boolean loginAsUser(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return false;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) {
            return false;
        }

        if(!password.equals(credentials.getPassword())){
            return false;
        }

        ShopUser shopUser = credentials.getShopUser();
        if(shopUser == null){
            return false;
        }

        return true;
    }

    public boolean loginAsAdmin(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return false;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) {
            return false;
        }

        if(!password.equals(credentials.getPassword())){
            return false;
        }

        Admin admin = credentials.getAdmin();
        if(admin == null){
            return false;
        }

        return true;
    }

}
