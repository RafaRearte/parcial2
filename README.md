[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/oJHxhyvb)
# API REST de Reserva de Habitaciones de Hotel

## Contexto del Negocio:

Imagine que está trabajando para un exclusivo hotel llamado "Grand Paradise Hotel & Spa".
Este hotel ofrece tres tipos de habitaciones: Simple, Doble y Triple.
La administración del hotel desea implementar un sistema que permita a los clientes verificar
la disponibilidad de habitaciones en una fecha determinada y realizar reservas de manera eficiente.

## Enpoint a diseñar

### Crear reserva

- Endpoint: ```/reserva```
- Método: ```POST```
- Request:
  ```
  {
    "id_hotel": 123,
    "nombre_cliente": "Jose Perez",
    "id_hotel": 123,
    "tipo_habitacion": "SIMPLE",
    "fecha_ingreso": "2024-12-25",
    "fecha_salida": "2024-12-28",
    "medio_pago": "EFECTIVO"
  }
- Respuesta:
  ```
  {
    "id_reserva": 12,
    "nombre_cliente": "Jose Perez",
    "id_hotel": 123,
    "tipo_habitacion": "SIMPLE",
    "fecha_ingreso": "2024-12-25",
    "fecha_salida": "2024-12-28",
    "estado_reserva": "EXITOSA",
    "precio": 1250.33,
    "medio_pago": "EFECTIVO"
  }
  ```

### Recuperar información de la reserva

- Endpoint: ```/reserva/{id_reserva}```
- Método: ```GET```
- Respuesta:
  ```
  {
    "id_reserva": 12,
    "nombre_cliente": "Jose Perez",
    "id_hotel": 123,
    "tipo_habitacion": "SIMPLE",
    "fecha_ingreso": "2024-12-25",
    "fecha_salida": "2024-12-28",
    "estado_reserva": "EXITOSA",
    "precio": 1250.33,
    "medio_pago": "EFECTIVO"
  }
  ```

## Reglas del Negocio:

Para llevar a cabo la gestión de reservas de habitaciones de hotel de manera efectiva,
su aplicación deberá cumplir con las siguientes reglas de negocio

### Disponibilidad de Habitaciones:

Se deberá consultar la disponibilidad de todos los días de la estadía a la tabla de reservas

### Precio de habitación

El precio de lista de las habitaciones será el siguiente:

- hotel_id: 1
    - Simple (SIMPLE): $1250
    - Doble (DOBLE): $2100
    - Triple (TRIPLE): $2850
- hotel_id: 2
    - Simple (SIMPLE): $370
    - Doble (DOBLE): $650
    - Triple (TRIPLE): $875
- hotel_id: 3
    - Simple (SIMPLE): $2200
    - Doble (DOBLE): $3700
    - Triple (TRIPLE): $4100


### Variación de Precios según Temporada:

- Temporada Alta (enero, febrero, julio, agosto): +30% al precio de lista.
- Temporada Baja (marzo, abril, octubre, noviembre): -10% al precio de lista.
- Temporada Media (mayo, junio, septiembre, diciembre): Precio de lista.

Si una estadia forma parte de mas de una temporada se toma el mayor precio para el calculo de toda la estadía

### Descuentos según Medio de Pago:

- En efectivo (EFECTIVO): 25% de descuento sobre el precio segun temporada.
- En débito (TARJETA_DEBITO): 10% de descuento sobre el precio segun temporada.
- en credito (TARJETA_CREDITO) no se realiza descuento


### Almacenamiento de la reserva

Si las validaciones son exitosas y luego de hacer el calculo final del precio
debe almacenarse en una BD la información de la reserva.

### Manejo de Errores:

- Se deberá validar las entradas recibidas (no se pueden fechas pasadas, el intervalo debe ser correcto,
  el tipo de habitación valido, etc.) y en caso de que se envie un dato incorrecto debe devolver status code 400

### Testing

- Es obligatorio la creación de testing unitario sobre la lógica de negocio de todo el proyecto

## Puntajes

- Validación de parametros **(10 pts)**
- Validar disponibilidad **(10 pts)**
- Calculo de precio **(30 pts)**
- Guardado de reserva **(10 pts)**
- Buscar reserva **(10 pts)**
- Testing **(30 pts)**