package com.myogui.restaurants.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String descripcion;
    @JsonProperty("hora_apertura")
    private String horaApertura;
    @JsonProperty("hora_cierre")
    private String horaCierre;
    private String telefono;
    private String Ubicacion;
    @JsonProperty("fecha_creacion")
    private Date fechaCreacion = Calendar.getInstance().getTime();
//    @ManyToOne(cascade = CascadeType.ALL)
//    private City city;

}
