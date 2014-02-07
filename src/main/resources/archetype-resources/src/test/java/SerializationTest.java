#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.core.${model};
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.yammer.dropwizard.testing.JsonHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;

public class SerializationTest {

    public static final String FIXTURE = "fixtures/${artifactId}.json";
    private static final DateTime CREATED = new DateTime(2014,01,05,20,42,11);
    private static final DateTime UPDATED = new DateTime(2014,01,05,21,42,12);
    private ${model} obj;

    @Before
    public void setUp() {
        obj = new ${model}("TEST");
        obj.setId("1");
        obj.setCreatedDate(CREATED);
        obj.setUpdatedDate(UPDATED);
    }

    @Test
    public void serializeToJson() throws IOException {
        assertThat(asJson(obj)).isEqualTo(jsonFixture(FIXTURE));
    }

    @Test
    public void loadFromJson() throws IOException {
        assertThat(fromJson(jsonFixture(FIXTURE), ${model}.class)).isEqualsToByComparingFields(obj);
    }

    @After
    public void tearDown() {
        obj = null;
    }
}
