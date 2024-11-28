package ec.edu.espe.sevices;

import java.util.List;
import ec.edu.espe.model.Curso;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface CursoService {

    @WebMethod
    Curso crearCurso(Curso curso);

    @WebMethod
    Curso modificarCurso(int id, Curso curso);

    @WebMethod
    void eliminarCurso(int id);

    @WebMethod
    Curso obtenerCursoPorId(int id);

    @WebMethod
    List<Curso> listarCursos();
    
    @WebMethod
    void cambiarEstadoCurso(int id, String nuevoEstado);
}
