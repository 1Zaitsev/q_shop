package qdo_ln.q_shop.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import qdo_ln.q_shop.entities.User;

@Component
public class UserProvider {

    private final Sql2o sql2o;

    private static final String SELECT_QUERY = "select id, phone_number, password, email, first_name, last_name from users where phone_number = :p_phone";

    public UserProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public User getUser(String phone){
        try(Connection connection = sql2o.open()){
            return connection.createQuery(SELECT_QUERY, false)
                    .addParameter(":p_phone", phone)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetch(User.class).get(0);
        }
    }
}
