package com.smarthome.backend.entity;


import javax.persistence.*;

@Entity
@Table(name = "DISPOSITIVO")
public class Dispositivo {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dispositivo")
    private Integer idDispositivo;
    private String nombre;
    private String tipo;
    private String ubicacion;
    private String estado;
    
    @Column(name = "id_hub")
    private Integer idHub;

    // Getters y Setters
    public Integer getIdDispositivo() { return idDispositivo; }
    public void setIdDispositivo(Integer idDispositivo) { this.idDispositivo = idDispositivo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getIdHub() { return idHub; }
    public void setIdHub(Integer idHub) { this.idHub = idHub; }
}