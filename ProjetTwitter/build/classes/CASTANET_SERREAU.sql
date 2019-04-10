-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 28 Février 2019 à 19:31
-- Version du serveur :  5.7.23
-- Version de PHP :  7.0.33-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `CASTANET_SERREAU`
--

-- --------------------------------------------------------

--
-- Structure de la table `Sessions`
--

CREATE TABLE `Sessions` (
  `user_id` int(11) NOT NULL,
  `session_key` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `user_id` int(11) NOT NULL,
  `user_login` varchar(32) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_prenom` varchar(255) NOT NULL,
  `user_nom` varchar(255) NOT NULL,
  `user_sexe` varchar(32) NOT NULL,
  `user_age` int(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`user_id`, `user_login`, `user_password`, `user_prenom`, `user_nom`, `user_sexe`, `user_age`) VALUES
(1, 'PAUL', 'PETITZIZI', 'JAIPA', 'DAMI', '2CM', 10),
(3, 'moi', 'moi', 'moi', 'moi', 'moi', 10);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Sessions`
--
ALTER TABLE `Sessions`
  ADD PRIMARY KEY (`user_id`,`session_key`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_login` (`user_login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `User`
--
ALTER TABLE `User`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
