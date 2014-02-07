#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.db;

import java.util.List;

public interface DAO<T, K> {

    public List<T> find();

    public T findById(K id);

    public void update(K id, T obj);

    public T create(T obj);

    public int delete(K id);
}
