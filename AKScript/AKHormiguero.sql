-- database: ../AKdatabase/AKHormigueroVirtual.sqlite
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
        IdCatalogoIngestaNativa INTEGER NOT NULL REFERENCES Catalogo (IdCatalogo),
        IdCatalogoGenoAllimento INTEGER NOT NULL REFERENCES Catalogo (IdCatalogo),
        Nombre VARCHAR(20) NOT NULL,
        Estado VARCHAR(1) NOT NULL DEFAULT ('A'),
        FechaCreacion DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime')),
        FechaModificacion DATETIME
    );

DROP VIEW IF EXISTS vwHormiga;

DROP VIEW IF EXISTS vwHormigaTipo;

CREATE VIEW
    vwHormiga AS
SELECT
    h.IdCatalogo,
    h.Nombre,
    h.Detalle,
    h.Estado,
    h.FechaCreacion,
    h.FechaModificacion
FROM
    Catalogo p
    JOIN Catalogo h ON h.IdCatalogoPadre = p.IdCatalogo
WHERE
    p.Nombre IN (
        'HormigaTipo',
        'HormigaSexo',
        'HormigaEstado',
        'HormigaGenoAlimento',
        'HormigaIngestaNativa'
    );

INSERT INTO
    Catalogo (IdCatalogoPadre, Nombre, Detalle)
VALUES
    (NULL, 'HormigaTipo', 'tipos de hormigas'),
    (
        NULL,
        'HormigaSexo',
        'tipo de sexo de las hormigas'
    ),
    (NULL, 'HormigaEstado', 'estado de las hormigas'),
    (
        NULL,
        'HormigaIngestaNativa',
        'ingesta nativa de las hormigas'
    ),
    (
        NULL,
        'HormigaGenoAlimento',
        'genoAlimento de las hormigas'
    );

INSERT INTO
    Catalogo (IdCatalogoPadre, Nombre, Detalle)
VALUES
    (1, 'Larva', 'tipos de hormigas'),
    (1, 'Soldado', 'tipos de hormigas'),
    (2, 'Macho', 'sexo de la hormiga'),
    (2, 'Hembra', 'sexo de la hormiga'),
    (2, 'Asexual', 'sexo de la hormiga'),
    (3, 'Viva', 'estado de la hormiga'),
    (3, 'Muerta', 'estado de la hormiga'),
    (4, 'Carnivoro', 'ingesta nativa'),
    (5, 'XY', 'genoAlimento');

INSERT INTO
    Hormiga (
        IdCatalogoTipo,
        IdCatalogoSexo,
        IdCatalogoEstado,
        IdCatalogoIngestaNativa,
        IdCatalogoGenoAllimento,
        Nombre
    )
VALUES
    (7, 8, 11, 13, 14, 'kleft');
