package grupo10.entity;

import grupo10.entity.Evento;
import grupo10.entity.InscripcionPK;
import grupo10.entity.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-05-15T13:39:53")
@StaticMetamodel(Inscripcion.class)
public class Inscripcion_ { 

    public static volatile SingularAttribute<Inscripcion, Evento> evento;
    public static volatile SingularAttribute<Inscripcion, InscripcionPK> inscripcionPK;
    public static volatile SingularAttribute<Inscripcion, Usuario> usuario;

}