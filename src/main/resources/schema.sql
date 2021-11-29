---DELETE TABLE---
DROP TABLE IF EXISTS "rt_request_history";
DROP TABLE IF EXISTS "rt_response_type";
DROP TABLE IF EXISTS "rt_workspace_request";
DROP TABLE IF EXISTS "rt_workspace";
DROP TABLE IF EXISTS "rt_request";
DROP TABLE IF EXISTS "rt_scheduled_type";
DROP TABLE IF EXISTS "rt_request_type";
DROP TABLE IF EXISTS "rt_user_role";
DROP TABLE IF EXISTS "rt_user";
DROP TABLE IF EXISTS "rt_role";

---CREATE TABLE---
CREATE TABLE "rt_response_type"
(
    "id"         serial       NOT NULL,
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
    "updated_by" bigint,
    "name"       varchar(255) NOT NULL,
    CONSTRAINT "rt_response_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );

CREATE TABLE "rt_scheduled_type"
(
    "id"         serial       NOT NULL,
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
    "updated_by" bigint,
    "name"       varchar(255) NOT NULL,
    CONSTRAINT "rt_scheduled_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );

CREATE TABLE "rt_request_type"
(
    "id"         serial       NOT NULL,
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
    "updated_by" bigint,
    "name"       varchar(255) NOT NULL,
    CONSTRAINT "rt_request_type_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );

CREATE TABLE "rt_role"
(
    "id"         serial       NOT NULL,
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
    "updated_by" bigint,
    "name"       varchar(100) NOT NULL,
    CONSTRAINT "rt_role_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );

CREATE TABLE "rt_user"
(
    "id"         serial       NOT NULL,
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
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
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
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
    "created"    DATE,
    "created_by" bigint,
    "updated"    DATE,
    "updated_by" bigint,
    "name"       varchar(255) NOT NULL,
    CONSTRAINT "rt_workspace_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_workspace"
    ADD CONSTRAINT "rt_workspace_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_workspace"
    ADD CONSTRAINT "rt_workspace_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");



CREATE TABLE "rt_request"
(
    "id"                   serial       NOT NULL,
    "created"              DATE,
    "created_by"           bigint,
    "updated"              DATE,
    "updated_by"           bigint,
    "fk_request_type_id"   bigint       NOT NULL,
    "fk_scheduled_type_id" bigint       NOT NULL,
    "url"                  varchar(255) NOT NULL,
    "port"                 varchar(50),
    CONSTRAINT "rt_request_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk2" FOREIGN KEY ("fk_request_type_id") REFERENCES "rt_request_type" ("id");
ALTER TABLE "rt_request"
    ADD CONSTRAINT "rt_request_fk3" FOREIGN KEY ("fk_scheduled_type_id") REFERENCES "rt_scheduled_type" ("id");


CREATE TABLE "rt_workspace_request"
(
    "id"              serial NOT NULL,
    "created"         DATE,
    "created_by"      bigint,
    "updated"         DATE,
    "updated_by"      bigint,
    "fk_workspace_id" bigint NOT NULL,
    "fk_request_id"   bigint NOT NULL,
    CONSTRAINT "rt_workspace_request_pk" PRIMARY KEY ("id")
) WITH (
      OIDS = FALSE
      );
ALTER TABLE "rt_workspace_request"
    ADD CONSTRAINT "rt_workspace_request_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_workspace_request"
    ADD CONSTRAINT "rt_workspace_request_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
ALTER TABLE "rt_workspace_request"
    ADD CONSTRAINT "rt_workspace_request_fk2" FOREIGN KEY ("fk_workspace_id") REFERENCES "rt_workspace" ("id");
ALTER TABLE "rt_workspace_request"
    ADD CONSTRAINT "rt_workspace_request_fk3" FOREIGN KEY ("fk_request_id") REFERENCES "rt_request" ("id");


CREATE TABLE "rt_request_history"
(
    "id"                  serial NOT NULL,
    "created"             DATE,
    "created_by"          bigint,
    "updated"             DATE,
    "updated_by"          bigint,
    "fk_request_id"       bigint NOT NULL,
    "fk_response_type_id" bigint NOT NULL,
    "request_date"        DATE   NOT NULL,
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
ALTER TABLE "rt_request_history"
    ADD CONSTRAINT "rt_request_history_fk3" FOREIGN KEY ("fk_response_type_id") REFERENCES "rt_response_type" ("id");


---INSERT TABLE---
INSERT INTO "rt_role" (id, created, created_by, updated, updated_by, name) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 'ADMIN');
INSERT INTO "rt_role" (id, created, created_by, updated, updated_by, name) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 'USER');


INSERT INTO "rt_user" (id, created, created_by, updated, updated_by, email, password, name, surname) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 'ahmet@ahmet.com', '123456', 'Ahmet', 'Okay');
INSERT INTO "rt_user" (id, created, created_by, updated, updated_by, email, password, name, surname) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 'turkuaz@turkuaz.com', '123456', 'Turkuaz', 'Şengül');

INSERT INTO "rt_user_role" (id, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO "rt_user_role" (id, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 1, 2);
INSERT INTO "rt_user_role" (id, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 2, 1);
INSERT INTO "rt_user_role" (id, created, created_by, updated, updated_by, fk_user_id, fk_role_id) VALUES (DEFAULT, NULL, NULL, NULL, NULL, 2, 2);


-- ALTER TABLE "rt_user"
--     ADD CONSTRAINT "rt_user_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
-- ALTER TABLE "rt_user"
--     ADD CONSTRAINT "rt_user_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
--
--
--
-- ALTER TABLE "rt_role"
--     ADD CONSTRAINT "rt_role_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
-- ALTER TABLE "rt_role"
--     ADD CONSTRAINT "rt_role_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
--
--
--
-- ALTER TABLE "rt_request_type"
--     ADD CONSTRAINT "rt_request_type_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
-- ALTER TABLE "rt_request_type"
--     ADD CONSTRAINT "rt_request_type_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
--
-- ALTER TABLE "rt_response_type"
--     ADD CONSTRAINT "rt_response_type_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
-- ALTER TABLE "rt_response_type"
--     ADD CONSTRAINT "rt_response_type_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");
--
-- ALTER TABLE "rt_scheduled_type"
--     ADD CONSTRAINT "rt_scheduled_type_fk0" FOREIGN KEY ("created_by") REFERENCES "rt_user" ("id");
-- ALTER TABLE "rt_scheduled_type"
--     ADD CONSTRAINT "rt_scheduled_type_fk1" FOREIGN KEY ("updated_by") REFERENCES "rt_user" ("id");