/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import web.DogDetails;
import web.Matereq;
import web.OwnerDetails;
import web.WalkReq;

/**
 *
 * @author saishree
 */
@Stateless
@Path("web.matereq")
public class MatereqFacadeREST extends AbstractFacade<Matereq> {

    @PersistenceContext(unitName = "WoofServerPU")
    private EntityManager em;

    public MatereqFacadeREST() {
        super(Matereq.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Matereq entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Matereq entity) {
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
    public Matereq find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("requestedMateList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Matereq> requestedWalk(@PathParam("ownerid") Integer id)  {
        //return super.findAll();
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Matereq> q = cq.createQuery(Matereq.class);
        Root<Matereq>from=q.from(Matereq.class);
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        
     //   .where(cq.equal(from.get("walkerId"), walkerId))
       //         .where(cq.notEqual(from.get("dogId").get("ownerId"), ownerDetails))
        Predicate tempPredicate1 = cq.notEqual(from.get("reqId").get("dogId").get("ownerId"),ownerDetails);
        Predicate tempPredicate2 = cq.equal(from.get("reqId").get("dogId2"), new DogDetails());
        Predicate tempPredicate3 = cq.equal(from.get("dogId").get("ownerId"), ownerDetails);
        Predicate temp=cq.and(tempPredicate1,tempPredicate2, tempPredicate3);
        
        q.select(from).where(temp);
        return em.createQuery(q).getResultList();
               
        
    }
    
    @GET
    @Path("pendingRequestMateList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Matereq> pendingRequestWalkList(@PathParam("ownerid") Integer id)  {
        //return super.findAll();
        
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Matereq> q = cq.createQuery(Matereq.class);
        Root<Matereq>from=q.from(Matereq.class);
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        //DogDetails dogDetails =new DogDetails();
        
        Predicate tempPredicate1 = cq.equal(from.get("reqId").get("dogId").get("ownerId"),ownerDetails);
        Predicate tempPredicate2 = cq.equal(from.get("reqId").get("dogId2"), new DogDetails());
        //Predicate tempPredicate3 = cq.equal(from.get("walkerId"), ownerDetails);
        Predicate temp=cq.and(tempPredicate1,tempPredicate2);
        
        
        
        q.select(from).where(temp);
        return em.createQuery(q).getResultList();
               
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Matereq> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Matereq> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
