#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.resources;

import ${package}.core.${model};
import ${package}.db.DAO;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.concurrent.TimeUnit;

@Path("/${artifactId}")
@Produces(MediaType.APPLICATION_JSON)
public class ${model}Resource {

    private static final Logger log = LoggerFactory.getLogger(${model}Resource.class);

    private final DAO<${model}, String> ${artifactId}DAO;

    public ${model}Resource(DAO ${artifactId}DAO) {
        this.${artifactId}DAO = ${artifactId}DAO;
    }

    @GET
    @Timed
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public Response retrieveAll() {
        try {
            return Response.ok().entity(${artifactId}DAO.find()).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{param}")
    @Timed
    @CacheControl(maxAge = 6, maxAgeUnit = TimeUnit.HOURS)
    public Response retrieveById(@PathParam("param") String param) {
        try {
            ${model} ${artifactId} = ${artifactId}DAO.findById(param);

            if (${artifactId} != null) {
                UriBuilder.fromResource(${model}Resource.class).build("hello");
                return Response.ok().entity(${artifactId}).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }


        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @POST
    @Timed
    public Response create(@Valid ${model} ${artifactId}) {
        try {
            ${model} result = ${artifactId}DAO.create(${artifactId});
            return Response.created(new URI(result.getId())).build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Timed
    @Path("/{param}")
    public Response update(@PathParam("param") String param, @Valid ${model} ${artifactId}) {
        try {
            ${model} existing = ${artifactId}DAO.findById(param);

            if (existing != null) {
                ${artifactId}.setCreatedDate(existing.getCreatedDate());
                ${artifactId}DAO.update(param, ${artifactId});
                return Response.ok().build();
            } else {
                ${model} result = ${artifactId}DAO.create(${artifactId});
                return Response.created(new URI(result.getId())).build();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Timed
    @Path("/{param}")
    public Response delete(@PathParam("param") String param) {
        try {
            int affectedObjects = ${artifactId}DAO.delete(param);

            if (affectedObjects != 1) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.noContent().build();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}