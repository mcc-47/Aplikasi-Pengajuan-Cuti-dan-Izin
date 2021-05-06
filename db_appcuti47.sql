-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 06, 2021 at 03:38 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_appcuti47`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(5) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `gender` enum('Male','Female') NOT NULL,
  `religion` enum('Islam','Protestan','Katolik','Hindu','Budha','Kong Hu Cu') NOT NULL,
  `email` varchar(50) NOT NULL,
  `job_title` enum('Application Developer','Admin HR') NOT NULL,
  `marital_status` enum('Married','Single') NOT NULL,
  `total_leave` int(5) NOT NULL,
  `entry_date` date NOT NULL,
  `discharge_date` date DEFAULT NULL,
  `manager_id` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `employee_name`, `gender`, `religion`, `email`, `job_title`, `marital_status`, `total_leave`, `entry_date`, `discharge_date`, `manager_id`) VALUES
(1, 'Joko Santosa', 'Male', 'Islam', 'joko@yopmail.com', 'Application Developer', 'Married', 3, '2015-01-10', NULL, 1),
(2, 'Devid Erliando ', 'Male', 'Islam', 'devid@yopmail.com', 'Application Developer', 'Married', 4, '2015-02-10', NULL, 1),
(3, 'Wahyu Kuncoro', 'Male', 'Islam', 'wahyu@yopmail.com', 'Application Developer', 'Single', 8, '2015-03-10', NULL, 2),
(4, 'Aqira Kelana', 'Male', 'Protestan', 'aqira@yopmail.com', 'Application Developer', 'Single', 0, '2015-03-10', NULL, 2),
(5, 'Fadel Muhammad Nasution', 'Male', 'Islam', 'fadel@yopmail.com', 'Application Developer', 'Single', 5, '2021-03-08', NULL, 3),
(6, 'Jaka Brajadenta', 'Male', 'Islam', 'jaka@yopmail.com', 'Application Developer', 'Single', 9, '2016-04-10', NULL, 3),
(7, 'Yosie Fridolin', 'Female', 'Protestan', 'yosie@yopmail.com', 'Application Developer', 'Single', 5, '2017-04-10', NULL, 4),
(8, 'Florentina Vela Nindyasari', 'Female', 'Katolik', 'florentina@yopmail.com', 'Application Developer', 'Single', 9, '2017-04-10', NULL, 4),
(9, 'Agus Kuncoro', 'Male', 'Islam', 'agus@yopmail.com', 'Admin HR', 'Single', 9, '2015-03-10', NULL, 1),
(10, 'Irene Sugiarto', 'Female', 'Islam', 'irene@yopmail.com', 'Admin HR', 'Married', 9, '2015-03-10', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `holiday`
--

CREATE TABLE `holiday` (
  `id` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `holiday_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `holiday`
--

