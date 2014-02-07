#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.health;

import com.yammer.dropwizard.config.LoggingConfiguration;
import com.yammer.metrics.core.HealthCheck;

public class ${model}ServiceHealthCheck extends HealthCheck {

    private LoggingConfiguration loggingConfiguration;

    public ${model}ServiceHealthCheck(LoggingConfiguration loggingConfiguration) {
        super("${model}ServiceHealthCheck");
        this.loggingConfiguration = loggingConfiguration;
    }

    @Override
    protected Result check() throws Exception {
        //Ensure file logging is enabled for the service
        if (loggingConfiguration == null || loggingConfiguration.getFileConfiguration() == null || !loggingConfiguration.getFileConfiguration().isEnabled()) {
            return Result.unhealthy("File logging in not enabled!");
        }
        return Result.healthy();
    }
}
