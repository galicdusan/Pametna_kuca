package entiteti;

import entiteti.Slusao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T16:21:45")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile CollectionAttribute<Korisnik, Slusao> slusaoCollection;
    public static volatile SingularAttribute<Korisnik, Integer> korisnikId;

}