/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import javax.ws.rs.core.Response;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;
import web.OwnerDetails;

/**
 *
 * @author saishree
 */
@Stateless
@Path("web.ownerdetails")
public class OwnerDetailsFacadeREST extends AbstractFacade<OwnerDetails> {

    @PersistenceContext(unitName = "WoofServerPU")
    private EntityManager em;

    public OwnerDetailsFacadeREST() {
        super(OwnerDetails.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(OwnerDetails entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Integer id, OwnerDetails entity) {
        try{
          String fileStartingLoc = "/Users/saishree/NetBeansProjects/WoofServer/web/images/";
            System.out.println("hit this api to upload");
            File file=new File(fileStartingLoc + id + ".jpg");
            FileOutputStream fos = new FileOutputStream(file); 
            byte byteArray[] = Base64.decodeBase64(entity.getProfilepic());
            fos.write(byteArray);     
            fos.close();        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        OwnerDetails ownerDetailsOnDb = super.find(id);
        ownerDetailsOnDb.setName(entity.getName());
        ownerDetailsOnDb.setAddress(entity.getAddress());
        ownerDetailsOnDb.setOwnerEmail(entity.getOwnerEmail());
        ownerDetailsOnDb.setOwnerMobile(entity.getOwnerMobile());
        super.edit(ownerDetailsOnDb);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }
    
    @GET
    @Path("login/{email}/{pswd}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public OwnerDetails Login(@PathParam("email") String email,@PathParam("pswd") String pswd) {
        
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<OwnerDetails> q = cq.createQuery(OwnerDetails.class);
        Root<OwnerDetails>from=q.from(OwnerDetails.class);
        
        
        
     //   .where(cq.equal(from.get("walkerId"), walkerId))
       //         .where(cq.notEqual(from.get("dogId").get("ownerId"), ownerDetails))
        Predicate tempPredicate1 = cq.equal(from.get("ownerEmail"),email);
        Predicate tempPredicate2 = cq.equal(from.get("password"), pswd);
        
        Predicate temp=cq.and(tempPredicate1,tempPredicate2);
        
        q.select(from).where(temp);
        
        return em.createQuery(q).getSingleResult(); 
    }
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public OwnerDetails find(@PathParam("id") Integer id) {
        return super.find(id);       
        //return Response.ok(owndb,MediaType.APPLICATION_JSON_TYPE).build();
    }
    
    
    @GET
    @Path("/getImage/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.APPLICATION_FORM_URLENCODED})
    public Response getImage(@PathParam("id") Integer id) {
        String imageString = null;
            String encodedImage = null;byte[] ba = null;
        try {
            String fileStartingLoc = "/Users/saishree/NetBeansProjects/WoofServer/web/images/";
            File originalFile = new File(fileStartingLoc+id+".jpg");
            BufferedImage img = ImageIO.read(originalFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            baos.flush();
            Base64 base = new Base64();
             ba = baos.toByteArray();
             
            baos.close();
            
        } catch (IOException ex) {
            Logger.getLogger(OwnerDetailsFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
return Response.ok(ba, MediaType.APPLICATION_OCTET_STREAM_TYPE).build();
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<OwnerDetails> findAll() {
        return super.findAll();
    }
    
   
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<OwnerDetails> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
