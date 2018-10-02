/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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
import web.OwnerDetails;
import web.WalkInfo;
import web.WalkReq;

/**
 *
 * @author saishree
 */
@Stateless
@Path("web.walkinfo")
public class WalkInfoFacadeREST extends AbstractFacade<WalkInfo> {

    @PersistenceContext(unitName = "WoofServerPU")
    private EntityManager em;

    public WalkInfoFacadeREST() {
        super(WalkInfo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(WalkInfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, WalkInfo entity) {
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
    public WalkInfo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Path("walkList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<WalkInfo> findAll(@PathParam("ownerid") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<WalkInfo> q = cq.createQuery(WalkInfo.class);
 
        //select * from walkInfo,dogDetails,ownerDetails where ownerid =1
        Root<WalkInfo> fromWalkinfo = q.from(WalkInfo.class);
        //Join<WalkInfo, DogDetails> details = fromWalkinfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        //conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
conditions.add(cq.equal(fromWalkinfo.get("dogId").get("ownerId"),ownerDetails ));

        TypedQuery<WalkInfo> typedQuery = em.createQuery(q.select(fromWalkinfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }
     
    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WalkInfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("postWalkList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<WalkInfo> postWalkList(@PathParam("ownerid") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<WalkInfo> q = cq.createQuery(WalkInfo.class);
 
        Root<WalkInfo> fromWalkinfo = q.from(WalkInfo.class);
        Join<WalkInfo, DogDetails> details = fromWalkinfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
        ownerDetails=new OwnerDetails();
        conditions.add(cq.equal(fromWalkinfo.get("walkerId"),ownerDetails ));


        TypedQuery<WalkInfo> typedQuery = em.createQuery(q.select(fromWalkinfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }
     
      @GET
    @Path("HistoryWalkList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<WalkInfo> HistoryWalkList(@PathParam("ownerid") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<WalkInfo> q = cq.createQuery(WalkInfo.class);
 
        Root<WalkInfo> fromWalkinfo = q.from(WalkInfo.class);
        Join<WalkInfo, DogDetails> details = fromWalkinfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
        ownerDetails=new OwnerDetails();
        conditions.add(cq.notEqual(fromWalkinfo.get("walkerId"),ownerDetails ));


        TypedQuery<WalkInfo> typedQuery = em.createQuery(q.select(fromWalkinfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }
  
     @GET
    @Path("availableWalkList/{ownerid}/{zipcode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WalkInfo> availableRequestWalkList(@PathParam("ownerid") Integer id, @PathParam("zipcode") Integer zip)  {
       
         Query sql=  em.createNativeQuery("select w.* from walk_info as w " +
                                "join dog_details as d on w.dog_id=d.dog_id " +
                                "join owner_details o on o.owner_id=d.owner_id " +
                                " where o.owner_id!=? and o.city=?  and walk_info_id not in (select req_id from walk_req where walker_id=?) and walker_id is null",WalkInfo.class);
         sql.setParameter(1, id);
         sql.setParameter(2, zip);
         sql.setParameter(3, id);
         
         
         
        
        return sql.getResultList();          
       
    }
    
     
    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<WalkInfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
