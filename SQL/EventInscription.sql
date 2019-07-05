--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-07-05 11:19:55

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2857 (class 1262 OID 16862)
-- Name: EventInscription; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "EventInscription" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Belgium.1252' LC_CTYPE = 'French_Belgium.1252';


ALTER DATABASE "EventInscription" OWNER TO postgres;

\connect "EventInscription"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 16863)
-- Name: Activities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Activities" (
    id_activity integer NOT NULL,
    "nameActivity" character varying(50) NOT NULL,
    "descriptionActivity" character varying(300) NOT NULL,
    "startActivity" timestamp with time zone NOT NULL,
    "endActivity" timestamp with time zone NOT NULL,
    id_event integer NOT NULL
);


ALTER TABLE public."Activities" OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 16866)
-- Name: Activities_id_activity_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Activities_id_activity_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Activities_id_activity_seq" OWNER TO postgres;

--
-- TOC entry 2858 (class 0 OID 0)
-- Dependencies: 197
-- Name: Activities_id_activity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Activities_id_activity_seq" OWNED BY public."Activities".id_activity;


--
-- TOC entry 198 (class 1259 OID 16868)
-- Name: Events; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Events" (
    id_event integer NOT NULL,
    "eventName" character varying(50) NOT NULL,
    description character varying(300) NOT NULL,
    "dateDebut" timestamp with time zone NOT NULL,
    "dateFin" timestamp with time zone NOT NULL,
    id_person integer
);


ALTER TABLE public."Events" OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 16871)
-- Name: Events_id_event_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Events_id_event_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Events_id_event_seq" OWNER TO postgres;

--
-- TOC entry 2859 (class 0 OID 0)
-- Dependencies: 199
-- Name: Events_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Events_id_event_seq" OWNED BY public."Events".id_event;


--
-- TOC entry 203 (class 1259 OID 16901)
-- Name: Inscription_activity; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."Inscription_activity" (
    id_inscription bigint NOT NULL,
    id_person integer NOT NULL,
    id_activity integer NOT NULL
);


ALTER TABLE public."Inscription_activity" OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16899)
-- Name: Inscription_activity_id_inscription_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Inscription_activity_id_inscription_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Inscription_activity_id_inscription_seq" OWNER TO postgres;

--
-- TOC entry 2860 (class 0 OID 0)
-- Dependencies: 202
-- Name: Inscription_activity_id_inscription_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Inscription_activity_id_inscription_seq" OWNED BY public."Inscription_activity".id_inscription;


--
-- TOC entry 200 (class 1259 OID 16873)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id_person integer NOT NULL,
    "namePerson" character varying(50) NOT NULL,
    "firstnamePerson" character varying(50) NOT NULL,
    login character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 16876)
-- Name: persons_id_person_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.persons_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persons_id_person_seq OWNER TO postgres;

--
-- TOC entry 2861 (class 0 OID 0)
-- Dependencies: 201
-- Name: persons_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persons_id_person_seq OWNED BY public.persons.id_person;


--
-- TOC entry 2703 (class 2604 OID 16878)
-- Name: Activities id_activity; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities" ALTER COLUMN id_activity SET DEFAULT nextval('public."Activities_id_activity_seq"'::regclass);


--
-- TOC entry 2704 (class 2604 OID 16879)
-- Name: Events id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events" ALTER COLUMN id_event SET DEFAULT nextval('public."Events_id_event_seq"'::regclass);


--
-- TOC entry 2706 (class 2604 OID 16904)
-- Name: Inscription_activity id_inscription; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inscription_activity" ALTER COLUMN id_inscription SET DEFAULT nextval('public."Inscription_activity_id_inscription_seq"'::regclass);


--
-- TOC entry 2705 (class 2604 OID 16880)
-- Name: persons id_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons ALTER COLUMN id_person SET DEFAULT nextval('public.persons_id_person_seq'::regclass);


--
-- TOC entry 2844 (class 0 OID 16863)
-- Dependencies: 196
-- Data for Name: Activities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Activities" (id_activity, "nameActivity", "descriptionActivity", "startActivity", "endActivity", id_event) FROM stdin;
10	Yoga	Initiation au yoga	2019-07-19 10:30:00+02	2019-07-19 11:30:00+02	1
11	Tai Chi	Initiation au Tai Chi	2019-07-19 11:30:00+02	2019-07-19 12:30:00+02	1
\.


