--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

-- Started on 2019-07-01 15:41:35

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
-- TOC entry 201 (class 1259 OID 16833)
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
-- TOC entry 200 (class 1259 OID 16831)
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
-- TOC entry 2842 (class 0 OID 0)
-- Dependencies: 200
-- Name: Activities_id_activity_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Activities_id_activity_seq" OWNED BY public."Activities".id_activity;


--
-- TOC entry 197 (class 1259 OID 16811)
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
-- TOC entry 196 (class 1259 OID 16809)
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
-- TOC entry 2843 (class 0 OID 0)
-- Dependencies: 196
-- Name: Events_id_event_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Events_id_event_seq" OWNED BY public."Events".id_event;


--
-- TOC entry 199 (class 1259 OID 16819)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id_person integer NOT NULL,
    "namePerson" character varying(50) NOT NULL,
    "firstnamePerson" character varying(50) NOT NULL
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16817)
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
-- TOC entry 2844 (class 0 OID 0)
-- Dependencies: 198
-- Name: persons_id_person_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persons_id_person_seq OWNED BY public.persons.id_person;


--
-- TOC entry 2699 (class 2604 OID 16836)
-- Name: Activities id_activity; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities" ALTER COLUMN id_activity SET DEFAULT nextval('public."Activities_id_activity_seq"'::regclass);


--
-- TOC entry 2697 (class 2604 OID 16814)
-- Name: Events id_event; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events" ALTER COLUMN id_event SET DEFAULT nextval('public."Events_id_event_seq"'::regclass);


--
-- TOC entry 2698 (class 2604 OID 16822)
-- Name: persons id_person; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons ALTER COLUMN id_person SET DEFAULT nextval('public.persons_id_person_seq'::regclass);


--
-- TOC entry 2836 (class 0 OID 16833)
-- Dependencies: 201
-- Data for Name: Activities; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Activities" VALUES (10, 'Yoga', 'Initiation au yoga', '2019-07-19 10:30:00+02', '2019-07-19 11:30:00+02', 1);
INSERT INTO public."Activities" VALUES (11, 'Tai Chi', 'Initiation au Tai Chi', '2019-07-19 11:30:00+02', '2019-07-19 12:30:00+02', 1);


--
-- TOC entry 2832 (class 0 OID 16811)
-- Dependencies: 197
-- Data for Name: Events; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public."Events" VALUES (1, 'Détente et Bien-être', 'Initiation au Yoga et Tai Chi', '2019-07-19 10:30:00+02', '2019-07-19 12:45:00+02', 1);


--
-- TOC entry 2834 (class 0 OID 16819)
-- Dependencies: 199
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.persons VALUES (1, 'Franquinet', 'Delphine');
INSERT INTO public.persons VALUES (2, 'Al-Sudani', 'Zahraa');


--
-- TOC entry 2845 (class 0 OID 0)
-- Dependencies: 200
-- Name: Activities_id_activity_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Activities_id_activity_seq"', 11, true);


--
-- TOC entry 2846 (class 0 OID 0)
-- Dependencies: 196
-- Name: Events_id_event_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Events_id_event_seq"', 1, true);


--
-- TOC entry 2847 (class 0 OID 0)
-- Dependencies: 198
-- Name: persons_id_person_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persons_id_person_seq', 2, true);


--
-- TOC entry 2706 (class 2606 OID 16838)
-- Name: Activities Activities_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "Activities_pkey" PRIMARY KEY (id_activity);


--
-- TOC entry 2701 (class 2606 OID 16816)
-- Name: Events Events_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id_event);


--
-- TOC entry 2704 (class 2606 OID 16824)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id_person);


--
-- TOC entry 2707 (class 1259 OID 16855)
-- Name: fki_FKEvent; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKEvent" ON public."Activities" USING btree (id_event);


--
-- TOC entry 2702 (class 1259 OID 16830)
-- Name: fki_FKPersonneResponsable; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX "fki_FKPersonneResponsable" ON public."Events" USING btree (id_person);


--
-- TOC entry 2709 (class 2606 OID 16850)
-- Name: Activities FKEvent; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "FKEvent" FOREIGN KEY (id_event) REFERENCES public."Events"(id_event);


--
-- TOC entry 2708 (class 2606 OID 16825)
-- Name: Events FKPersonneResponsable; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "FKPersonneResponsable" FOREIGN KEY (id_person) REFERENCES public.persons(id_person);


-- Completed on 2019-07-01 15:41:35

--
-- PostgreSQL database dump complete
--

