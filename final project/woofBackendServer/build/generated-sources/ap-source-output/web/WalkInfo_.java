package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.DogDetails;
import web.OwnerDetails;
import web.WalkReq;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T19:49:11")
@StaticMetamodel(WalkInfo.class)
public class WalkInfo_ { 

    public static volatile SingularAttribute<WalkInfo, DogDetails> dogId;
    public static volatile SingularAttribute<WalkInfo, OwnerDetails> walkerId;
    public static volatile SingularAttribute<WalkInfo, Date> fromTime;
    public static volatile SingularAttribute<WalkInfo, Integer> walkInfoId;
    public static volatile SingularAttribute<WalkInfo, Date> walkInfoDate;
    public static volatile CollectionAttribute<WalkInfo, WalkReq> walkReqCollection;
    public static volatile SingularAttribute<WalkInfo, Date> toTime;

}