package electronicapractica10.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
public class Renta implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Date fecha;

    private Date fechaEntrega;

    @OneToOne
    private Cliente cliente;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<EquipoRenta> equipoRenta;

    private Boolean pendiente;

    private int cantidadEquipos;

    private int costo;

    public Renta() {
    }

    public Renta(Date fecha, Date fechaEntrega, Cliente cliente, Set<EquipoRenta> equipoRenta, Boolean pendiente, int cantidadEquipos, int costo) {
        this.fecha = fecha;
        this.fechaEntrega = fechaEntrega;
        this.cliente = cliente;
        this.equipoRenta = equipoRenta;
        this.pendiente = pendiente;
        this.cantidadEquipos = cantidadEquipos;
        this.costo = costo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<EquipoRenta> getEquipoRenta() {
        return equipoRenta;
    }

    public void setEquipoRenta(Set<EquipoRenta> equipoRenta) {
        this.equipoRenta = equipoRenta;
    }

    public Boolean getPendiente() {
        return pendiente;
    }

    public void setPendiente(Boolean pendiente) {
        this.pendiente = pendiente;
    }

    public int getCantidadEquipos() {
        return cantidadEquipos;
    }

    public void setCantidadEquipos(int cantidadEquipos) {
        this.cantidadEquipos = cantidadEquipos;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
}
