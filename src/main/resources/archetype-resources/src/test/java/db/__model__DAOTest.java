#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.db;

import ${package}.core.${model};
import com.mongodb.DB;
import com.mongodb.Mongo;
import net.vz.mongodb.jackson.JacksonDBCollection;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ${model}DAOTest {

    private Mongo mongo;
    private DB db;
    private JacksonDBCollection<${model}, String> ${artifactId}Collection;
    private DAO<${model}, String> ${artifactId}DAO;
    private ${model} ${artifactId};

    @Before
    public void setUp() throws Exception {
        mongo = new Mongo("localhost", 27017);
        db = mongo.getDB("temp");
        ${artifactId}Collection = JacksonDBCollection.wrap(db.getCollection("${artifactId}"), ${model}.class, String.class);

        ${artifactId}DAO = new ${model}DAO(${artifactId}Collection);

        ${artifactId} = new ${model}("TEST");
        ${artifactId}.setCreatedDate(DateTime.now());
    }

    @Test
    public void save${model}Data() {
        List<${model}> list = ${artifactId}DAO.find();
        assertEquals("Initial list not equal to zero", 0, list.size());

        ${model} savedObj = ${artifactId}DAO.create(${artifactId});
        assertNotNull("Saved ${model} is null", savedObj);
        assertEquals("Saved foo value is unexpected", "TEST", savedObj.getFoo());

        list = ${artifactId}DAO.find();
        assertEquals("List after insert not equal to one", 1, list.size());

        savedObj = ${artifactId}DAO.findById(savedObj.getId());
        assertNotNull("Retrieved ${model} is null", savedObj);
        assertEquals("Retrieved foo value is unexpected", "TEST", savedObj.getFoo());

        ${model} updated = new ${model}("TESTING");
        updated.setCreatedDate(savedObj.getCreatedDate());
        ${artifactId}DAO.update(savedObj.getId(), updated);

        list = ${artifactId}DAO.find();
        assertEquals("List after update not equal to one", 1, list.size());

        savedObj = ${artifactId}DAO.findById(savedObj.getId());
        assertNotNull("Updated ${model} is null", savedObj);
        assertEquals("Updated foo value is unexpected", "TESTING", savedObj.getFoo());

        int affectedObjects = ${artifactId}DAO.delete(savedObj.getId());
        assertEquals("Items deleted should only be one", 1, affectedObjects);

        list = ${artifactId}DAO.find();
        assertEquals("List after delete not equal to zero", 0, list.size());
    }

    @After
    public void tearDown() {
        db.dropDatabase();
        ${artifactId}DAO = null;
        ${artifactId}Collection = null;
        db = null;
        mongo = null;
        ${artifactId} = null;
    }
}
