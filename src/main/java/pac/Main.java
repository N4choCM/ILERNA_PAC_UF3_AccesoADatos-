package pac;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Configuration cfg = new Configuration().configure();

        SessionFactory sessionFactory = cfg.buildSessionFactory(new StandardServiceRegistryBuilder()
                        .configure().build());
        Session session = sessionFactory.openSession();

        /*
         * Añadimos la informacion de los modulos para poder añadirlos posteriormente a un set de modulos.
         */
        Modulo m03b = new Modulo();
        m03b.setNombre("Programacion B");
        m03b.setCodigo("M03B");

        Modulo m06 = new Modulo();
        m06.setNombre("Acceso a Datos");
        m06.setCodigo("M06");

        Modulo m08 = new Modulo();
        m08.setNombre("Desarrollo de aplicaciones moviles");
        m08.setCodigo("M08");

        Modulo m09 = new Modulo();
        m09.setNombre("Servicios y procesos");
        m09.setCodigo("M09");


        // Insertamos los módulos
        insertModulo(m03b, session);
        insertModulo(m06, session);
        insertModulo(m08, session);
        insertModulo(m09, session);

        // Insertamos al profesor.
        insertProfesor("Alvaro", "Hombre", session);

        // Añadimos los módulos de cada alumno a sus respectivos sets.
        Set<Modulo> modulosJuan = new HashSet<>();
        modulosJuan.add(m03b);
        modulosJuan.add(m06);
        modulosJuan.add(m08);
        modulosJuan.add(m09);

        Set<Modulo> modulosPedro = new HashSet<>();
        modulosJuan.add(m03b);
        modulosJuan.add(m06);
        modulosJuan.add(m09);

        Set<Modulo> modulosMarta = new HashSet<>();
        modulosJuan.add(m08);
        modulosJuan.add(m09);

        Set<Modulo> modulosCarla = new HashSet<>();
        modulosJuan.add(m06);
        modulosJuan.add(m08);
        modulosJuan.add(m09);

        //Insertamos a los alumnos.
        insertAlumno("Juan", "Espaniola", 26, "Hombre", modulosJuan, session);
        insertAlumno("Pedro", "Andorrana", 21, "Hombre", modulosPedro, session);
        insertAlumno("Marta", "Espaniola", 19, "Mujer", modulosMarta, session);
        insertAlumno("Carla", "Francesa", 35, "Mujer", modulosCarla, session);


        session.close();
        sessionFactory.close();
    }

    /**
     * Procedimiento para insertar un modulo en la BD.
     * @param modulo El modulo que queremos insertar.
     * @param session Una instancia de la sesion actual para poder mostrar un mensaje en consola DESPUÉS de guardar el
     *                modulo.
     * @author Juan Ignacio Campos Marti
     */
    static void insertModulo (Modulo modulo, Session session){
        session.beginTransaction();
        session.save(modulo);
        session.getTransaction().commit();
        System.out.println("Insert into modulo, nombre: " + modulo.getNombre() + ", codigo: " + modulo.getCodigo());
    }

    /**
     * Procedimiento para insertar un profesor en la BD.
     * @param nombre Nombre del profesor.
     * @param sexo Sexo del profesor.
     * @param session Una instancia de la sesion actual para poder mostrar un mensaje en consola DESPUÉS de guardar el
     *      profesor.
     * @author Juan Ignacio Campos Marti
     */
    static void insertProfesor (String nombre, String sexo, Session session){
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setSexo(sexo);
        session.beginTransaction();
        session.save(profesor);
        session.getTransaction().commit();
        System.out.println("Insert into profesor, nombre: " + nombre + ", sexo: " + sexo);
    }

    /**
     * Procedimiento para insertar un alumno en la BD.
     * @param nombre Nombre del alumno.
     * @param nacionalidad Nacionalidad del alumno.
     * @param edad Edad del alumno.
     * @param sexo Sexo del alumno.
     * @param modulos Cantidad de modulos en los que esta matriculado el alumno.
     * @param session Una instancia de la sesion actual para poder mostrar un mensaje en consola DESPUÉS de guardar el
     *      profesor.
     * @author Juan Ignacio Campos Marti
     */
    static void insertAlumno (String nombre, String nacionalidad, int edad, String sexo, Set <Modulo> modulos ,
                              Session session){
        Alumno alumno = new Alumno();
        alumno.setNombre(nombre);
        alumno.setNacionalidad(nacionalidad);
        alumno.setEdad(edad);
        alumno.setSexo(sexo);
        alumno.setModulos(modulos);
        session.beginTransaction();
        session.save(alumno);
        session.getTransaction().commit();
        System.out.println("Insert into alumno, nombre: " + nombre +  ", nacionalidad: " + nacionalidad +
                ", edad: " + edad + ", sexo: " + sexo + ", modulos: " + modulos.size());
    }
}
