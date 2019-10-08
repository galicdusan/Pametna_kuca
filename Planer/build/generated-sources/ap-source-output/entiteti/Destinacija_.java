package entiteti;

import entiteti.Obaveza;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-13T02:13:35")
@StaticMetamodel(Destinacija.class)
public class Destinacija_ { 

    public static volatile SingularAttribute<Destinacija, Integer> destinacijaId;
    public static volatile SingularAttribute<Destinacija, Double> yKoordinata;
    public static volatile CollectionAttribute<Destinacija, Obaveza> obavezaCollection;
    public static volatile SingularAttribute<Destinacija, Double> xKoordinata;
    public static volatile SingularAttribute<Destinacija, String> naziv;

}