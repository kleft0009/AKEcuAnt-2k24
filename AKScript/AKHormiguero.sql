-- database: ../database/AKHormigueroVirtual.sqlite
/*
CopyRight: 2k25 EPN
autor: kleft
fecha: Febrero
*/
DROP TABLE IF EXISTS Hormiga;

DROP TABLE IF EXISTS Catalogo;

CREATE TABLE
    Catalogo (
        IdCatalogo INTEGER PRIMARY KEY AUTOINCREMENT,
        IdCatalogoPadre INTEGER,
        Nombre VARCHAR(20) NOT NULL,
        Detalle VARCHAR(20) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCreacion DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime')),
        FechaModificacion DATETIME
    );

CREATE TABLE
    Hormiga (
        IdHormiga INTEGER PRIMARY KEY AUTOINCREMENT,
        IdCatalogoTipo INTEGER NOT NULL REFERENCES Catalogo (IdCatalogo),
        IdCatalogoSexo INTEGER NOT NULL REFERENCES Catalogo (IdCatalogo),
        IdCatalogoEstado INTEGER NOT NULL REFERENCES Catalogo (IdCatalogo),
        Nombre VARCHAR(20) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCreacion DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime')),
        FechaModificacion DATETIME
    );

DROP VIEW IF EXISTS vwHormigaTipo;

CREATE VIEW
    vwHormigaTipo AS
SELECT
    h.IdCatalogo,
    h.IdCatalogoPadre,
    h.Nombre,
    h.Detalle,
    h.Estado,
    h.FechaCreacion,
    h.FechaModificacion
FROM
    Catalogo p
    JOIN Catalogo h ON h.IdCatalogoPadre = p.IdCatalogo
WHERE
    p.Nombre = 'HormigaTipo';

DROP VIEW IF EXISTS vwHormigaSexo;

CREATE VIEW
    vwHormigaSexo AS
SELECT
    h.IdCatalogo,
    h.IdCatalogoPadre,
    h.Nombre,
    h.Detalle,
    h.Estado,
    h.FechaCreacion,
    h.FechaModificacion
FROM
    Catalogo p
    JOIN Catalogo h ON h.IdCatalogoPadre = p.IdCatalogo
WHERE
    p.Nombre = 'HormigaSexo';

DROP VIEW IF EXISTS vwHormigaEstado;

CREATE VIEW
    vwHormigaEstado AS
SELECT
    h.IdCatalogo,
    h.IdCatalogoPadre,
    h.Nombre,
    h.Detalle,
    h.Estado,
    h.FechaCreacion,
    h.FechaModificacion
FROM
    Catalogo p
    JOIN Catalogo h ON h.IdCatalogoPadre = p.IdCatalogo
WHERE
    p.Nombre = 'HormigaEstado';

INSERT INTO
    Catalogo (IdCatalogoPadre, Nombre, Detalle)
VALUES
    (NULL, 'HormigaTipo', 'tipos de hormigas'),
    (
        NULL,
        'HormigaSexo',
        'tipo de sexo de las hormigas'
    ),
    (NULL, 'HormigaEstado', 'estado de las hormigas');

INSERT INTO
    Catalogo (IdCatalogoPadre, Nombre, Detalle)
VALUES
    (1, 'Larva', 'tipos de hormigas'),
    (1, 'Soldado', 'tipos de hormigas'),
    (1, 'Zangano', 'tipos de hormigas'),
    (2, 'Macho', 'sexo de la hormiga'),
    (2, 'Hembra', 'sexo de la hormiga'),
    (3, 'Viva', 'estado de la hormiga'),
    (3, 'Muerta', 'estado de la hormiga');

INSERT INTO
    Hormiga (
        IdCatalogoTipo,
        IdCatalogoSexo,
        IdCatalogoEstado,
        Nombre
    )
VALUES
    (4, 7, 9, 'kleft');
