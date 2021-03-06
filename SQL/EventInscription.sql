PGDMP         !                w           EventInscription    11.2    11.2 *    -           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false            .           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false            /           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false            0           1262    16862    EventInscription    DATABASE     �   CREATE DATABASE "EventInscription" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'French_Belgium.1252' LC_CTYPE = 'French_Belgium.1252';
 "   DROP DATABASE "EventInscription";
             postgres    false            �            1259    16863 
   Activities    TABLE     �  CREATE TABLE public."Activities" (
    id_activity integer NOT NULL,
    "nameActivity" character varying(50) NOT NULL,
    "descriptionActivity" character varying(300) NOT NULL,
    "startActivity" timestamp with time zone NOT NULL,
    "endActivity" timestamp with time zone NOT NULL,
    id_event integer NOT NULL,
    CONSTRAINT checktimerelation CHECK ((("endActivity" - "startActivity") >= '00:00:00'::interval))
);
     DROP TABLE public."Activities";
       public         postgres    false            �            1259    16866    Activities_id_activity_seq    SEQUENCE     �   CREATE SEQUENCE public."Activities_id_activity_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public."Activities_id_activity_seq";
       public       postgres    false    196            1           0    0    Activities_id_activity_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public."Activities_id_activity_seq" OWNED BY public."Activities".id_activity;
            public       postgres    false    197            �            1259    16868    Events    TABLE     �  CREATE TABLE public."Events" (
    id_event integer NOT NULL,
    "eventName" character varying(50) NOT NULL,
    description character varying(300) NOT NULL,
    "dateDebut" timestamp with time zone NOT NULL,
    "dateFin" timestamp with time zone NOT NULL,
    id_person integer NOT NULL,
    place character varying(200) NOT NULL,
    CONSTRAINT checktimerelation CHECK ((("dateFin" - "dateDebut") >= '00:00:00'::interval))
);
    DROP TABLE public."Events";
       public         postgres    false            �            1259    16871    Events_id_event_seq    SEQUENCE     �   CREATE SEQUENCE public."Events_id_event_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public."Events_id_event_seq";
       public       postgres    false    198            2           0    0    Events_id_event_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public."Events_id_event_seq" OWNED BY public."Events".id_event;
            public       postgres    false    199            �            1259    16901    Inscription_activity    TABLE     �   CREATE TABLE public."Inscription_activity" (
    id_inscription bigint NOT NULL,
    id_person integer NOT NULL,
    id_activity integer NOT NULL
);
 *   DROP TABLE public."Inscription_activity";
       public         postgres    false            �            1259    16899 '   Inscription_activity_id_inscription_seq    SEQUENCE     �   CREATE SEQUENCE public."Inscription_activity_id_inscription_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 @   DROP SEQUENCE public."Inscription_activity_id_inscription_seq";
       public       postgres    false    203            3           0    0 '   Inscription_activity_id_inscription_seq    SEQUENCE OWNED BY     w   ALTER SEQUENCE public."Inscription_activity_id_inscription_seq" OWNED BY public."Inscription_activity".id_inscription;
            public       postgres    false    202            �            1259    16873    persons    TABLE     �   CREATE TABLE public.persons (
    id_person integer NOT NULL,
    "namePerson" character varying(50) NOT NULL,
    "firstnamePerson" character varying(50) NOT NULL,
    email character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);
    DROP TABLE public.persons;
       public         postgres    false            �            1259    16876    persons_id_person_seq    SEQUENCE     �   CREATE SEQUENCE public.persons_id_person_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.persons_id_person_seq;
       public       postgres    false    200            4           0    0    persons_id_person_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.persons_id_person_seq OWNED BY public.persons.id_person;
            public       postgres    false    201            �
           2604    16878    Activities id_activity    DEFAULT     �   ALTER TABLE ONLY public."Activities" ALTER COLUMN id_activity SET DEFAULT nextval('public."Activities_id_activity_seq"'::regclass);
 G   ALTER TABLE public."Activities" ALTER COLUMN id_activity DROP DEFAULT;
       public       postgres    false    197    196            �
           2604    16879    Events id_event    DEFAULT     v   ALTER TABLE ONLY public."Events" ALTER COLUMN id_event SET DEFAULT nextval('public."Events_id_event_seq"'::regclass);
 @   ALTER TABLE public."Events" ALTER COLUMN id_event DROP DEFAULT;
       public       postgres    false    199    198            �
           2604    16904 #   Inscription_activity id_inscription    DEFAULT     �   ALTER TABLE ONLY public."Inscription_activity" ALTER COLUMN id_inscription SET DEFAULT nextval('public."Inscription_activity_id_inscription_seq"'::regclass);
 T   ALTER TABLE public."Inscription_activity" ALTER COLUMN id_inscription DROP DEFAULT;
       public       postgres    false    202    203    203            �
           2604    16880    persons id_person    DEFAULT     v   ALTER TABLE ONLY public.persons ALTER COLUMN id_person SET DEFAULT nextval('public.persons_id_person_seq'::regclass);
 @   ALTER TABLE public.persons ALTER COLUMN id_person DROP DEFAULT;
       public       postgres    false    201    200            #          0    16863 
   Activities 
   TABLE DATA               �   COPY public."Activities" (id_activity, "nameActivity", "descriptionActivity", "startActivity", "endActivity", id_event) FROM stdin;
    public       postgres    false    196   .3       %          0    16868    Events 
   TABLE DATA               p   COPY public."Events" (id_event, "eventName", description, "dateDebut", "dateFin", id_person, place) FROM stdin;
    public       postgres    false    198   �3       *          0    16901    Inscription_activity 
   TABLE DATA               X   COPY public."Inscription_activity" (id_inscription, id_person, id_activity) FROM stdin;
    public       postgres    false    203   C4       '          0    16873    persons 
   TABLE DATA               ^   COPY public.persons (id_person, "namePerson", "firstnamePerson", email, password) FROM stdin;
    public       postgres    false    200   m4       5           0    0    Activities_id_activity_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public."Activities_id_activity_seq"', 18, true);
            public       postgres    false    197            6           0    0    Events_id_event_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public."Events_id_event_seq"', 27, true);
            public       postgres    false    199            7           0    0 '   Inscription_activity_id_inscription_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public."Inscription_activity_id_inscription_seq"', 18, true);
            public       postgres    false    202            8           0    0    persons_id_person_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.persons_id_person_seq', 5, true);
            public       postgres    false    201            �
           2606    16882    Activities Activities_pkey 
   CONSTRAINT     e   ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "Activities_pkey" PRIMARY KEY (id_activity);
 H   ALTER TABLE ONLY public."Activities" DROP CONSTRAINT "Activities_pkey";
       public         postgres    false    196            �
           2606    16884    Events Events_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "Events_pkey" PRIMARY KEY (id_event);
 @   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "Events_pkey";
       public         postgres    false    198            �
           2606    16906 .   Inscription_activity Inscription_activity_pkey 
   CONSTRAINT     |   ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "Inscription_activity_pkey" PRIMARY KEY (id_inscription);
 \   ALTER TABLE ONLY public."Inscription_activity" DROP CONSTRAINT "Inscription_activity_pkey";
       public         postgres    false    203            �
           2606    17007    persons UniqueEmail 
   CONSTRAINT     Q   ALTER TABLE ONLY public.persons
    ADD CONSTRAINT "UniqueEmail" UNIQUE (email);
 ?   ALTER TABLE ONLY public.persons DROP CONSTRAINT "UniqueEmail";
       public         postgres    false    200            �
           2606    16920 '   Inscription_activity inscription_unique 
   CONSTRAINT     v   ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT inscription_unique UNIQUE (id_person, id_activity);
 S   ALTER TABLE ONLY public."Inscription_activity" DROP CONSTRAINT inscription_unique;
       public         postgres    false    203    203            �
           2606    16886    persons persons_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id_person);
 >   ALTER TABLE ONLY public.persons DROP CONSTRAINT persons_pkey;
       public         postgres    false    200            �
           1259    16918    fki_FKActivity    INDEX     Z   CREATE INDEX "fki_FKActivity" ON public."Inscription_activity" USING btree (id_activity);
 $   DROP INDEX public."fki_FKActivity";
       public         postgres    false    203            �
           1259    16887    fki_FKEvent    INDEX     J   CREATE INDEX "fki_FKEvent" ON public."Activities" USING btree (id_event);
 !   DROP INDEX public."fki_FKEvent";
       public         postgres    false    196            �
           1259    16912    fki_FKPerson    INDEX     V   CREATE INDEX "fki_FKPerson" ON public."Inscription_activity" USING btree (id_person);
 "   DROP INDEX public."fki_FKPerson";
       public         postgres    false    203            �
           1259    16888    fki_FKPersonneResponsable    INDEX     U   CREATE INDEX "fki_FKPersonneResponsable" ON public."Events" USING btree (id_person);
 /   DROP INDEX public."fki_FKPersonneResponsable";
       public         postgres    false    198            �
           2606    17008    Inscription_activity FKActivity    FK CONSTRAINT     �   ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "FKActivity" FOREIGN KEY (id_activity) REFERENCES public."Activities"(id_activity) ON DELETE CASCADE;
 M   ALTER TABLE ONLY public."Inscription_activity" DROP CONSTRAINT "FKActivity";
       public       postgres    false    2711    196    203            �
           2606    17013    Activities FKEvent    FK CONSTRAINT     �   ALTER TABLE ONLY public."Activities"
    ADD CONSTRAINT "FKEvent" FOREIGN KEY (id_event) REFERENCES public."Events"(id_event) ON DELETE CASCADE;
 @   ALTER TABLE ONLY public."Activities" DROP CONSTRAINT "FKEvent";
       public       postgres    false    2714    196    198            �
           2606    16907    Inscription_activity FKPerson    FK CONSTRAINT     �   ALTER TABLE ONLY public."Inscription_activity"
    ADD CONSTRAINT "FKPerson" FOREIGN KEY (id_person) REFERENCES public.persons(id_person);
 K   ALTER TABLE ONLY public."Inscription_activity" DROP CONSTRAINT "FKPerson";
       public       postgres    false    2719    200    203            �
           2606    16894    Events FKPersonneResponsable    FK CONSTRAINT     �   ALTER TABLE ONLY public."Events"
    ADD CONSTRAINT "FKPersonneResponsable" FOREIGN KEY (id_person) REFERENCES public.persons(id_person);
 J   ALTER TABLE ONLY public."Events" DROP CONSTRAINT "FKPersonneResponsable";
       public       postgres    false    2719    198    200            #   h   x�34���OO����,�L,���SH,U�	Z���Z*XXh�"���9CR�K�8��@�̼tb�0B1�l�13,��a`�lF� �0�      %   �   x�3�t9��$5�$U!�D�)35O�𪒢TNϼ̒�Ē��<��R����D����L�LN#CK]s]CKC+c+m#a#+S��!gpA"���WiJ>g&��,��B���@��f�#�9}2S�S�b���� ~�1"      *      x�34�4�44�2� �\1z\\\ !'�      '   d   x�3�t+J�+,��K-�tI�)� �8S���������TNC#c.#N���ҔļLΨČ��D�*0��Є�=3#���8��739#5'�3B������� ��*�     