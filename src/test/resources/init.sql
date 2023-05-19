CREATE TABLE users
(
    id       UUID         NOT NULL,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE habits
(
    id               UUID         NOT NULL,
    user_id          UUID,
    tag              VARCHAR(255) NOT NULL,
    max_quantity     INTEGER      NOT NULL,
    frequency        VARCHAR(255) NOT NULL,
    current_quantity INTEGER      NOT NULL,
    date_done        date,
    done             BOOLEAN      NOT NULL,
    CONSTRAINT pk_habits PRIMARY KEY (id)
);

ALTER TABLE habits
    ADD CONSTRAINT FK_HABITS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

