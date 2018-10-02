package web;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import web.Dogpics;
import web.Mateinfo;
import web.Matereq;
import web.OwnerDetails;
import web.WalkInfo;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-03-15T19:49:11")
@StaticMetamodel(DogDetails.class)
public class DogDetails_ { 

    public static volatile CollectionAttribute<DogDetails, Mateinfo> mateinfoCollection;
    public static volatile SingularAttribute<DogDetails, Integer> dogId;
    public static volatile SingularAttribute<DogDetails, String> gender;
    public static volatile CollectionAttribute<DogDetails, WalkInfo> walkInfoCollection;
    public static volatile SingularAttribute<DogDetails, Date> dob;
    public static volatile CollectionAttribute<DogDetails, Mateinfo> mateinfoCollection1;
    public static volatile SingularAttribute<DogDetails, String> name;
    public static volatile CollectionAttribute<DogDetails, Matereq> matereqCollection;
    public static volatile SingularAttribute<DogDetails, String> pic;
    public static volatile SingularAttribute<DogDetails, OwnerDetails> ownerId;
    public static volatile CollectionAttribute<DogDetails, Dogpics> dogpicsCollection;
    public static volatile SingularAttribute<DogDetails, String> breed;

}