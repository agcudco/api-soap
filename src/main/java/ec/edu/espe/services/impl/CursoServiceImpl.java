package ec.edu.espe.services.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ec.edu.espe.model.Curso;
import ec.edu.espe.sevices.CursoService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "ec.edu.espe.sevices.CursoService")
public class CursoServiceImpl implements CursoService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public Curso crearCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
        return curso;
    }

    @Override
    public Curso modificarCurso(int id, Curso curso) {
        EntityManager em = emf.createEntityManager();
        Curso cursoExistente = em.find(Curso.class, id);
        if (cursoExistente != null) {
            em.getTransaction().begin();
            cursoExistente.setNombre(curso.getNombre());
            cursoExistente.setDescripcion(curso.getDescripcion());
            cursoExistente.setEstado(curso.getEstado());
            cursoExistente.setCreador(curso.getCreador());
            em.getTransaction().commit();
        }
        em.close();
        return cursoExistente;
    }

    @Override
    public void eliminarCurso(int id) {
        EntityManager em = emf.createEntityManager();
        Curso curso = em.find(Curso.class, id);
        if (curso != null) {
            em.getTransaction().begin();
            em.remove(curso);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Curso obtenerCursoPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Curso curso = em.find(Curso.class, id);
        em.close();
        return curso;
    }

    @Override
    public List<Curso> listarCursos() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
        List<Curso> cursos = query.getResultList();
        em.close();
        return cursos;
    }

    @Override
    public void cambiarEstadoCurso(int id, String nuevoEstado) {
        EntityManager em = emf.createEntityManager();
        Curso curso = em.find(Curso.class, id);
        if (curso != null) {
            em.getTransaction().begin();
            curso.setEstado(nuevoEstado);
            em.getTransaction().commit();
        }
        em.close();
    }
}
