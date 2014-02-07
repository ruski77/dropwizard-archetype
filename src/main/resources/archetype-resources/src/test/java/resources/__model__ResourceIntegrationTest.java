#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import ${package}.core.${model};
import ${package}.db.${model}DAO;
import com.yammer.dropwizard.testing.ResourceTest;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ${model}ResourceIntegrationTest extends ResourceTest {

    private ${model} ${artifactId};
    private final ${model}DAO dao = mock(${model}DAO.class);
    private static final DateTime CREATED = new DateTime(2014,01,05,20,42,11);
    private static final DateTime UPDATED = new DateTime(2014,01,05,21,42,12);

    @Override
    protected void setUpResources() throws Exception {
        ${artifactId} = new ${model}("TEST");
        ${artifactId}.setId("1");
        ${artifactId}.setCreatedDate(CREATED);
        ${artifactId}.setUpdatedDate(UPDATED);

        when(dao.findById(anyString())).thenReturn(${artifactId});

        addResource(new ${model}Resource(dao));
    }

    @Test
    public void getRequest() {
       assertThat(client().resource("/${artifactId}/1").get(${model}.class)).isEqualsToByComparingFields(${artifactId});
       verify(dao).findById("1");
    }
}
