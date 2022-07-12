CREATE TABLE `wallet` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `wallet_id` nvarchar(255) NOT NULL,
                          `nickname` nvarchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

