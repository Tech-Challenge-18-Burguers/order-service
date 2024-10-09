-- public.customers definition

CREATE TABLE public.customers (
	id uuid NOT NULL,
	created_at timestamptz(6) NULL,
	document_number varchar(255) NULL,
	document_type varchar(255) NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	updated_at timestamptz(6) NULL,
	CONSTRAINT customers_pkey PRIMARY KEY (id)
);

-- public.products definition

CREATE TABLE public.products (
	id bigserial NOT NULL,
	category int4 NULL,
	description varchar(255) NULL,
	image varchar(255) NULL,
	"name" varchar(255) NULL,
	price numeric(38, 2) NULL,
	CONSTRAINT products_pkey PRIMARY KEY (id)
);

-- public.orders definition

CREATE TABLE public.orders (
	id bigserial NOT NULL,
	amount numeric(38, 2) NULL,
	customer_id uuid NULL,
	created_at timestamptz(6) NULL,
	status int4 NULL,
	updated_at timestamptz(6) NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);

-- public.order_items definition

CREATE TABLE public.order_items (
	id bigserial NOT NULL,
	category int4 NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	observation varchar(255) NULL,
	price numeric(38, 2) NULL,
	product_id int8 NULL,
	quantity int4 NULL,
	order_id int8 NULL,
	CONSTRAINT order_items_pkey PRIMARY KEY (id)
);


-- public.order_items foreign keys

ALTER TABLE public.order_items ADD CONSTRAINT fk_order_item_order_id
FOREIGN KEY (order_id) REFERENCES public.orders(id);
