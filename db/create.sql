-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Nov 16, 2020 at 12:35 AM
-- Server version: 5.7.32-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ncq-workflow`
--

-- --------------------------------------------------------

--
-- Table structure for table `ncq_workflow`
--

CREATE TABLE `ncq_workflow` (
  `id_workflow` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ncq_workflow`
--

-- --------------------------------------------------------

--
-- Table structure for table `ncq_workflow_category`
--

CREATE TABLE `ncq_workflow_category` (
  `id_category` int(11) NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `uuid` varchar(255) NOT NULL,
  `parent_category_id_category` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Table structure for table `workflow_category_join`
--

CREATE TABLE `workflow_category_join` (
  `id_workflow` int(11) NOT NULL,
  `id_category` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `workflow_variants`
--

CREATE TABLE `workflow_variants` (
  `id_variant` int(11) NOT NULL,
  `id_workflow` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



--
-- Indexes for table `ncq_workflow`
--
ALTER TABLE `ncq_workflow`
  ADD PRIMARY KEY (`id_workflow`),
  ADD UNIQUE KEY `UK_2019964dx1w262bpobrds8ago` (`uuid`);

--
-- Indexes for table `ncq_workflow_category`
--
ALTER TABLE `ncq_workflow_category`
  ADD PRIMARY KEY (`id_category`),
  ADD UNIQUE KEY `UK_9tj8kb2xcffxclrkfwl7s5muf` (`uuid`),
  ADD KEY `FKf1svqh7cj5uy75koqmguutbaq` (`parent_category_id_category`);

--
-- Indexes for table `workflow_category_join`
--
ALTER TABLE `workflow_category_join`
  ADD PRIMARY KEY (`id_workflow`,`id_category`),
  ADD KEY `FKg9be107vpd2ot7edgwvlaaf0g` (`id_category`);

--
-- Indexes for table `workflow_variants`
--
ALTER TABLE `workflow_variants`
  ADD PRIMARY KEY (`id_workflow`,`id_variant`),
  ADD KEY `FKen7bwjmxwhw550ltndiq60yhj` (`id_variant`);

--
-- AUTO_INCREMENT for table `ncq_workflow`
--
ALTER TABLE `ncq_workflow`
  MODIFY `id_workflow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `ncq_workflow_category`
--
ALTER TABLE `ncq_workflow_category`
  MODIFY `id_category` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
