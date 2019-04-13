# Proyecto final Lenguajes


### Lineamientos:


- Interfaz para indicar cuantos productores o consumidores van a generar un start
- Campo en donde puedes ingresar un número que indica número de consumidores y productores
- Mínimo uno de cada uno
- Máximo 9 de cada uno
- Definir cuanto tiempo va a durar el productor o el consumidor, milisegundos, valor mínimo 0 y máximo max-long
- Buffer de tareas
- Buffer tamaño de 1 a 100
- Productores producen operaciones de scheme (* + - /) binaria (e.g. (* 3 4), (- 0 9) ), donde los números van del 0 al 9
- Consumidor da el resultado de esa operación
- En caso de que sea división entre 0, el consumidor evalúa a error (NO TRONAR EL PROCESO OSCAR)
- Tiene un botón `Start`
- Debe haber manera de hacer `Stop`, ya no se puede reanudar.
- En caso de hacer start nuevamente, limpiar los datos
- En caso de cerrar ventana, matar los hilos.


### Cómo se muestra (Interfaz):


- Dos tablas, uno de consumidor, otro de productor.
- Productor {{id}} produjo {{product}}
- Consumidor {{id}} consumió {{product}} resultado {{result}}
- Para el productor: *Mostrar abajo una barra como progreso de qué tan lleno está el buffer*
- Para el consumidor: *Mostrar las tareas realizadas*

**Validar las 5 entradas a prueba de errores**


