-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 19, 2016 at 07:09 AM
-- Server version: 5.1.36
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `government`
--

-- --------------------------------------------------------

--
-- Table structure for table `aadhar`
--

CREATE TABLE IF NOT EXISTS `aadhar` (
  `aadhar_id` bigint(20) NOT NULL,
  `name` text NOT NULL,
  `dob` varchar(10) NOT NULL,
  `gender` text NOT NULL,
  `address` varchar(2000) NOT NULL,
  `city` text NOT NULL,
  `pincode` int(7) NOT NULL,
  `contact` bigint(13) NOT NULL,
  `image` longblob NOT NULL,
  UNIQUE KEY `aadhar_id` (`aadhar_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aadhar`
--

INSERT INTO `aadhar` (`aadhar_id`, `name`, `dob`, `gender`, `address`, `city`, `pincode`, `contact`, `image`) VALUES
(828709185529, 'Kanishk Sharma', '30/12/1994', 'Male', '61, Jagganath Pur Meerut', 'Meerut', 250002, 8860488735, 0x4141454141514141414141414141506c414141414a4759354f47566c4d6d56684c5755334e5745744e4449794e4331685a6a67794c5749324f4755784e47466c596a566b4f512e6a7067),
(217777017071, 'Aayush Arora', '27/10/1995', 'Male', '539/1 Meerut Road South Civil Lines, Muzzafarnagar', 'Muzzafarnagar', 251002, 9971860311, 0x41414541415141414141414141414d41414141414a44677a5a544d784d4452694c544e685a4759744e446c6b4e5331685a4463334c5751334e7a41334d324e6a595759334e512e6a7067),
(258479369048, 'Aakarsh Goel', '09/09/1996', 'Male', 'H.No-22 Kaiserganj, Meerut', 'Meerut', 250001, 7834840168, 0x61616b617273682e6a7067),
(713575365744, 'Anant Kumar Singh', '15/09/1994', 'Male', '22-C, Tot Market Sector 62,Noida', 'Noida', 201301, 9560619115, 0x616e616e742e6a7067);
