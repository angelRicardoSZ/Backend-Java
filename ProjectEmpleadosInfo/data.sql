CREATE TABLE public.empleados
(
    id_empleado serial NOT NULL,
    primer_nombre character varying(50) NOT NULL,
    primer_apellido character varying(50) NOT NULL,
    segundo_apellido character varying(50) NOT NULL,
    ciudad character varying(50) NOT NULL,
    sexo character varying(50) NOT NULL,
    puesto character varying(50) NOT NULL,
    PRIMARY KEY (id_empleado)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE IF EXISTS public.empleados
    OWNER to postgres;


INSERT INTO public.info(
	id_empleado, numero_empleado, primer_apellido, segundo_apellido, ciudad, sexo, centro, puesto, clave_interbancaria, nombres_empleado)
	VALUES (1, 10, 'apellido1', 'apellido2', 'ciudad1', 'sexo1', 'centro1', 'puesto1', 1234, 'empleado1'),
	(2, 11, 'apellido2', 'apellido22', 'ciudad2', 'sexo1', 'centro2', 'puesto2', 5670, 'empleado2'),
	(3, 12, 'apellido3', 'apellido23', 'ciudad3', 'sexo2', 'centro3', 'puesto3', 1232, 'empleado3'),
	(4, 13, 'apellido4', 'apellido24', 'ciudad4', 'sexo1', 'centro4', 'puesto4', 3451,'empleado4'),
	(5, 14, 'apellido5', 'apellido25', 'ciudad5', 'sexo2', 'centro5', 'puesto5', 4512,'empleado5');