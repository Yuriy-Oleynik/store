package ua.webstore.auth.ejb;

import org.apache.commons.lang3.StringUtils;
import ua.webstore.auth.domain.Credentials;
import ua.webstore.auth.domain.Role;
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

    public Role login(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return null;
        }

        Credentials credentials = entityManager.find(Credentials.class, email);
        if (credentials == null) {
            return null;
        }

        if(!password.equals(credentials.getPassword())){
            return null;
        }

        ShopUser shopUser = credentials.getShopUser();
        if(shopUser == null){
            return null;
        }

        return shopUser.getRole();
    }

}
