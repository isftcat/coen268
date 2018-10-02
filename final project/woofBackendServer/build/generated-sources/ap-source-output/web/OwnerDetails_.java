package web;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.DogDetails;
import web.WalkInfo;
import web.WalkReq;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T19:49:11")
@StaticMetamodel(OwnerDetails.class)
public class OwnerDetails_ { 

    public static volatile SingularAttribute<OwnerDetails, String> country;
    public static volatile SingularAttribute<OwnerDetails, String> address;
    public static volatile SingularAttribute<OwnerDetails, String> city;
    public static volatile CollectionAttribute<OwnerDetails, WalkInfo> walkInfoCollection;
    public static volatile SingularAttribute<OwnerDetails, String> profilepic;
    public static volatile SingularAttribute<OwnerDetails, Integer> ownerId;
    public static volatile SingularAttribute<OwnerDetails, String> ownerEmail;
    public static volatile SingularAttribute<OwnerDetails, String> token;
    public static volatile SingularAttribute<OwnerDetails, Integer> zipcode;
    public static volatile SingularAttribute<OwnerDetails, String> password;
    public static volatile SingularAttribute<OwnerDetails, String> ownerMobile;
    public static volatile SingularAttribute<OwnerDetails, String> name;
    public static volatile CollectionAttribute<OwnerDetails, DogDetails> dogDetailsCollection;
    public static volatile SingularAttribute<OwnerDetails, String> state;
    public static volatile CollectionAttribute<OwnerDetails, WalkReq> walkReqCollection;

}