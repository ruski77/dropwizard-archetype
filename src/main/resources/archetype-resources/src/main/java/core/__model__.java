#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.vz.mongodb.jackson.ObjectId;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;

public class ${model} {

    private String id;

    private String foo;

    private DateTime createdDate;

    private DateTime updatedDate;

    public ${model}() { /* Jackson deserialization */ }

    public ${model}(String foo) {
        this.foo = foo;
    }

    @JsonProperty
    public String getFoo() {
        return foo;
    }

    @NotBlank
    @JsonProperty
    public void setFoo(String foo) {
        this.foo = foo;
    }

    @JsonProperty
    @JsonDeserialize(using = DateDeserializer.class)
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @JsonProperty
    @JsonSerialize(using = DateSerializer.class)
    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    @JsonProperty
    @JsonDeserialize(using = DateDeserializer.class)
    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    @JsonProperty
    @JsonSerialize(using = DateSerializer.class)
    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @ObjectId
    @JsonProperty("_id")
    public String getId() {
        return id;
    }

    @ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ${model})) return false;
        if (obj == this) return true;

        if (id == ((${model}) obj).getId() && foo == ((${model}) obj).getFoo()) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        String cDate = "";
        String uDate = "";
        if (createdDate != null)   {
           cDate = createdDate.toString();
        }
        if (updatedDate != null){
            uDate = updatedDate.toString();
        }
        return id + foo + cDate + uDate;
    }
}