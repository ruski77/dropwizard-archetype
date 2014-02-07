#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.core.${model};
import ${package}.db.DAO;
import ${package}.db.${model}DAO;
import ${package}.db.MongoManaged;
import ${package}.health.${model}ServiceHealthCheck;
import ${package}.health.MongoHealthCheck;
import ${package}.resources.${model}Resource;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class ${model}Service extends Service<${model}ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ${model}Service().run(args);
    }

    @Override
    public void initialize(Bootstrap<${model}ServiceConfiguration> bootstrap) {
        bootstrap.setName("${artifactId}");
    }

    @Override
    public void run(${model}ServiceConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.getDatabase().getHost(), configuration.getDatabase().getPort());
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);

        environment.addHealthCheck(new ${model}ServiceHealthCheck(configuration.getLoggingConfiguration()));

        environment.addHealthCheck(new MongoHealthCheck(mongo));

        DB db = mongo.getDB(configuration.getDatabase().getName());
        db.authenticate(configuration.getDatabase().getUser(), configuration.getDatabase().getPassword().toCharArray());
        JacksonDBCollection<${model}, String> ${artifactId}Collection = JacksonDBCollection.wrap(db.getCollection("${artifactId}"), ${model}.class, String.class);

        DAO<${model}, String> ${artifactId}DAO = new ${model}DAO(${artifactId}Collection);

        environment.addResource(new ${model}Resource(${artifactId}DAO));
    }
}
