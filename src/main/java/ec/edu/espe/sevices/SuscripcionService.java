package ec.edu.espe.sevices;

import java.util.List;
import ec.edu.espe.model.Suscripcion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface SuscripcionService {

    @WebMethod
    Suscripcion suscribir(int usuarioId, int cursoId);

    @WebMethod
    void cancelarSuscripcion(int usuarioId, int cursoId);

    @WebMethod
    Suscripcion consultarSuscripcion(int usuarioId, int cursoId);

    @WebMethod
    List<Suscripcion> listarSuscripciones();
}
