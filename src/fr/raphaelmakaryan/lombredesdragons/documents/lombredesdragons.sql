-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : jeu. 07 août 2025 à 14:40
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `lombredesdragons`
--

-- --------------------------------------------------------

--
-- Structure de la table `Board`
--

CREATE TABLE `Board` (
  `ID` int(11) NOT NULL,
  `idCharacter` int(11) NOT NULL,
  `Board` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Character`
--

CREATE TABLE `Character` (
  `ID` int(11) NOT NULL,
  `Type` varchar(1000) NOT NULL,
  `Name` varchar(1000) NOT NULL,
  `LifePoints` int(11) NOT NULL,
  `Strength` int(11) NOT NULL,
  `OffensiveEquipment` text DEFAULT NULL,
  `DefensiveEquipment` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `Survival`
--

CREATE TABLE `Survival` (
  `ID` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `position` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Board`
--
ALTER TABLE `Board`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idCharacter` (`idCharacter`);

--
-- Index pour la table `Character`
--
ALTER TABLE `Character`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `Survival`
--
ALTER TABLE `Survival`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `idUser` (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Board`
--
ALTER TABLE `Board`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Character`
--
ALTER TABLE `Character`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `Survival`
--
ALTER TABLE `Survival`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Board`
--
ALTER TABLE `Board`
  ADD CONSTRAINT `Board_ibfk_1` FOREIGN KEY (`idCharacter`) REFERENCES `Character` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `Survival`
--
ALTER TABLE `Survival`
  ADD CONSTRAINT `Survival_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `Character` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
