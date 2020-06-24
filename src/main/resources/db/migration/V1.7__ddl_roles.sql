DROP table if exists q_shop.roles CASCADE;

CREATE TABLE q_shop.roles
(
    id   INT          NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE
);

