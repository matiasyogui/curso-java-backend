package com.coderhouse.controller;

import com.coderhouse.model.Restaurant;
import com.coderhouse.service.RestaurantService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;
    private final ObjectMapper mapper;

    @Operation(summary = "Metodo para crear un restaurante.", description = "Permite crear a un restaurante.", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se creo correctamente al restaurante"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @PostMapping("/")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        log.info("POST crear mensaje");
        return service.create(restaurant);
    }

    @Operation(summary = "Metodo para obtener un restaurante.", description = "Permite obtener a un restaurante a traves de un id.", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se obtuvo correctamente al restaurante"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        log.info("GET obtener mensaje por el id " + id);
        return service.getRestaurantById(id);
    }

    @Operation(summary = "Metodo para actualizar un restaurante.", description = "Permite actualizar a un restaurante a traves de un id. (Este metodo elimina el restaurante previo y lo crea de nuevo (por esta razon el id sera nuevo.))", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se actualizo correctamente al restaurante"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @PatchMapping("/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id) {
        log.info("UPDATE de restaurante " + newRestaurant.getId());
        return service.update(newRestaurant, id);
    }

    @Operation(summary = "Metodo para eliminar un restaurante.", description = "Permite eliminar a un restaurante a traves de un id.", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se elimino correctamente al restaurante"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        log.info("DELETE del restaurante " + id);
        service.deleteById(id);
    }

    @Operation(summary = "Metodo para devolver un restaurante en formato String.", description = "Permite obtener a un restaurante que es devuelto en formato String. (Aunque el metodo sea POST no crea un restaunte)", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se devolvio correctamente al restaurante"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @PostMapping("/serializado")
    public String createRestaurantSerializado(@RequestBody Restaurant restaurant) {
        return service.createRestaurantSerializado(restaurant);
    }

    @Operation(summary = "Metodo para devolver un restaurante mappeado.", description = "Permite obtener un restaurante que es devuelto en formato Map. (Aunque el metodo sea POST no crea un restaurante)", tags = {"restaurant"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Se devolvio correctamente al restaurante en formato Map"),
                    @ApiResponse(responseCode = "400", description = "Hay un error en el request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Ocurrio un error inesperado", content = @Content)
            }
    )
    @PostMapping("/mappeado")
    public String createRestaurantMappeado(@RequestBody String restaurant) {
        return service.createRestaurantMap(restaurant);
    }
}
