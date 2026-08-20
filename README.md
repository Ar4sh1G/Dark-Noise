# Dark Noise

Videojuego 2D desarrollado en Java como proyecto universitario, trabajado en equipo de 2 integrantes.

![Gameplay](gameplay1.gif)


![Gameplay2](gameplay2.gif)

## Descripción

Dark Noise es un videojuego 2D de exploración y sigilo, inspirado en Dark Echo, en el que el jugador debe orientarse en un entorno completamente oscuro utilizando ondas de sonido para detectar obstáculos, peligros y el camino a seguir. El objetivo es completar todos los niveles en el menor tiempo posible. Incluye sistema de niveles, cronometraje de partidas y un listado de logros para desbloquear.

## Inspiración

Este proyecto fue desarrollado con fines académicos, tomando como referencia e inspiración el juego Dark Echo. Se reinterpretaron sus mecánicas principales como ejercicio de aprendizaje de programación orientada a objetos, diseño de videojuegos y bases de datos, sin fines comerciales.

## Tecnologías utilizadas

- **Java** — lenguaje principal del proyecto
- **NetBeans IDE** — entorno de desarrollo
- **MySQL** (vía XAMPP) — persistencia de datos (usuarios, puntajes, partidas, etc.)

## Requisitos previos

- [NetBeans IDE](https://netbeans.apache.org/) instalado
- [XAMPP](https://www.apachefriends.org/) instalado, con el servicio de **MySQL** activo
- JDK compatible con el proyecto (Java 21)
- MySQL Connector/J 9.6.0 agregado como librería del proyecto en NetBeans

## Instalación y ejecución

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/Ar4sh1G/Dark-Noise.git
   ```
2. Iniciá **XAMPP** y activá el módulo de **MySQL**.
3. Abrí **phpMyAdmin** (`http://localhost/phpmyadmin`) e importá el archivo `mibase.sql` incluido en este repositorio para crear la base de datos.
4. Abrí el proyecto en **NetBeans** (`File > Open Project`).
5. Ejecutá el proyecto con `Run > Run Project` (o la tecla `F6`).


## Estructura del proyecto

```
Dark-Noise/
├── src/                # Código fuente
├── nbproject/          # Configuración de NetBeans
├── build.xml           # Script de build
├── [archivo].sql       # Script de la base de datos
└── README.md
```

## Autores

[Ar4sh1G](https://github.com/Ar4sh1G)
[Lucasmdqx](https://github.com/Lucasmdqx)
