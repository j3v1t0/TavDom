CREATE TABLE facturacion.cliente
(
  codcliente integer NOT NULL,
  nombres character varying(255),
  apellidos character varying(255),
  negocio text,
  cedula VARCHAR(13),
  rnc VARCHAR(11),
  telefono VARCHAR(14),
  celular VARCHAR(14),
  credito integer,
  estatus_negocio char,
  direccion character varying(255),
  
  CONSTRAINT cliente_pk PRIMARY KEY (codcliente)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE facturacion.producto
(
  codproducto integer NOT NULL,
  nombreproducto text,
  precioventa numeric(19,2),
  stockminimo integer,
  stockactual integer,
  codbarra text,
  CONSTRAINT pk_producto PRIMARY KEY (codproducto)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE facturacion.vendedor
(
  codvendedor integer NOT NULL,
  nombres text,
  apellidos text,
  dui text,
  celular text,
  direccion text,
  CONSTRAINT pk_vendedor PRIMARY KEY (codvendedor)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE facturacion.factura
(
  codfactura integer NOT NULL,
  numerofactura integer,
  codvendedor integer,
  codcliente integer,
  totalventa numeric(19,2),
  fecharegistro date,
  CONSTRAINT pk_factura PRIMARY KEY (codfactura),
  CONSTRAINT fk_vendedor FOREIGN KEY (codvendedor)
      REFERENCES facturacion.vendedor (codvendedor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
      CONSTRAINT fk_cliente FOREIGN KEY (codcliente)
      REFERENCES facturacion.cliente (codcliente) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE TABLE facturacion.detallefactura
(
  coddetallefactura integer NOT NULL,
  codfactura integer,
  codproducto integer,
  codbarra text,
  nombreproducto text,
  cantidad integer,
  precioventa numeric(19,2),
  total numeric(19,2),
  CONSTRAINT pk_detallfactura PRIMARY KEY (coddetallefactura),
  CONSTRAINT fk_factura FOREIGN KEY (codfactura)
      REFERENCES facturacion.factura (codfactura) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_producto FOREIGN KEY (codproducto)
      REFERENCES facturacion.producto (codproducto) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

ALTER TABLE facturacion.cliente ADD CONSTRAINT chk_estatus_negocio CHECK (estatus_negocio IN ('A','P'));

CREATE TABLE facturacion.tipos_comprobantes(
codtipo_comprobante integer not null,
clasificacion VARCHAR(2),
tipo_comprobante text,
CONSTRAINT pk_codtipo_comprobante PRIMARY KEY (codtipo_comprobante)
);

ALTER TABLE facturacion.cliente ADD COLUMN codtipo_comprobante integer;
ALTER TABLE facturacion.cliente ADD CONSTRAINT fk_codtipo_comprobante FOREIGN KEY (codtipo_comprobante) REFERENCES facturacion.tipos_comprobantes(codtipo_comprobante);