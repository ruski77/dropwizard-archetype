#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class DatabaseConfig {

    @JsonProperty
    @NotEmpty
    private String host;

    @JsonProperty
    @Min(1)
    @Max(65535)
    private int port;

    @JsonProperty
    @NotEmpty
    private String name;

    @JsonProperty
    private String user;

    @JsonProperty
    private String password;

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
