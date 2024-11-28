package ec.edu.espe.services.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import ec.edu.espe.model.Usuario;
import ec.edu.espe.sevices.UsuarioService;
import jakarta.jws.WebService;

@WebService(endpointInterface = "ec.edu.espe.sevices.UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
        return usuario;
    }

    @Override
    public Usuario modificarUsuario(int id, Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        Usuario usuarioExistente = em.find(Usuario.class, id);
        if (usuarioExistente != null) {
            em.getTransaction().begin();
            usuarioExistente.setNombres(usuario.getNombres());
            usuarioExistente.setApellidos(usuario.getApellidos());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioExistente.setTipoUsuario(usuario.getTipoUsuario());
            em.getTransaction().commit();
        }
        em.close();
        return usuarioExistente;
    }

    @Override
    public void eliminarUsuario(int id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Usuario obtenerUsuarioPorId(int id) {
        EntityManager em = emf.createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        em.close();
        return usuario;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        em.close();
        return usuarios;
    }
}
