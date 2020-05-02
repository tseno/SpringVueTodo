# SpringVueTodo

Spring Boot + Vue.js で SPA な TodoListを作成

# システム概要

## システム構成

- Java8以上
- Spring Boot
- MyBatis
- MySQL
- Vue cli

# 新規プロジェクト作成

- Spring Initializr（ https://start.spring.io/ ）から以下のDepenciesを追加してプロジェクトを作成
  - Spring Boot DevTools
  - Lombok
  - Spring Web
  - MyBatis Framework
  - MySQL Driver
  - H2 Database

# DockerでMySQLを起動する

- docker-compose.yml

```
version: '3'

services:
  mysql:
    image: mysql:8.0
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    env_file: .env
    environment:
      TZ: "Asia/Tokyo"

    ports:
      - 3306:3306
    volumes:
      - mysql:/var/lib/mysql
      - ./mysql/initdb:/docker-entrypoint-initdb.d

  phpmyadmin:
    image: phpmyadmin/phpmyadmin
    restart: always
    ports:
      - 8090:80
    environment:
      PMA_ARBITRARY: 1
    env_file: .env
    depends_on:
      - mysql

volumes:
  mysql:
```

- .env

```
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=test_db
MYSQL_USER=test
MYSQL_PASSWORD=test

PMA_HOST=mysql
PMA_USER=root
PMA_PASSWORD=root
```

- mysql/initdb/1_create_tables.sql
- docker-compose 起動時に作成したいDDLを記述する

```
create table if not exists task
(
    id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title varchar(30) NOT NULL,
    due_date date,
    checked tinyint(1) default 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

- mysql/initdb/2_insert_seed.sql
- docker-compose 起動時に挿入したいDMLを記述する

```
insert into task (title, due_date) values ('書類の届け出', '2020-05-31');
insert into task (title, due_date) values ('給料日', '2020-05-25');
insert into task (title, due_date) values ('マスクを買う', '2020-05-01');
```

# APIの作成

- controllerの作成
  - `@RestController`で作成する
- serviceの作成
- 

## mapperの作成

# APIのテスト作成

# Vue cliでフロント側作成

# 完成
