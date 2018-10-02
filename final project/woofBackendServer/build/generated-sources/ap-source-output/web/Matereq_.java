package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.DogDetails;
import web.Mateinfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T19:49:11")
@StaticMetamodel(Matereq.class)
public class Matereq_ { 

    public static volatile SingularAttribute<Matereq, Integer> mateReqId;
    public static volatile SingularAttribute<Matereq, DogDetails> dogId;
    public static volatile SingularAttribute<Matereq, Date> mateReqDate;
    public static volatile SingularAttribute<Matereq, Mateinfo> reqId;

}