INSERT INTO `holiday` (`id`, `name`, `holiday_date`) VALUES
(1, 'New Year 2015', '2015-01-01'),
(2, 'New Year 2016', '2016-01-01'),
(3, 'New Year 2017', '2017-01-01'),
(4, 'New Year 2018', '2018-01-01'),
(5, 'New Year 2019', '2019-01-01'),
(6, 'New Year 2020', '2020-01-01'),
(7, 'New Year 2021', '2021-01-01'),
(8, 'Imlek 2572 Kongzili', '2021-02-02'),
(9, 'Isra Mi\'raj Muhammad SAW', '2021-03-11'),
(10, 'Nyepi 1943 Saka', '2021-03-14'),
(11, 'Good Friday', '2021-04-02'),
(12, 'International Labor Day', '2021-05-01'),
(13, 'Ascension Day of Jesus', '2021-05-13'),
(14, 'Idul Fitri 1442 H', '2021-05-13'),
(15, 'Idul Fitri 1442 H', '2021-05-14'),
(16, 'Vesak 2565', '2021-05-26'),
(17, 'Pancasila Day', '2021-06-01'),
(18, 'Idul Adha 1442 H', '2021-07-20'),
(19, 'New Year 1443 H', '2021-08-10'),
(20, 'Independent Day', '2021-08-17'),
(21, 'Birthday of Prophet Muhammad', '2021-10-29'),
(22, 'Christmas Day', '2021-12-25'),
(23, 'New Year 2022', '2022-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `leave_type`
--

CREATE TABLE `leave_type` (
  `leave_id` int(2) NOT NULL,
  `leave_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `leave_type`
--

INSERT INTO `leave_type` (`leave_id`, `leave_name`) VALUES
(1, 'permit'),
(2, 'paid leave'),
(3, 'maternity leave'),
(4, 'marriage leave'),
(5, 'pilgrimage to mecca leave'),
(6, 'child circumcision leave'),
(7, 'bereavement leave');

-- --------------------------------------------------------

--
-- Table structure for table `manager_fill`
--

CREATE TABLE `manager_fill` (
  `req_id` int(5) NOT NULL,
  `note` text DEFAULT NULL,
  `manager_id` int(5) NOT NULL,
  `status_id` int(2) NOT NULL,
  `approvement_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `manager_fill`
--

INSERT INTO `manager_fill` (`req_id`, `note`, `manager_id`, `status_id`, `approvement_date`) VALUES
(1, 'All responsibility handle by A2', 1, 3, '2021-04-30'),
(2, 'Dont forget to notif your team', 1, 2, '2015-12-28'),
(3, 'Happy wedding by the way', 2, 2, '2018-01-22'),
(4, 'Keep strong', 3, 2, '2018-03-30');

--
-- Triggers `manager_fill`
--
DELIMITER $$
CREATE TRIGGER `update_leave` AFTER UPDATE ON `manager_fill` FOR EACH ROW IF NEW.status_id=2 THEN
            UPDATE employee SET total_leave = total_leave -(SELECT leave_duration
                            FROM request r
                            JOIN employee e ON r.employee_id = e.employee_id
                            JOIN manager_fill m ON m.req_id = r.req_id
                            WHERE r.req_id = NEW.req_id)

       WHERE employee_id = (SELECT e.employee_id
                            FROM employee e JOIN request r ON e.employee_id = r.employee_id
                            JOIN manager_fill m ON r.req_id = m.req_id
                                                    
                            WHERE r.req_id = NEW.req_id AND r.leave_id=2);

        END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `mandatory_leave`
--

CREATE TABLE `mandatory_leave` (
  `id` int(5) NOT NULL,
  `name` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `duration` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mandatory_leave`
--

INSERT INTO `mandatory_leave` (`id`, `name`, `start_date`, `duration`) VALUES
(16, 'Idul Fitri 1442 H', '2021-05-12', 2);

--
-- Triggers `mandatory_leave`
--
DELIMITER $$
CREATE TRIGGER `mandatory_trigger` AFTER INSERT ON `mandatory_leave` FOR EACH ROW BEGIN
	UPDATE employee SET total_leave= total_leave-NEW.duration;
    END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `privilege`
--

CREATE TABLE `privilege` (
  `privilege_id` int(4) NOT NULL,
  `privilege_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `privilege`
--

INSERT INTO `privilege` (`privilege_id`, `privilege_name`) VALUES
(9, 'adminhr_create'),
(12, 'adminhr_delete'),
(10, 'adminhr_read'),
(11, 'adminhr_update'),
(1, 'employee_create'),
(4, 'employee_delete'),
(2, 'employee_read'),
(3, 'employee_update'),
(5, 'manager_create'),
(8, 'manager_delete'),
(6, 'manager_read'),
(7, 'manager_update');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `req_id` int(5) NOT NULL,
  `employee_id` int(5) NOT NULL,
  `leave_id` int(2) NOT NULL,
  `leave_duration` int(3) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `reasons` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`req_id`, `employee_id`, `leave_id`, `leave_duration`, `start_date`, `end_date`, `reasons`) VALUES
(1, 1, 5, 30, '2016-01-04', '2016-02-10', 'Go to Umrah with family'),
(2, 2, 6, 2, '2016-02-15', '2016-02-16', 'Elder child circumcision event'),
(3, 3, 4, 3, '2018-02-01', '2018-02-03', 'Wik wik wik'),
(4, 5, 7, 2, '2018-04-02', '2018-04-03', 'Grandma last day fufufu');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(3) NOT NULL,
  `role_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(3, 'AdminHR'),
(1, 'Employee'),
(2, 'Manager');

-- --------------------------------------------------------

--
-- Table structure for table `roles_privileges`
--

CREATE TABLE `roles_privileges` (
  `role_id` int(3) NOT NULL,
  `privilege_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles_privileges`
--

INSERT INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(3, 9),
(3, 10),
(3, 11),
(3, 12);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(2) NOT NULL,
  `status_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_name`) VALUES
(1, 'on progress'),
(2, 'accepted'),
(3, 'denied');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`) VALUES
(1, 'joko', '$2y$10$d/7i7Xs/rRD1QRyda8pyqu2hiirlW8nOVEuiEtAXFyjN5jyONPDsm'),
(2, 'devid', '$2y$10$h9XrhE/CfwFFjjS7fF8bkueDktw7HgjKINAyL7XxRE15oyd6AVUD6'),
(3, 'wahyu', '$2y$10$nE/aPRLOY2rxnCErwjwt/ugStvU5r.yNHb8yJ8nza6KOdREHgdWuy'),
(4, 'aqira', '$2y$10$G1RDp7KCDzZoPb5.yBVZ4Ow7fv36NVlcn3R8JMr4ewSPnySqKSWva'),
(5, 'fadel', '$2y$10$iStjCkKOLOtjyeF0qEwKUOc1rb.BaiKNj0PLRbiA8e9zm9bOTCx/m'),
(6, 'jaka', '$2y$10$mm0E0yydLaPqCZEFY/ujPOIFRhPEU/5n3D15HJRdBi5JiB9e1Ox42'),
(7, 'yosie', '$2y$10$CqBoZk/Law47v.WzSMLrn.2ZTnGRJw31O7fa2ECas9GZGTGIPGPgW'),
(8, 'florentina', '$2y$10$kZEiIj4kBHrInBI/qK9FMuaIuEE3tnI/cELqGgrIud0JWkS9hRY6O'),
(9, 'agus', '$2y$10$xXgQBONNKBQCQQzNz/bTfOq3SZfTrDsvJzO4.tQewjO6V8eYbwjiG'),
(10, 'irene', '$2y$10$BvEq0h6PkQ2VXbqLNXV89OfYhf0B1ZhF.5KN7Rlt0tUUsyJEbc4PK');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(5) NOT NULL,
  `role_id` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(4, 1),
(4, 2),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 3),
(10, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `EMAIL` (`email`),
  ADD KEY `manager_id` (`manager_id`);

--
-- Indexes for table `holiday`
--
ALTER TABLE `holiday`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `leave_type`
--
ALTER TABLE `leave_type`
  ADD PRIMARY KEY (`leave_id`);

--
-- Indexes for table `manager_fill`
--
ALTER TABLE `manager_fill`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `status_id` (`status_id`);

--
-- Indexes for table `mandatory_leave`
--
ALTER TABLE `mandatory_leave`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `privilege`
--
ALTER TABLE `privilege`
  ADD PRIMARY KEY (`privilege_id`),
  ADD UNIQUE KEY `Privileges Name` (`privilege_name`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`req_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `leave_id` (`leave_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`),
  ADD UNIQUE KEY `Rolename` (`role_name`);

--
-- Indexes for table `roles_privileges`
--
ALTER TABLE `roles_privileges`
  ADD KEY `role_id` (`role_id`),
  ADD KEY `privilege_id` (`privilege_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `Username` (`username`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `holiday`
--
ALTER TABLE `holiday`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `leave_type`
--
ALTER TABLE `leave_type`
  MODIFY `leave_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `mandatory_leave`
--
ALTER TABLE `mandatory_leave`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`manager_id`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `manager_fill`
--
ALTER TABLE `manager_fill`
  ADD CONSTRAINT `manager_fill_ibfk_1` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  ADD CONSTRAINT `manager_fill_ibfk_2` FOREIGN KEY (`req_id`) REFERENCES `request` (`req_id`);

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`),
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`leave_id`) REFERENCES `leave_type` (`leave_id`);

--
-- Constraints for table `roles_privileges`
--
ALTER TABLE `roles_privileges`
  ADD CONSTRAINT `roles_privileges_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `roles_privileges_ibfk_2` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`privilege_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `employee` (`employee_id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `users_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `users_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `change_total_leave` ON SCHEDULE EVERY 1 YEAR STARTS '2021-01-01 00:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE
       employee      
    SET
       total_leave = 12$$

CREATE DEFINER=`root`@`localhost` EVENT `del` ON SCHEDULE EVERY 30 SECOND STARTS '2021-05-01 15:07:53' ON COMPLETION NOT PRESERVE ENABLE DO DELETE FROM request
	WHERE req_id=6$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
