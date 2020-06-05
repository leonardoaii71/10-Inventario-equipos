package electronicapractica10.demo.service;

public class ReporteRenta {

    private int cliente;

    private int cantidad;

    public ReporteRenta() {
    }

    public ReporteRenta(int cliente, int cantidad) {
        this.cliente = cliente;
        this.cantidad = cantidad;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
