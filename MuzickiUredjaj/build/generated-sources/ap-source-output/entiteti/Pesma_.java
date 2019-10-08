package entiteti;

import entiteti.Alarm;
import entiteti.Slusao;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-12T14:48:48")
@StaticMetamodel(Pesma.class)
public class Pesma_ { 

    public static volatile CollectionAttribute<Pesma, Slusao> slusaoCollection;
    public static volatile CollectionAttribute<Pesma, Alarm> alarmCollection;
    public static volatile SingularAttribute<Pesma, Integer> pesmaId;
    public static volatile SingularAttribute<Pesma, String> naziv;

}