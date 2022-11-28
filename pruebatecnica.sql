-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2022 a las 23:22:41
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `movieseries`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `gender`
--

CREATE TABLE `gender` (
  `id` bigint(20) NOT NULL,
  `description` varchar(450) DEFAULT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `gender`
--

INSERT INTO `gender` (`id`, `description`, `name`) VALUES
(null, 'En este género prevalecen altas dosis de adrenalina con una buena carga de movimiento, fugas, acrobacias, peleas, guerras, persecuciones y una lucha contra el mal.', 'Acción'),
(null, 'Similares a las de acción, predominan las nuevas experiencias y situaciones.', 'Aventuras'),
(null, 'Basados en fenómenos imaginarios, en la ciencia ficción son usuales los extraterrestres, sociedades inventadas, otros planetas…', 'Ciencia Ficción'),
(null, 'Diseñadas específicamente para provocar la risa o la alegría entre los espectadores.', 'Comedia'),
(null, 'Este género analiza un hecho o situación real sin ficcionarlo.', 'No- Ficción / documental'),
(null, 'Los dramas se centran en desarrollar el problema o problemas entre los diferentes protagonistas. Este es quizás uno de los géneros cinematográficos más amplios de la lista. No predominan las aventuras o la acción, aunque pueden aparecer puntualmente Generalmente se basan en desarrollar la interacción y caracteres humanos.', 'Drama'),
(null, 'En ellas se incluyen personajes irreales o totalmente inventados, inexistentes en nuestra realidad. También podemos conocer este género de cine como “fantástico”. No se basa en ideas que puedan llegar a materializarse.', 'Fantasía'),
(null, 'Las películas que cortan su desarrollo natural con fragmentos musicales son protagonistas de este género.', 'Musical'),
(null, 'Conocido también como intriga, estas películas se desarrollan rápidamente, y todos sus elementos giran entorno un mismo elemento intrigante.', 'Suspenso'),
(null, 'Su principal objetivo es causar miedo, horror, incomodidad o preocupación.', 'ATerror');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `type`
--

CREATE TABLE `type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `type`
--

INSERT INTO `type` (`id`, `name`) VALUES
(null, 'Pelicula'),
(null, 'Serie');

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movieseries`
--

CREATE TABLE `movieseries` (
  `id` bigint(20) NOT NULL,
  `name` varchar(150) DEFAULT NULL,
  `numberviews` int(11) DEFAULT 0,
  `score` decimal(3,2) DEFAULT 0.00,
  `gender` bigint(20) NOT NULL,
  `type` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `movieseries`
--

INSERT INTO `movieseries` (`id`, `name`, `numberviews`, `score`, `gender`, `type`) VALUES
(null, 'Dune', 3, '2.33', 1, 1),
(null, 'Mad Max: Furia en la carretera', 3, '2.00', 2, 1),
(null, 'Blade Runner 2049', 3, '3.50', 1, 1),
(null, 'El hoyo', 2, '2.50', 10, 1),
(null, 'Interstellar', 2, '3.50', 3, 1),
(null, 'Serenity', 3, '2.50', 3, 1),
(null, 'The Mentalist', 3, '5.00', 3, 2);

--
-- Estructura de tabla para la tabla `usermovieseries`
--

CREATE TABLE `usermovieseries` (
  `id` bigint(20) NOT NULL,
  `score` double DEFAULT 0,
  `view` tinyint(1) DEFAULT 0,
  `movieserie` bigint(20) NOT NULL,
  `user` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `password` varchar(10) NOT NULL,
  `session` varchar(250) DEFAULT NULL,
  `username` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `password`, `session`, `username`) VALUES
(null, 'Andres Felipe', 'Ramirez', 'andres', null, 'andres'),
(null, 'Juan Alberto', 'Velasquez', 'andres', null, 'alberto'),
(null, 'Luis Guillermo', 'Restrepo', 'andres', null, 'restrepo'),
(null, 'Jhonnatan', 'Herron', 'andres', null, 'herron');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `gender`
--
ALTER TABLE `gender`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_jdyxf8c9k9656frmqjb67cgo3` (`name`);

--
-- Indices de la tabla `movieseries`
--
ALTER TABLE `movieseries`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKoc7h57wdj19vhxcxuxvvjcmnm` (`gender`),
  ADD KEY `FKmyqn6guqd043qe4oyd88e5q62` (`type`);

--
-- Indices de la tabla `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3tg65hx29l2ser69ddfwvhy4h` (`name`);

--
-- Indices de la tabla `usermovieseries`
--
ALTER TABLE `usermovieseries`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uqUserMovieSerie` (`user`,`movieserie`),
  ADD KEY `FK7qjggvh8mvd7rpw46433cytrh` (`movieserie`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `gender`
--
ALTER TABLE `gender`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `movieseries`
--
ALTER TABLE `movieseries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `type`
--
ALTER TABLE `type`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usermovieseries`
--
ALTER TABLE `usermovieseries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `movieseries`
--
ALTER TABLE `movieseries`
  ADD CONSTRAINT `FKmyqn6guqd043qe4oyd88e5q62` FOREIGN KEY (`type`) REFERENCES `type` (`id`),
  ADD CONSTRAINT `FKoc7h57wdj19vhxcxuxvvjcmnm` FOREIGN KEY (`gender`) REFERENCES `gender` (`id`);

--
-- Filtros para la tabla `usermovieseries`
--
ALTER TABLE `usermovieseries`
  ADD CONSTRAINT `FK7qjggvh8mvd7rpw46433cytrh` FOREIGN KEY (`movieserie`) REFERENCES `movieseries` (`id`),
  ADD CONSTRAINT `FKgcpamstwe23lxaxcnteus3m0a` FOREIGN KEY (`user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
