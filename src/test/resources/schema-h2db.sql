create table if not exists task
(
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title varchar(30) NOT NULL,
    due_date date,
    checked tinyint(1) default 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
