package entiteti;

import entiteti.Obaveza;
import entiteti.Pesma;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T16:21:45")
@StaticMetamodel(Alarm.class)
public class Alarm_ { 

    public static volatile SingularAttribute<Alarm, Date> vremeAktivacije;
    public static volatile SingularAttribute<Alarm, Pesma> idP;
    public static volatile CollectionAttribute<Alarm, Obaveza> obavezaCollection;
    public static volatile SingularAttribute<Alarm, Integer> alarmId;
    public static volatile SingularAttribute<Alarm, Integer> perioda;

}