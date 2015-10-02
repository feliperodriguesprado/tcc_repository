--drop schema public cascade;
--create schema public;
--create database smom;

CREATE SEQUENCE smom.public.seq_accounts_id;

CREATE TABLE smom.public.accounts (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_accounts_id'),
                description VARCHAR(50) NOT NULL,
                CONSTRAINT pk_accounts_id PRIMARY KEY (id)
);


ALTER SEQUENCE smom.public.seq_accounts_id OWNED BY smom.public.accounts.id;

CREATE SEQUENCE smom.public.seq_address_id;

CREATE TABLE smom.public.address (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_address_id'),
                people_id INTEGER NOT NULL,
                cep VARCHAR(9) NOT NULL,
                city VARCHAR(100) NOT NULL,
                uf VARCHAR(2) NOT NULL,
                district VARCHAR(100) NOT NULL,
                street VARCHAR(100) NOT NULL,
                CONSTRAINT pk_address_id PRIMARY KEY (id)
);


ALTER SEQUENCE smom.public.seq_address_id OWNED BY smom.public.address.id;

CREATE SEQUENCE smom.public.seq_financial_releases_id;

CREATE TABLE smom.public.financial_releases (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_financial_releases_id'),
                type INTEGER NOT NULL,
                account_id INTEGER NOT NULL,
                people_id INTEGER,
                payment_type_id INTEGER NOT NULL,
                create_date DATE NOT NULL,
                due_date DATE NOT NULL,
                payment_date DATE NOT NULL,
                is_paid BOOLEAN NOT NULL,
                description VARCHAR(1000) NOT NULL,
                value NUMERIC NOT NULL,
                CONSTRAINT pk_financial_releases_id PRIMARY KEY (id)
);


ALTER SEQUENCE smom.public.seq_financial_releases_id OWNED BY smom.public.financial_releases.id;

CREATE SEQUENCE smom.public.seq_payment_types_id;

CREATE TABLE smom.public.payment_types (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_payment_types_id'),
                description VARCHAR(50) NOT NULL,
                CONSTRAINT pk_payment_types_id PRIMARY KEY (id)
);


ALTER SEQUENCE smom.public.seq_payment_types_id OWNED BY smom.public.payment_types.id;

CREATE SEQUENCE smom.public.seq_peoples_id;

CREATE TABLE smom.public.peoples (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_peoples_id'),
                type INTEGER NOT NULL,
                name VARCHAR(100) NOT NULL,
                cpf_cnpj VARCHAR(18) NOT NULL,
                active BOOLEAN NOT NULL,
				date_create TIMESTAMP NOT NULL,
                CONSTRAINT pk_peoples_id PRIMARY KEY (id)
);


ALTER SEQUENCE smom.public.seq_peoples_id OWNED BY smom.public.peoples.id;

CREATE SEQUENCE smom.public.seq_phones_id;

CREATE TABLE smom.public.phones (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_phones_id'),
                number VARCHAR(15) NOT NULL,
                people_id INTEGER NOT NULL,
                CONSTRAINT pk_phones_id PRIMARY KEY (id),
		CONSTRAINT uk_phones_people_id_number UNIQUE (people_id, number)
);

ALTER SEQUENCE smom.public.seq_phones_id OWNED BY smom.public.phones.id;

CREATE SEQUENCE smom.public.seq_users_id;

CREATE TABLE smom.public.users (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_users_id'),
                name VARCHAR(100) NOT NULL,
                password VARCHAR(45) NOT NULL,
                username VARCHAR(45) NOT NULL,
                active BOOLEAN NOT NULL,
                CONSTRAINT pk_users PRIMARY KEY (id),
		CONSTRAINT uk_users_username UNIQUE (username)
);


ALTER SEQUENCE smom.public.seq_users_id OWNED BY smom.public.users.id;

CREATE SEQUENCE smom.public.seq_view_modules_id;

CREATE TABLE smom.public.view_modules (
                id INTEGER NOT NULL DEFAULT nextval('smom.public.seq_view_modules_id'),
                type INTEGER NOT NULL,
                symbolic_name VARCHAR(100) NOT NULL UNIQUE,
                active BOOLEAN NOT NULL,
                name VARCHAR(100) NOT NULL,
                context_path VARCHAR(100) NOT NULL,
                icon VARCHAR(50) NOT NULL,
                position INTEGER NOT NULL,
                parent INTEGER,
                CONSTRAINT pk_view_modules_id PRIMARY KEY (id),
		CONSTRAINT uk_view_modules_symbolic_name UNIQUE (symbolic_name),
		CONSTRAINT fk_view_modules_id_parent FOREIGN KEY (parent) REFERENCES smom.public.view_modules (id)
);


ALTER SEQUENCE smom.public.seq_view_modules_id OWNED BY smom.public.view_modules.id;

ALTER TABLE smom.public.financial_releases ADD CONSTRAINT fk_accounts_financial_releases
FOREIGN KEY (account_id)
REFERENCES smom.public.accounts (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE smom.public.financial_releases ADD CONSTRAINT fk_payment_types_financial_releases
FOREIGN KEY (payment_type_id)
REFERENCES smom.public.payment_types (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE smom.public.address ADD CONSTRAINT fk_peoples_address
FOREIGN KEY (people_id)
REFERENCES smom.public.peoples (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE smom.public.financial_releases ADD CONSTRAINT fk_peoples_financial_releases
FOREIGN KEY (people_id)
REFERENCES smom.public.peoples (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE smom.public.phones ADD CONSTRAINT fk_peoples_phones
FOREIGN KEY (people_id)
REFERENCES smom.public.peoples (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
