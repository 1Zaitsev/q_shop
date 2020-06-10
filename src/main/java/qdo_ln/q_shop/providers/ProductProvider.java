package qdo_ln.q_shop.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import qdo_ln.q_shop.entities.Product;

@Component
public class ProductProvider {
    private final Sql2o sql2o;
    private static final String SELECT_QUERY = "select id, title from products where id = :p_id";

    public ProductProvider(@Autowired Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public Product getProductById(int id){
        try(Connection connection = sql2o.open()){
            return connection.createQuery(SELECT_QUERY, false)
                    .addParameter(":p_id", id)
                    .executeAndFetch(Product.class).get(0);
        }
    }
}
