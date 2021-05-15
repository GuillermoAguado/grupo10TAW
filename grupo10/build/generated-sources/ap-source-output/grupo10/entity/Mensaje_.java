package grupo10.entity;

import grupo10.entity.Conversacion;
import grupo10.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-15T13:39:53")
@StaticMetamodel(Mensaje.class)
public class Mensaje_ { 

    public static volatile SingularAttribute<Mensaje, Date> fechahora;
    public static volatile SingularAttribute<Mensaje, String> contenido;
    public static volatile SingularAttribute<Mensaje, Conversacion> idconversacion;
    public static volatile SingularAttribute<Mensaje, Usuario> idemisor;

}