package ec.edu.espe.services.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ec.edu.espe.model.Curso;
import ec.edu.espe.model.Suscripcion;
import ec.edu.espe.model.Usuario;
import ec.edu.espe.sevices.SuscripcionService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "ec.edu.espe.sevices.SuscripcionService")
public class SuscripcionServiceImpl implements SuscripcionService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public Suscripcion suscribir(int usuarioId, int cursoId) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, usuarioId);
        Curso curso = em.find(Curso.class, cursoId);
        Suscripcion suscripcion = new Suscripcion(usuario, curso);
        em.getTransaction().begin();
        em.persist(suscripcion);
        em.getTransaction().commit();
        em.close();
        return suscripcion;
    }

    @Override
    public void cancelarSuscripcion(int usuarioId, int cursoId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Suscripcion> query = em.createQuery(
            "SELECT s FROM Suscripcion s WHERE s.usuario.id = :usuarioId AND s.curso.id = :cursoId", Suscripcion.class);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("cursoId", cursoId);
        Suscripcion suscripcion = query.getSingleResult();
        if (suscripcion != null) {
            em.getTransaction().begin();
            em.remove(suscripcion);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Suscripcion consultarSuscripcion(int usuarioId, int cursoId) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Suscripcion> query = em.createQuery(
            "SELECT s FROM Suscripcion s WHERE s.usuario.id = :usuarioId AND s.curso.id = :cursoId", Suscripcion.class);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("cursoId", cursoId);
        Suscripcion suscripcion = query.getSingleResult();
        em.close();
        return suscripcion;
    }

    @Override
    public List<Suscripcion> listarSuscripciones() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Suscripcion> query = em.createQuery("SELECT s FROM Suscripcion s", Suscripcion.class);
        List<Suscripcion> suscripciones = query.getResultList();
        em.close();
        return suscripciones;
    }
}
