package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.DogDetails;
import web.Matereq;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T19:49:11")
@StaticMetamodel(Mateinfo.class)
public class Mateinfo_ { 

    public static volatile SingularAttribute<Mateinfo, DogDetails> dogId;
    public static volatile CollectionAttribute<Mateinfo, Matereq> matereqCollection;
    public static volatile SingularAttribute<Mateinfo, DogDetails> dogId2;
    public static volatile SingularAttribute<Mateinfo, Date> mateDate;
    public static volatile SingularAttribute<Mateinfo, Integer> mateInfoId;

}