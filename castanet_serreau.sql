-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Apr 18, 2019 at 11:05 AM
-- Server version: 8.0.15
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `castanet_serreau`
--

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
CREATE TABLE IF NOT EXISTS `follows` (
  `my_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL,
  PRIMARY KEY (`my_id`,`friend_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `follows`
--

INSERT INTO `follows` (`my_id`, `friend_id`) VALUES
(1, 3),
(3, 4),
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
CREATE TABLE IF NOT EXISTS `sessions` (
  `user_id` int(11) NOT NULL,
  `session_key` varchar(32) NOT NULL,
  `expiration_date` timestamp NOT NULL,
  PRIMARY KEY (`user_id`,`session_key`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sessions`
--

INSERT INTO `sessions` (`user_id`, `session_key`, `expiration_date`) VALUES
(3, 'VwdsGOtgm75M3k1rBmnNvlWotQDnLGB6', '2019-04-18 13:48:27');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_login` varchar(32) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_prenom` varchar(255) NOT NULL,
  `user_nom` varchar(255) NOT NULL,
  `user_sexe` varchar(32) NOT NULL,
  `user_age` int(32) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login` (`user_login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_login`, `user_password`, `user_prenom`, `user_nom`, `user_sexe`, `user_age`) VALUES
(1, 'PAUL', 'AHAH', 'Paul', 'Damn', 'H', 16),
(3, 'moi', 'moi', 'moi', 'moi', 'moi', 10),
(4, 'moei2', 'monmdp', 'Qtin', 'monmdp', 'male', 22),
(5, 'dd', 'dd', 'ddf', 'dd', 'zdzd', 77),
(6, 'Jean', 'Michel', 'JP', 'Michel', 'Homme', 40),
(7, 'JP', 'JP', 'JP', 'JP', 'Homme', 26);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
