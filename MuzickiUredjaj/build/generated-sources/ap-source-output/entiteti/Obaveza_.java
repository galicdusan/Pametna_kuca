package entiteti;

import entiteti.Alarm;
import entiteti.Destinacija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T14:48:48")
@StaticMetamodel(Obaveza.class)
public class Obaveza_ { 

    public static volatile SingularAttribute<Obaveza, Integer> obavezaId;
    public static volatile SingularAttribute<Obaveza, Date> vremePocetka;
    public static volatile SingularAttribute<Obaveza, Alarm> idA;
    public static volatile SingularAttribute<Obaveza, String> naziv;
    public static volatile SingularAttribute<Obaveza, Destinacija> idD;

}