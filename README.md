# challenge-pinapp

Desafío a resolver de microservicios:

1. Microservicio desarrollado en JAVA Spring Boot
2. API Rest documentada en Swagger
3. Deployado en AWS o algún Cloud + código subido en Github endpoint de:
- Entrada `POST /creacliente`
  - Nombre
  - Apellido
  - Edad
  - Fecha de nacimiento
- Endpoint de salida `GET /kpideclientes`
  - Promedio edad entre todos los clientes
  - Desviación estándar entre las edades de todos los clientes
- Endpoint de salida `GET /listclientes`
  - Lista de personas con todos los datos + fecha probable de muerte de cada una