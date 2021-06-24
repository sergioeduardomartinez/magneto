# magneto

# Título del Proyecto

Magneto API que permite validar si la secuencia de adn ingresada es de un mutante o de un humano

Dominio POST AWS

http://ec2-18-118-173-59.us-east-2.compute.amazonaws.com:8080//mutant/

Dominio GET AWS

http://ec2-18-118-173-59.us-east-2.compute.amazonaws.com:8080/stats/

### Pre-requisitos 📋


```
1. JDK 11
2. Maven 3.6.1
3. Mysql
4. SpringBoot
```

### Instalación 🔧


1. Clonar https://github.com/sergioeduardomartinez/magneto.git
2. 2. Ejecutar Maven clean install
3. Iniciar el servidor default de SpringBoot
4. Ejecutar archivo dBMagneto.sql , desde la base de datos Mysql crear schema magneto
    o simplemente conectase a la siguiente bd expuesta en aws:
    jdbc:mysql://database-1.c2gpobj0bqfh.us-east-2.rds.amazonaws.com:3306/magneto
    spring.datasource.username=admin
    spring.datasource.password=pruebaadmin


## Evidencias

Documento evidencias eviendenciasAPiMageno.docx
