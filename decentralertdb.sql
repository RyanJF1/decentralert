CREATE TABLE `coin` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `last_trade_price` float DEFAULT NULL,
                        `price24h` float DEFAULT NULL,
                        `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                        `volume24h` float DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UK_64aog9dk80krue67e2a7s7ss1` (`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

