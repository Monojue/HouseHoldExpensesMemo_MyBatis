-- Table: public.expense

-- DROP TABLE IF EXISTS public.expense;

CREATE TABLE IF NOT EXISTS public.expense
(
    id integer NOT NULL DEFAULT nextval('expense_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    edate date,
    amount integer,
    CONSTRAINT expense_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.expense
    OWNER to postgres;