package ec.edu.espe.sevices;

import java.util.List;
import ec.edu.espe.model.Usuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface UsuarioService {

    @WebMethod
    Usuario crearUsuario(Usuario usuario);

    @WebMethod
    Usuario modificarUsuario(int id, Usuario usuario);

    @WebMethod
    void eliminarUsuario(int id);

    @WebMethod
    Usuario obtenerUsuarioPorId(int id);

    @WebMethod
    List<Usuario> listarUsuarios();
}
