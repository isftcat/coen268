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
import web.Mateinfo;
import web.Matereq;
import web.OwnerDetails;

/**
 *
 * @author saishree
 */
@Stateless
@Path("web.mateinfo")
public class MateinfoFacadeREST extends AbstractFacade<Mateinfo> {

    @PersistenceContext(unitName = "WoofServerPU")
    private EntityManager em;

    public MateinfoFacadeREST() {
        super(Mateinfo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Mateinfo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Mateinfo entity) {
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
    public Mateinfo find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("mateList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<Mateinfo> findAll(@PathParam("ownerid") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mateinfo> q = cq.createQuery(Mateinfo.class);
 
        //select * from walkInfo,dogDetails,ownerDetails where ownerid =1
        Root<Mateinfo> fromMateInfo = q.from(Mateinfo.class);
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        //conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
conditions.add(cq.equal(fromMateInfo.get("dogId").get("ownerId"),ownerDetails));

        TypedQuery<Mateinfo> typedQuery = em.createQuery(q.select(fromMateInfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }
     
      @GET
    @Path("postMateList/{owner_id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<Mateinfo> postMateList(@PathParam("owner_id") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mateinfo> q = cq.createQuery(Mateinfo.class);
 
        Root<Mateinfo> fromMateInfo = q.from(Mateinfo.class);
        Join<Mateinfo, DogDetails> details = fromMateInfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
        DogDetails dogDetails=new DogDetails();
        conditions.add(cq.equal(fromMateInfo.get("dogId2"),dogDetails ));
        //return records before and after accept

        TypedQuery<Mateinfo> typedQuery = em.createQuery(q.select(fromMateInfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }
     
     //use to find items of available list 
    @GET
    @Path("availableMateList/{dogid}/{zipcode}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mateinfo> availableRequestMateList(@PathParam("dogid") Integer id, @PathParam("zipcode") Integer zip)  {
    Query sql=  em.createNativeQuery("select m.* from mate_info as m join dog_details as d on m.dog_id=d.dog_id join owner_details o on o.owner_id=d.owner_id  where o.owner_id!=3 and o.city=95050  and mate_info_id not in (select req_id from mate_req mr join dog_details d1 on d1.dog_id=mr.dog_id where d1.owner_id=3 ) and m.dog_id_2 is null",Mateinfo.class);
         sql.setParameter(1, id);
         sql.setParameter(2, zip);
         sql.setParameter(3, id);
         
         
         
        
        return sql.getResultList();
    }
    
    @GET
    @Path("HistoryMateList/{ownerid}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
     public List<Mateinfo> HistoryWalkList(@PathParam("ownerid") Integer id) {
        CriteriaBuilder cq = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Mateinfo> q = cq.createQuery(Mateinfo.class);
 
        Root<Mateinfo> fromMateinfo = q.from(Mateinfo.class);
        Join<Mateinfo, DogDetails> details = fromMateinfo.join("dogId");
        List<Predicate> conditions = new ArrayList<>();
        OwnerDetails ownerDetails =new OwnerDetails();
        ownerDetails.setOwnerId(id);
        conditions.add(cq.equal(details.get("ownerId"),ownerDetails ));
        conditions.add(cq.notEqual(fromMateinfo.get("dogId2"),new DogDetails() ));


        TypedQuery<Mateinfo> typedQuery = em.createQuery(q.select(fromMateinfo)
        .where(conditions.toArray(new Predicate[] {}))
        
);
        return typedQuery.getResultList();
        
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mateinfo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Mateinfo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
