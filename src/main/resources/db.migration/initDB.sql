DROP TABLE IF EXISTS public.bills;
DROP TABLE IF EXISTS public.customers;
DROP TABLE IF EXISTS public.actions;
DROP SEQUENCE IF EXISTS customers_id_seq;
DROP SEQUENCE IF EXISTS bills_id_seq;

CREATE TABLE IF NOT EXISTS public.customers (
    id   integer   NOT NULL PRIMARY KEY,
    name character varying(100) NOT NULL,
    pin  integer   NOT NULL
);
CREATE SEQUENCE customers_id_seq START WITH 4 INCREMENT BY 1;


CREATE TABLE IF NOT EXISTS public.bills (
    id         integer NOT NULL PRIMARY KEY,
    number     character varying(100) NOT NULL,
    amount     integer NOT NULL,
    customerId integer NOT NULL
);
CREATE SEQUENCE bills_id_seq START WITH 7 INCREMENT BY 1;

ALTER TABLE public.bills
ADD CONSTRAINT fk_bill_customers FOREIGN KEY (customerId)
REFERENCES public.customers (id) ON UPDATE CASCADE ON DELETE RESTRICT;

CREATE TABLE IF NOT EXISTS public.actions (
    id serial NOT NULL,
    dateTime   timestamp without time zone NOT NULL,
    billId     integer   NOT NULL,
    customerId integer   NOT NULL,
    actionType integer   NOT NULL,
    amount     integer   NOT NULL,
    CONSTRAINT action_id_pk PRIMARY KEY (id)
);