-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-07-2026 a las 22:04:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mibase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo_item`
--

CREATE TABLE `catalogo_item` (
  `ItemId` int(11) NOT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `catalogo_item`
--

INSERT INTO `catalogo_item` (`ItemId`, `Nombre`, `Descripcion`) VALUES
(1, 'Calentando Motores', 'Completa el tutoria'),
(2, 'Gajes del Oficio', 'Muere por primera vez'),
(3, 'Quinta Marcha', 'Pasa el nivel 5'),
(4, '100%', 'completa todo el juego'),
(5, 'Primer Tramo', 'Termina el primer nivel'),
(6, 'Forjando la Paciencia', 'Muere 10 veces en el juego');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `InventarioId` int(11) NOT NULL,
  `UsuarioId` int(11) NOT NULL,
  `ItemId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`InventarioId`, `UsuarioId`, `ItemId`) VALUES
(1, 2, 1),
(2, 2, 2),
(3, 2, 5),
(4, 2, 4),
(5, 5, 1),
(6, 5, 5),
(7, 5, 2),
(8, 5, 3),
(9, 5, 6),
(10, 5, 4),
(11, 7, 1),
(12, 7, 2),
(13, 7, 5),
(14, 7, 3),
(15, 7, 6),
(16, 7, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partida_guardada`
--

CREATE TABLE `partida_guardada` (
  `PartidaId` int(11) NOT NULL,
  `UsuarioId` int(11) NOT NULL,
  `NivelActual` int(11) NOT NULL,
  `Tiempo` float NOT NULL,
  `Muertes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `partida_guardada`
--

INSERT INTO `partida_guardada` (`PartidaId`, `UsuarioId`, `NivelActual`, `Tiempo`, `Muertes`) VALUES
(2, 1, 1, 25.2888, 0),
(6, 7, 0, 8.49515, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE `ranking` (
  `UsuarioId` int(11) NOT NULL,
  `TiempoMax` float NOT NULL,
  `Fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ranking`
--

INSERT INTO `ranking` (`UsuarioId`, `TiempoMax`, `Fecha`) VALUES
(5, 692.17, '2026-07-02'),
(7, 601.651, '2026-07-03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `UsuarioId` int(11) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `muertes` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`UsuarioId`, `Username`, `Password`, `muertes`) VALUES
(1, 'Pepe', '1234', 0),
(2, 'messi', '12345', 0),
(5, 'pepe', 'abc', 0),
(7, 'Lucas', 'abc', 0);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `catalogo_item`
--
ALTER TABLE `catalogo_item`
  ADD PRIMARY KEY (`ItemId`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`InventarioId`),
  ADD KEY `Fk_Inventario_Usuario` (`UsuarioId`),
  ADD KEY `Fk_Inventario_Item` (`ItemId`);

--
-- Indices de la tabla `partida_guardada`
--
ALTER TABLE `partida_guardada`
  ADD PRIMARY KEY (`PartidaId`),
  ADD KEY `Fk_Partida_Usuario` (`UsuarioId`);

--
-- Indices de la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD PRIMARY KEY (`UsuarioId`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`UsuarioId`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `catalogo_item`
--
ALTER TABLE `catalogo_item`
  MODIFY `ItemId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `InventarioId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `partida_guardada`
--
ALTER TABLE `partida_guardada`
  MODIFY `PartidaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `UsuarioId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `Fk_Inventario_Item` FOREIGN KEY (`ItemId`) REFERENCES `catalogo_item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Fk_Inventario_Usuario` FOREIGN KEY (`UsuarioId`) REFERENCES `usuarios` (`UsuarioId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `partida_guardada`
--
ALTER TABLE `partida_guardada`
  ADD CONSTRAINT `Fk_Partida_Usuario` FOREIGN KEY (`UsuarioId`) REFERENCES `usuarios` (`UsuarioId`);

--
-- Filtros para la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD CONSTRAINT `FK_Ranking_Usuario` FOREIGN KEY (`UsuarioId`) REFERENCES `usuarios` (`UsuarioId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
