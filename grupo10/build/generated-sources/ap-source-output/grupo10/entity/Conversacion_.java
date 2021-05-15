package grupo10.entity;

import grupo10.entity.Evento;
import grupo10.entity.Mensaje;
import grupo10.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-15T13:39:53")
@StaticMetamodel(Conversacion.class)
public class Conversacion_ { 

    public static volatile SingularAttribute<Conversacion, Evento> idevento;
    public static volatile ListAttribute<Conversacion, Mensaje> mensajeList;
    public static volatile SingularAttribute<Conversacion, Integer> id;
    public static volatile SingularAttribute<Conversacion, Usuario> idteleoperador;
    public static volatile SingularAttribute<Conversacion, Usuario> idusuario;

}