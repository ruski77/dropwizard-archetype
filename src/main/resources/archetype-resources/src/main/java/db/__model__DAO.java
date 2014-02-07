#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.db;

import ${package}.core.${model};
import com.mongodb.BasicDBObject;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;
import net.vz.mongodb.jackson.WriteResult;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class ${model}DAO implements DAO<${model}, String> {

    private JacksonDBCollection<${model}, String> collection;

    public ${model}DAO(JacksonDBCollection<${model}, String> ${artifactId}) {
        this.collection = ${artifactId};
    }

    @Override
    public List<${model}> find() {
        DBCursor<${model}> dbCursor = collection.find();
        List<${model}> list = new ArrayList<${model}>();
        while (dbCursor.hasNext()) {
            ${model} ${artifactId} = dbCursor.next();
            list.add(${artifactId});
        }

        return list;
    }

    @Override
    public ${model} findById(String id) {
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(id));

        DBCursor<${model}> cursor = collection.find(query);

        if (cursor.hasNext()) {
            return cursor.next();
        }

        return null;
    }

    @Override
    public void update(String id, ${model} obj) {
       obj.setUpdatedDate(DateTime.now());
       collection.updateById(id, obj);
    }

    @Override
    public ${model} create(${model} obj) {
        obj.setCreatedDate(DateTime.now());
        WriteResult<${model}, String> result = collection.insert(obj);
        return result.getSavedObject();
    }

    @Override
    public int delete(String id) {
        WriteResult<${model}, String> result = collection.removeById(id);
        return result.getWriteResult().getN();
    }
}
