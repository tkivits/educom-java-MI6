-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Gegenereerd op: 24 aug 2022 om 14:23
-- Serverversie: 10.4.25-MariaDB
-- PHP-versie: 8.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mi6`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `agents`
--

CREATE TABLE `agents` (
  `ID` int(3) NOT NULL,
  `service_num` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `secret_pass` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `active` tinyint(1) NOT NULL,
  `license_to_kill` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `agents`
--

INSERT INTO `agents` (`ID`, `service_num`, `secret_pass`, `active`, `license_to_kill`) VALUES
(1, '002', 'For ThE Royal QUEEN', 1, '2026-08-19 16:13:50'),
(2, '005', 'FOR The RoYaL Queen', 1, '2022-07-05 16:13:50'),
(3, '007', 'FoR THE royal queen', 1, '2022-11-16 23:13:50'),
(4, '030', 'FOR THE royal QuEeN', 1, NULL),
(5, '102', 'For THE ROYal QUeen', 1, NULL),
(6, '777', 'FOR tHe rOyAl qUeEn', 0, '2024-02-13 00:00:00');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `login_attempts`
--

CREATE TABLE `login_attempts` (
  `ID` int(3) NOT NULL,
  `service_num` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `time_login` datetime NOT NULL,
  `success` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `agents`
--
ALTER TABLE `agents`
  ADD PRIMARY KEY (`ID`);

--
-- Indexen voor tabel `login_attempts`
--
ALTER TABLE `login_attempts`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `agents`
--
ALTER TABLE `agents`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT voor een tabel `login_attempts`
--
ALTER TABLE `login_attempts`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
