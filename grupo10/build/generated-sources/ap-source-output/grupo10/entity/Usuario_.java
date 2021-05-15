package grupo10.entity;

import grupo10.entity.Conversacion;
import grupo10.entity.Estudiobd;
import grupo10.entity.Evento;
import grupo10.entity.Inscripcion;
import grupo10.entity.Mensaje;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-15T13:39:53")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidos;
    public static volatile ListAttribute<Usuario, Estudiobd> estudiobdList;
    public static volatile ListAttribute<Usuario, Conversacion> conversacionList;
    public static volatile ListAttribute<Usuario, Inscripcion> inscripcionList;
    public static volatile ListAttribute<Usuario, Mensaje> mensajeList;
    public static volatile ListAttribute<Usuario, Evento> eventoList;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, Integer> edad;
    public static volatile SingularAttribute<Usuario, Integer> tipousuario;
    public static volatile SingularAttribute<Usuario, String> domicilio;
    public static volatile ListAttribute<Usuario, Conversacion> conversacionList1;
    public static volatile SingularAttribute<Usuario, String> contrasenia;
    public static volatile SingularAttribute<Usuario, Integer> id;
    public static volatile ListAttribute<Usuario, Estudiobd> estudiobdList1;
    public static volatile SingularAttribute<Usuario, String> sexo;
    public static volatile SingularAttribute<Usuario, String> ciudadRes;
    public static volatile SingularAttribute<Usuario, String> email;

}