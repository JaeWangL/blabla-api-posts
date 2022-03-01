DROP TABLE IF EXISTS `outbox`;
CREATE TABLE `client_request`
(
    `id`     varchar(255)   NOT NULL,
    `name`   varchar(255),
    `time`   timestamp,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `outbox`;
CREATE TABLE `outbox` (
    `id`              varchar(255)   NOT NULL,
    `content`         varchar(1024)  NOT NULL,
    `creation_time`   timestamp,
    `event_id`        varchar(255),
    `event_state`     varchar(255),
    `event_type_name` varchar(255),
    `times_sent`      int(11),
    `topic`           varchar(255),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `writers`;
CREATE TABLE `writers` (
    `id`            varchar(255)   NOT NULL,
    `device_type`   smallint(6)    NOT NULL,
    `device_id`     varchar(1024)  NOT NULL,
    `created_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts` (
    `id`                             varchar(255)     NOT NULL,
    `writer_id`                      varchar(255)     NOT NULL,
    `latitude`                       decimal(11, 7)   NOT NULL,
    `longitude`                      decimal(11, 7)   NOT NULL,
    `country`                        varchar(255)     NOT NULL DEFAULT '',
    `state`                          varchar(255)     NOT NULL DEFAULT '',
    `city`                           varchar(255)     NOT NULL DEFAULT '',
    `street`                         varchar(255)     NOT NULL DEFAULT '',
    `detail_address`                 varchar(255)     NOT NULL DEFAULT '',
    `zip_code`                       varchar(255)     NOT NULL DEFAULT '',
    `title`                          varchar(255)     NOT NULL,
    `contents`                       longtext         NOT NULL,
    `thumbnail_domain`               varchar(255)     NOT NULL DEFAULT '',
    `thumbnail_blob_filename`        varchar(255)     NOT NULL DEFAULT '',
    `thumbnail_original_filename`    varchar(255)     NOT NULL DEFAULT '',
    `created_at`                     timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`                     timestamp        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
