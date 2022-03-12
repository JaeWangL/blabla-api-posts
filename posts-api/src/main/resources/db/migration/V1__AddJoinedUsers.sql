ALTER TABLE `posts` ADD COLUMN `joined_users` int(11) NOT NULL DEFAULT 0
AFTER `thumbnail_original_filename`;
