CREATE TABLE `outbox` (
    `id`              varchar(255)   NOT NULL,
    `content`         varchar(1024)  NOT NULL,
    `creation_time`   timestamp,
    `event_id`        varchar(255),
    `event_state`     varchar(255),
    `event_type_name` varchar(255),
    `times_sent`      int(11),
    `topic`           varchar(255)
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