--
-- TOC entry 2846 (class 0 OID 16868)
-- Dependencies: 198
-- Data for Name: Events; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Events" (id_event, "eventName", description, "dateDebut", "dateFin", id_person) FROM stdin;
1	Détente et Bien-être	Initiation au Yoga et Tai Chi	2019-07-19 10:30:00+02	2019-07-19 12:45:00+02	1
2	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
3	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
4	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
5	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
7	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
8	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
9	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
10	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
11	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
12	newEvent	1234	2018-12-12 12:30:00+01	2018-12-12 13:30:00+01	1
\.


--
-- TOC entry 2851 (class 0 OID 16901)
-- Dependencies: 203
-- Data for Name: Inscription_activity; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."Inscription_activity" (id_inscription, id_person, id_activity) FROM stdin;
\.


--
-- TOC entry 2848 (class 0 OID 16873)
-- Dependencies: 200
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persons (id_person, "namePerson", "firstnamePerson", login, password) FROM stdin;
1	Franquinet	Delphine	delphine122	1234
2	Al-Sudani	Zahraa	Zahraa	1234
\.


--
-- TOC entry 2862 (class 0 OID 0)
-- Dependencies: 197
-- Name: Activities_id_activity_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Activities_id_activity_seq"', 11, true);


--
-- TOC entry 2863 (class 0 OID 0)
-- Dependencies: 199
-- Name: Events_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Events_id_event_seq"', 12, true);


--
-- TOC entry 2864 (class 0 OID 0)
-- Dependencies: 202
-- Name: Inscription_activity_id_inscription_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Inscription_activity_id_inscription_seq"', 1, false);


--
-- TOC entry 2865 (class 0 OID 0)
-- Dependencies: 201
-- Name: persons_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persons_id_person_seq', 2, true);


--
-- TOC entry 2708 (class 2606 OID 16882)
-- Name: Activities Activities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "Activities_pkey" PRIMARY KEY (id_activity);


--
-- TOC entry 2711 (class 2606 OID 16884)
-- Name: Events Events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id_event);


--
-- TOC entry 2716 (class 2606 OID 16906)
-- Name: Inscription_activity Inscription_activity_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "Inscription_activity_pkey" PRIMARY KEY (id_inscription);


--
-- TOC entry 2714 (class 2606 OID 16886)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id_person);


--
-- TOC entry 2717 (class 1259 OID 16918)
-- Name: fki_FKActivity; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKActivity" ON public."Inscription_activity" USING btree (id_activity);


--
-- TOC entry 2709 (class 1259 OID 16887)
-- Name: fki_FKEvent; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKEvent" ON public."Activities" USING btree (id_event);


--
-- TOC entry 2718 (class 1259 OID 16912)
-- Name: fki_FKPerson; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKPerson" ON public."Inscription_activity" USING btree (id_person);


--
-- TOC entry 2712 (class 1259 OID 16888)
-- Name: fki_FKPersonneResponsable; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKPersonneResponsable" ON public."Events" USING btree (id_person);


--
-- TOC entry 2722 (class 2606 OID 16913)
-- Name: Inscription_activity FKActivity; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "FKActivity" FOREIGN KEY (id_activity) REFERENCES public."Activities"(id_activity);


--
-- TOC entry 2719 (class 2606 OID 16889)
-- Name: Activities FKEvent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "FKEvent" FOREIGN KEY (id_event) REFERENCES public."Events"(id_event);


--
-- TOC entry 2721 (class 2606 OID 16907)
-- Name: Inscription_activity FKPerson; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "FKPerson" FOREIGN KEY (id_person) REFERENCES public.persons(id_person);


--
-- TOC entry 2720 (class 2606 OID 16894)
-- Name: Events FKPersonneResponsable; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "FKPersonneResponsable" FOREIGN KEY (id_person) REFERENCES public.persons(id_person);


-- Completed on 2019-07-05 11:19:56

--
-- PostgreSQL database dump complete
--

