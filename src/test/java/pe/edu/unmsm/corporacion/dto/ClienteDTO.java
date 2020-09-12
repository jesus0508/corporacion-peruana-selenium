package pe.edu.unmsm.corporacion.dto;

import java.util.ArrayList;

public class ClienteDTO {

    public String ruc;
    public String razonSocial;
    public String representante;
    public String cargo;
    public String dni;
    public String telefono;
    public String correo;
    public String actividadEconomica;
    public String lineaCredito;
    public String distrito;
    public String direccion;
    public String precioGalon;
    public String formaDePago;
    public String personaComision;
    public String correoRepresentante;

    public ClienteDTO(String ruc, String razonSocial, String representante, String cargo, String dni, String telefono,
                      String correo, String actividadEconomica, String lineaCredito, String distrito, String direccion,
                      String precioGalon, String formaDePago, String personaComision, String correoRepresentante) {
        this.ruc = ruc;
        this.razonSocial = razonSocial;
        this.representante = representante;
        this.cargo = cargo;
        this.dni = dni;
        this.telefono = telefono;
        this.correo = correo;
        this.actividadEconomica = actividadEconomica;
        this.lineaCredito = lineaCredito;
        this.distrito = distrito;
        this.direccion = direccion;
        this.precioGalon = precioGalon;
        this.formaDePago = formaDePago;
        this.personaComision = personaComision;
        this.correoRepresentante = correoRepresentante;
    }

    public ClienteDTO(ArrayList<String> clientData) {
        this.ruc = clientData.get(0);
        this.razonSocial = clientData.get(1);
        this.representante = clientData.get(2);
        this.cargo = clientData.get(3);
        this.dni = clientData.get(4);
        this.telefono = clientData.get(5);
        this.correo = clientData.get(6);
        this.actividadEconomica = clientData.get(7);
        this.lineaCredito = clientData.get(8);
        this.distrito = clientData.get(9);
        this.direccion = clientData.get(10);
        this.precioGalon = clientData.get(11);
        this.formaDePago = clientData.get(12);
        this.personaComision = clientData.get(13);
        this.correoRepresentante = clientData.get(14);
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "ruc='" + ruc + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", representante='" + representante + '\'' +
                ", cargo='" + cargo + '\'' +
                ", dni='" + dni + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", actividadEconomica='" + actividadEconomica + '\'' +
                ", lineaCredito='" + lineaCredito + '\'' +
                ", distrito='" + distrito + '\'' +
                ", direccion='" + direccion + '\'' +
                ", precioGalon='" + precioGalon + '\'' +
                ", formaDePago='" + formaDePago + '\'' +
                ", personaComision='" + personaComision + '\'' +
                ", correoRepresentante='" + correoRepresentante + '\'' +
                '}';
    }
}

