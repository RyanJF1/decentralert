CREATE TABLE `address` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `wallet_id` nvarchar(255) NOT NULL,
                          `nickname` nvarchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `hibernate_sequence` (
    `next_val` bigint DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` nvarchar(255) NOT NULL,
                           `email` nvarchar(255) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS user_address(
    user_id  int REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE
    , address_id int REFERENCES address(id) ON UPDATE CASCADE ON DELETE CASCADE
    , PRIMARY KEY (user_id, address_id)
    );