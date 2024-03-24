
BEGIN;
    CREATE SEQUENCE user_id_seq;
    CREATE SEQUENCE vhs_id_seq;
    CREATE TABLE IF NOT EXISTS public.vhs
    (
        id integer NOT NULL DEFAULT nextval('vhs_id_seq'::regclass),
        late_return_fee numeric(4,2) NOT NULL,
        date_created timestamp without time zone NOT NULL,
        date_modified timestamp without time zone NOT NULL,
        title text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT "VHS_pkey" PRIMARY KEY (id)
    )

        TABLESPACE pg_default;

    CREATE TABLE IF NOT EXISTS public."user"
    (
        id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
        date_created timestamp without time zone NOT NULL,
        date_modified timestamp without time zone NOT NULL,
        username text COLLATE pg_catalog."default" NOT NULL,
        CONSTRAINT user_pkey PRIMARY KEY (id)
    )

        TABLESPACE pg_default;

    CREATE TABLE IF NOT EXISTS public.rental
    (
        id integer NOT NULL DEFAULT nextval('user_id_seq'::regclass),
        user_id bigint NOT NULL,
        vhs_id bigint NOT NULL,
        date_rented date NOT NULL,
        date_due date NOT NULL,
        date_returned date,
        date_created timestamp without time zone NOT NULL,
        date_modified timestamp without time zone NOT NULL,
        fee numeric(8,3),
        fee_paid boolean,
        CONSTRAINT "Rental_pkey" PRIMARY KEY (id),
        CONSTRAINT "Fk_Rental_User" FOREIGN KEY (user_id)
            REFERENCES public."user" (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION,
        CONSTRAINT "Fk_Rental_Vhs" FOREIGN KEY (vhs_id)
            REFERENCES public.vhs (id) MATCH SIMPLE
            ON UPDATE NO ACTION
            ON DELETE NO ACTION
    )

        TABLESPACE pg_default;


    INSERT INTO "user" (date_created, date_modified, username)
    VALUES
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ivan'),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Ana'),
        (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Marko');

    INSERT INTO vhs (title, late_return_fee, date_created, date_modified)
    VALUES
        ('The Matrix', 1.50, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
        ('Jurassic Park', 2.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
        ('Titanic', 1.75, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

    INSERT INTO rental (user_id, vhs_id, date_rented, date_due, date_returned, date_created, date_modified, fee, fee_paid)
    VALUES
        (1, 1, '2024-03-20', '2024-03-25', '2024-03-22', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, TRUE), -- Returned
        (2, 3, '2024-03-22', '2024-03-27', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null, null),      -- Not returned
        (3, 2, '2024-03-25', '2024-03-30', NULL, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, null, null);      -- Not returned
COMMIT;
