package entiteti;

import entiteti.Obaveza;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T14:48:48")
@StaticMetamodel(Destinacija.class)
public class Destinacija_ { 

    public static volatile SingularAttribute<Destinacija, Integer> destinacijaId;
    public static volatile SingularAttribute<Destinacija, Integer> yKoordinata;
    public static volatile CollectionAttribute<Destinacija, Obaveza> obavezaCollection;
    public static volatile SingularAttribute<Destinacija, Integer> xKoordinata;
    public static volatile SingularAttribute<Destinacija, String> naziv;

}