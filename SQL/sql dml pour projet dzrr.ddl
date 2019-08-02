CREATE TABLE Activities (
    id_activity SERIAL PRIMARY KEY,
    nameActivity varchar(50) NOT NULL,
    descriptionActivity varchar(300) NOT NULL,
    startActivity timestamp with time zone NOT NULL,
    endActivity timestamp with time zone NOT NULL,
    id_event int NOT NULL,
    CONSTRAINT checktimerelation CHECK (((endActivity - startActivity) >= '00:00:00'::interval)),
    CONSTRAINT UK_nameActivity_startActivity UNIQUE (nameActivity, startActivity)

);

CREATE TABLE Events (
    id_event serial PRIMARY KEY ,
    eventName varchar(50) NOT NULL,
    description varchar(300) NOT NULL,
    startDate timestamp with time zone NOT NULL,
    endDate timestamp with time zone NOT NULL,
    id_person int NOT NULL,
    eventPlace varchar(200) NOT NULL,
    CONSTRAINT checktimerelation CHECK (((endDate - startDate) >= '00:00:00'::interval)),
    CONSTRAINT UK_eventName_startDate_eventPlace UNIQUE (eventName, startDate, eventPlace)
);

CREATE TABLE Inscription_activity (
    id_inscription serial PRIMARY KEY ,
    id_person int NOT NULL,
    id_activity int NOT NULL
);

CREATE TABLE persons (
    id_person serial PRIMARY KEY ,
    namePerson varchar(50) NOT NULL,
    firstnamePerson varchar(50) NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
    password varchar(50) NOT NUL