---DELETE TABLE---
DROP TABLE IF EXISTS "rt_request_history";
DROP TABLE IF EXISTS "rt_request";
DROP TABLE IF EXISTS "rt_workspace";
DROP TABLE IF EXISTS "rt_user_role";
DROP TABLE IF EXISTS "rt_user";
DROP TABLE IF EXISTS "rt_role";


---CREATE TABLE---

CREATE TABLE "rt_role"
(
    "id"         serial       NOT NULL,
    "active"     boolean,
    "created"    TIMESTAMP,
    "created_by" bigint,
    "updated"    TIMESTAMP,
    "updated_by" bigint,
    "name"       varchar(100) NOT NULL,
    CONSTRAINT "rt_role_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );

CREATE TABLE "rt_user"
(
    "id"         serial       NOT NULL,
    "active"     boolean,
    "created"    TIMESTAMP,
    "created_by" bigint,
    "updated"    TIMESTAMP,
    "updated_by" bigint,
    "email"      varchar(150) NOT NULL,
    "password"   varchar(100) NOT NULL,
    "name"       varchar(100),
    "surname"    varchar(100),
    CONSTRAINT "rt_user_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );


CREATE TABLE "rt_user_role"
(
    "id"         serial NOT NULL,
    "active"     boolean,
    "created"    TIMESTAMP,
    "created_by" bigint,
    "updated"    TIMESTAMP,
    "updated_by" bigint,
    "fk_role_id" bigint NOT NULL,
    "fk_user_id" bigint NOT NULL,
    CONSTRAINT "rt_user_role_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_user_role"
    ADD CONSTRAINT "rt_user_role_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_user_role"
    ADD CONSTRAINT "rt_user_role_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_user_role"
    ADD CONSTRAINT "rt_user_role_fk2" FOREIGN KEY ("fk_role_id") REFERENCES "rt_role" ("id");
ALTER TABLE "rt_user_role"
    ADD CONSTRAINT "rt_user_role_fk3" FOREIGN KEY ("fk_user_id") REFERENCES "rt_user" ("id");


CREATE TABLE "rt_workspace"
(
    "id"         serial       NOT NULL,
    "active"     boolean,
    "created"    TIMESTAMP,
    "created_by" bigint,
    "updated"    TIMESTAMP,
    "updated_by" bigint,
    "fk_user_id" bigint NOT NULL,
    "name"       varchar(255) NOT NULL,
    CONSTRAINT "rt_workspace_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_workspace"
    ADD CONSTRAINT "rt_workspace_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_workspace"
    ADD CONSTRAINT "rt_workspace_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_workspace"
    ADD CONSTRAINT "rt_workspace_fk2" FOREIGN KEY ("fk_user_id") REFERENCES "rt_user" ("id");


CREATE TABLE "rt_request"
(
    "id"                   serial       NOT NULL,
    "active"               boolean,
    "created"              TIMESTAMP,
    "created_by"           bigint,
    "updated"              TIMESTAMP,
    "updated_by"           bigint,
    "fk_workspace_id"      bigint       NOT NULL,
    "request_type"         varchar(50)  NOT NULL,
    "scheduled_type"       varchar(50)  NOT NULL,
    "name"                 varchar(50)  NOT NULL,
    "protocol"             varchar(50)  NOT NULL,
    "url"                  varchar(255) NOT NULL,
    "port"                 varchar(50),
    "body"                 varchar,
    "try_count"            bigint default 0,
    CONSTRAINT "rt_request_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk2" FOREIGN KEY ("fk_workspace_id") REFERENCES "rt_workspace" ("id");


CREATE TABLE "rt_request_history"
(
    "id"                  serial NOT NULL,
    "active"              boolean,
    "created"             TIMESTAMP,
    "created_by"          bigint,
    "updated"             TIMESTAMP,
    "updated_by"          bigint,
    "fk_request_id"       bigint NOT NULL,
    "response_code"       bigint NOT NULL,
    "body"                varchar,
    "request_date"        TIMESTAMP   NOT NULL,
    CONSTRAINT "rt_request_history_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_request_history"
    ADD CONSTRAINT "rt_request_history_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request_history"
    ADD CONSTRAINT "rt_request_history_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request_history"
    ADD CONSTRAINT "rt_request_history_fk2" FOREIGN KEY ("fk_request_id") REFERENCES "rt_request" ("id");


---INSERT TABLE---
INSERT INTO "rt_role" (id, active, created, created_by, updated, updated_by, name) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 'ADMIN');
INSERT INTO "rt_role" (id, active, created, created_by, updated, updated_by, name) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 'USER');


INSERT INTO "rt_user" (id, active, created, created_by, updated, updated_by, email, password, name, surname) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 'ahmet@ahmet.com', '123456', 'Ahmet', 'Okay');
INSERT INTO "rt_user" (id, active, created, created_by, updated, updated_by, email, password, name, surname) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 'turkuaz@turkuaz.com', '123456', 'Turkuaz', 'Şengül');

INSERT INTO "rt_user_role" (id, active, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 1);
INSERT INTO "rt_user_role" (id, active, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 2);
INSERT INTO "rt_user_role" (id, active, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 2, 1);
INSERT INTO "rt_user_role" (id, active, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 2, 2);

INSERT INTO "rt_workspace" (id, active, created, created_by, updated, updated_by, fk_user_id, name) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'Test Workspace');

INSERT INTO "rt_request" (id, active, created, created_by, updated, updated_by, fk_workspace_id, name, request_type, scheduled_type, protocol, url, port, body) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'httpbin.org', 'GET', 'MIN1', 'HTTPS', '34.199.80.110', '443', null);
INSERT INTO "rt_request" (id, active, created, created_by, updated, updated_by, fk_workspace_id, name, request_type, scheduled_type, protocol, url, port, body) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'google.com', 'GET', 'MIN1', 'HTTPS', '142.250.184.142', '443', null);
INSERT INTO "rt_request" (id, active, created, created_by, updated, updated_by, fk_workspace_id, name, request_type, scheduled_type, protocol, url, port, body) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'google.com', 'GET', 'MIN1', 'HTTPS', 'google.com', '443', null);
INSERT INTO "rt_request" (id, active, created, created_by, updated, updated_by, fk_workspace_id, name, request_type, scheduled_type, protocol, url, port, body) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'google.com', 'POST', 'MIN1', 'HTTPS', '142.250.184.142', '443', '{"name": "Upendra", "job": "Programmer"}');
INSERT INTO "rt_request" (id, active, created, created_by, updated, updated_by, fk_workspace_id, name, request_type, scheduled_type, protocol, url, port, body) VALUES (DEFAULT, true, CURRENT_TIMESTAMP, NULL, NULL, NULL, 1, 'google.com', 'POST', 'MIN1', 'HTTPS', '142.250.184.142', '443', '{"name": "Dünya", "job": "Engineer"}');

