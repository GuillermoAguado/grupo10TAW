package grupo10.entity;

import grupo10.entity.Conversacion;
import grupo10.entity.Etiquetas;
import grupo10.entity.Inscripcion;
import grupo10.entity.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-15T13:39:53")
@StaticMetamodel(Evento.class)
public class Evento_ { 

    public static volatile SingularAttribute<Evento, String> descripcion;
    public static volatile SingularAttribute<Evento, Integer> aforo;
    public static volatile ListAttribute<Evento, Conversacion> conversacionList;
    public static volatile ListAttribute<Evento, Inscripcion> inscripcionList;
    public static volatile ListAttribute<Evento, Etiquetas> etiquetasList;
    public static volatile SingularAttribute<Evento, String> nombre;
    public static volatile SingularAttribute<Evento, Integer> nfilas;
    public static volatile SingularAttribute<Evento, Usuario> idcreador;
    public static volatile SingularAttribute<Evento, Double> precioentrada;
    public static volatile SingularAttribute<Evento, Integer> maximoentradasusuario;
    public static volatile SingularAttribute<Evento, Integer> id;
    public static volatile SingularAttribute<Evento, Date> fechalimiteentradas;
    public static volatile SingularAttribute<Evento, Date> fechaevento;
    public static volatile SingularAttribute<Evento, Integer> asientosfila;

}