package pe.edu.unmsm.corporacion.dto;

import java.util.ArrayList;

public class ProveedorDTO {
    String razonSocial;
    String ruc;
    String email;
    String lineaCredito;
    String sobregiro;

    public ProveedorDTO(ArrayList<String> proveedorData) {
        this.razonSocial = proveedorData.get(0);
        this.ruc = proveedorData.get(1);
        this.email = proveedorData.get(2);
        this.lineaCredito = proveedorData.get(3);
        this.sobregiro = proveedorData.get(4);
    }
}
