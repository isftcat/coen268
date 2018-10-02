/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.xml.security.utils.Base64;
import web.DogDetails;
import web.OwnerDetails;
import web.WalkInfo;


/**
 *
 * @author saishree
 */
@Stateless
@Path("web.dogdetails")
public class DogDetailsFacadeREST extends AbstractFacade<DogDetails> {

    @PersistenceContext(unitName = "WoofServerPU")
    private EntityManager em;

    public DogDetailsFacadeREST() {
        super(DogDetails.class);
    }
    
    @POST
    @Path("/upload")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String uploadImage(@FormParam("image") String image) {
        String result="false";
        
        //decode Base64 String to image
        try{
            
        String pth="C:\\Users\\A\\Documents\\NetBeansProjects\\Woofapi\\WoofNewService\\build\\web\\images\\"+System.currentTimeMillis()+".jpg";
            File file=new File(pth);
            FileOutputStream fos = new FileOutputStream(file); 
            byte byteArray[] = Base64.decode(image);
            fos.write(byteArray);
             
            result="true";
            fos.close();        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return result;
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(DogDetails entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, DogDetails entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public DogDetails find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("ownerId/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DogDetails> findbyOwnerId(@PathParam("id") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<DogDetails> q = cq.createQuery(DogDetails.class);
 
        //select * from walkInfo,dogDetails,ownerDetails where ownerid =1
        Root<DogDetails> fromDogDetails = q.from(DogDetails.class);
        //Join<WalkInfo, DogDetails> details = fromWalkinfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails(id);
        
        //conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
conditions.add(cq.equal(fromDogDetails.get("ownerId"),ownerDetails ));

        TypedQuery<DogDetails> typedQuery = em.createQuery(q.select(fromDogDetails)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
        
        
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DogDetails> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<DogDetails> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
