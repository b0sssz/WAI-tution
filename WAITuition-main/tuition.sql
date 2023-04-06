-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2023 at 04:24 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tuition`
--

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `gender` varchar(40) NOT NULL,
  `email` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `gender`, `email`, `name`) VALUES
(2, 'Female', 'IS@tuition.com', 'Iskandar D Luffy'),
(4, 'Male', 'Wrizz@tuition.com', 'Wazir Rizz'),
(6, 'Male', 'boss@tuition.com', 'adriana samad'),
(7, 'Female', 'imis@gmail.com', 'imis'),
(10, 'Female', 'ammar@tuition.com', 'ammar zaidi');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE `subject` (
  `id` int(11) NOT NULL,
  `date` varchar(100) NOT NULL,
  `subject_name` varchar(20) NOT NULL,
  `time` varchar(40) NOT NULL,
  `total_stud` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `date`, `subject_name`, `time`, `total_stud`) VALUES
(1, '30 May 2023', 'Maths', '9.00 PM', 10),
(4, '30 May 2023', 'Science', '10.00 AM', 10),
(5, '30 Jun 2023', 'Bahasa', '9.00 PM', 100),
(6, '30 April 2023', 'English', '10.00 AM', 105),
(7, '30 April 2023', 'Komputer Sains', '10.00 AM', 20);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`) VALUES
(1, 'anuar@library.com', 'Syed', 'Anuar', '$2a$10$HM7YducrWBLRrbjN.HAwZu7XsEKReYUx1008OfJehbxj.7/vrV3Z.'),
(2, 'aan@library.com', 'Farhan', 'Raffi', '$2a$10$UnIFKwRk2VRm5fwlVJd3F./g64hmx7Cn7iMpqGe4OJ.5YAxwKnVW2'),
(4, 'test@ex.com', 'John', 'Kennedy', '$2a$10$sBZYHBsmMnK0rEkpLa4JuORJm9INwB3zwvA5pAf14apSsjWdwoplG'),
(5, '123@123.com', 'Syed', 'Anuar', 'R3pl1c4nT'),
(6, 'blqs@gmail.com', 'Nurul', 'Balqis', '$2a$10$8YP3wk10CPhOdCKYehuf1uItWISGXolANRZOXdbyCzVqFdg1BR8MG'),
(10, 'adriana.zanna2407@gmail.com', 'adriana', 'samad', '$2a$10$lUSYkSMlz8Vw4xvBkDKHLe4B1C70KedTnkjMp9UXAHTzLpUA.iHYy'),
(11, 'imranimran@gmail.com', 'Imran', 'Talaha', '$2a$10$3Fx3KeMHnL.HWrSJz4VvAub5BdsI9yrLCasMxo34Z6R2aeDHB3os6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_kibbepcitr0a3cpk3rfr7nihn` (`email`);
ALTER TABLE `student` ADD FULLTEXT KEY `full_text_index` (`email`,`name`,`gender`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_9x44p8j4xvkb6vhaccexngaao` (`subject_name`);
ALTER TABLE `subject` ADD FULLTEXT KEY `name_of_index` (`subject_name`,`date`,`time`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `subject`
--
ALTER TABLE `subject`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
