package ec.edu.espe.server;

import ec.edu.espe.services.impl.CursoServiceImpl;
import ec.edu.espe.services.impl.UsuarioServiceImpl;
import ec.edu.espe.services.impl.SuscripcionServiceImpl;
import jakarta.xml.ws.Endpoint;

public class ServerWS {

    public static void main(String[] args) {
        String urlCurso = "http://0.0.0.0:8088/curso";
        String urlUsuario = "http://0.0.0.0:8088/usuario";
        String urlSuscripcion = "http://0.0.0.0:8088/suscripcion";

        Endpoint.publish(urlCurso, new CursoServiceImpl());

        Endpoint.publish(urlUsuario, new UsuarioServiceImpl());
        Endpoint.publish(urlSuscripcion, new SuscripcionServiceImpl());
        System.out.println("Server running on: " + urlCurso + ", " + urlUsuario + " and " + urlSuscripcion);
    }
}
