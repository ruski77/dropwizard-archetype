#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.db.DatabaseConfig;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.constraints.NotNull;

public class ${model}ServiceConfiguration extends Configuration {

    @JsonProperty("database")
    @NotNull
    private DatabaseConfig database = new DatabaseConfig();

    public DatabaseConfig getDatabase() {
        return database;
    }
}
