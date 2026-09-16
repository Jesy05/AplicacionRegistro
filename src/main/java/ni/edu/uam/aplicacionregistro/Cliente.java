package ni.edu.uam.aplicacionregistro;

import java.time.LocalDate;
import java.util.List;

public class Cliente {
    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private String tipoSolicitud;
    private List<String> serviciosInteres;
    private String rutaFoto;

    public Cliente(String nombres, String apellidos, String tipoCliente, String ciudad,
                   LocalDate fechaNacimiento, String tipoSolicitud,
                   List<String> serviciosInteres, String rutaFoto) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoCliente = tipoCliente;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSolicitud = tipoSolicitud;
        this.serviciosInteres = serviciosInteres;
        this.rutaFoto = rutaFoto;
    }

    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public String getNombreCompleto() { return nombres + " " + apellidos; }
    public String getTipoCliente() { return tipoCliente; }
    public String getCiudad() { return ciudad; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public String getTipoSolicitud() { return tipoSolicitud; }
    public List<String> getServiciosInteres() { return serviciosInteres; }
    public String getRutaFoto() { return rutaFoto; }

    @Override
    public String toString() {
        return getNombreCompleto();
    }
}