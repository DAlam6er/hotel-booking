--CREATE DATABASE hotel_bookings;
CREATE DOMAIN gender CHAR(1)
    CHECK ( value IN ('F', 'M') );

CREATE DOMAIN marital_status CHAR(1)
    CHECK ( value IN ('S', 'M') );

CREATE TABLE room_number_of_places_type
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    capacity    INTEGER    NOT NULL,
    abbr        varchar(4) NOT NULL,
    description varchar(256)
);

INSERT INTO room_number_of_places_type (abbr, capacity, description)
VALUES ('SGL', 1, 'Предназначен для размещения одного человека и комплектуется одной кроватью'),
       ('DBL', 2, 'Предусматривает установку одной двуспальной кровати и проживание двух постояльцев'),
       ('TWIN', 2, 'Предусматривает установку двух односпальных кроватей и проживание двух постояльцев'),
       ('TRPL', 3,
        'Предназначен для проживания трех человек и укомплектован двумя возможными способами: тремя односпальными кроватями или одной двуспальной и одной односпальной'),
       ('QDPL', 4,
        'Предназначен для проживания четырёх человек и укомплектован двумя возможными способами: одной двуспальной и двумя односпальными кроватями или четырьмя односпальными');

CREATE TABLE room_age_type
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    abbr        CHAR(3) NOT NULL,
    description varchar(256)
);

INSERT INTO room_age_type(abbr, description)
VALUES ('ADL', 'для постояльцев старше 12 лет'),
       ('CHD', 'для постояльцев, не достигших 12 лет'),
       ('INF', 'для детей в пределах 2 лет');

CREATE TABLE room_interior_type
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    abbr        varchar(10) NOT NULL,
    description text
);

INSERT INTO room_interior_type(abbr, description)
VALUES ('STD', 'Типовой номер, обычно рассчитанный на 1-2 человек с аналогичным количеством кроватей'),
       ('SUP',
        'Отличается от стандартного номера присутствием какого –либо бонуса – вида из окна, уровня интерьера, большей площадью и т.д.'),
       ('BDR',
        'Состоит из двух комнат. Кровать или кровати устанавливаются во второй, которая используется как спальная. Первая комната выполняет роль гостиной или зала'),
       ('APT',
        'Номер отличается присутствием полноценной кухни или кухонной зоны, которая предназначена для приготовления или разогревания пищи. Допускается присутствие одной или даже нескольких спален'),
       ('Studio',
        'Однокомнатная разновидность апартаментов. Обычно имеет очень небольшую – в пределах 20 кв. м. – площадь'),
       ('Suite',
        'Двух- или трехкомнатный номер площадью от 35 кв. м. Отличается улучшенным уровнем дизайна и повышенной комфортностью проживания. В том числе – за счет комплектования различным дополнительным оборудованием, например, джакузи, системой климат-контроля, вторым санузлом и т.д'),
       ('Duplex',
        'Наименование гостиничного номера наглядно показывает главную особенность – расположение на разных этажах отеля');

CREATE TABLE room_bed_type
(
    id          INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    abbr        varchar(15) NOT NULL,
    description varchar(256)
);

INSERT INTO room_bed_type(abbr, description)
VALUES ('single', 'одноместная c типовым размером матраца 96*190 см'),
       ('double', 'двухместная (135-190 см)'),
       ('king-size bed', 'двухместная, ширина которой превышает 180 см'),
       ('extra bed',
        'одноместная для взрослого, которая устанавливается в добавление к основным (альтернативный вариант – раскладушка)'),
       ('crib', 'одноместная (60*120 см)');

CREATE TABLE guest
(
    id               BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    last_name        VARCHAR(128)   NOT NULL,
    first_name       VARCHAR(128)   NOT NULL,
    surname          varchar(128),
    birthday         DATE           NOT NULL,
    gender           gender         NOT NULL,
    address          VARCHAR(256)   NOT NULL,
    pass_no          VARCHAR(32)    NOT NULL,
    marital_status   marital_status NOT NULL,
    check_in         timestamp      NOT NULL,
    check_out        timestamp,
    duration_of_stay SMALLINT
);

INSERT INTO guest (last_name, first_name, surname, birthday, gender, address, pass_no, marital_status, check_in,
                   check_out, duration_of_stay)
VALUES ('Химченко', 'Анисим', 'Филиппович', '1980-09-27', 'M',
        '442494, г. Североуральск, ул. Прокудинский пер, дом 20, квартира 803', '', 'S', '2023-11-15 14:00:00',
        '2023-11-16 12:00:00', '1'),
       ('Смолов', 'Панкратий', 'Георгиевич', '2002-01-16', 'M',
        '143988, г. Миайлово, ул. Тверская-Ямская 2-Я, дом 48, квартира 892', '', 'M', '2023-11-15 14:00:00', null,
        null);

CREATE TABLE room
(
    id                         CHAR(5) PRIMARY KEY,
    floor                      SMALLINT      NOT NULL CHECK ( floor > 0),
    room_number_of_places_type INTEGER       NOT NULL REFERENCES room_number_of_places_type (id),
    room_age_type              INTEGER       NOT NULL REFERENCES room_age_type (id),
    room_interior_type         INTEGER       NOT NULL REFERENCES room_interior_type (id),
    room_bed_type              INTEGER       NOT NULL REFERENCES room_bed_type (id),
    occupied                   SMALLINT      NOT NULL DEFAULT 0 CHECK ( occupied >= 0 ),
    employee_code              BIGINT        NOT NULL,
    cost                       NUMERIC(8, 2) NOT NULL
);

INSERT INTO room(id, floor, room_number_of_places_type, room_age_type, room_interior_type, room_bed_type, occupied,
                 employee_code,
                 cost)
VALUES ('STD01', 2, 1, 1, 1, 1, 1, 4, 1500),
       ('DBL02', 2, 2, 1, 1, 2, 1, 5, 2000),
       ('DBL03', 2, 2, 1, 1, 2, 0, 5, 2000);

CREATE TABLE application_status
(
    id     INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    status varchar(128) NOT NULL
);

INSERT INTO application_status(status)
VALUES ('PENDING'),
       ('APPROVED'),
       ('REJECTED');

CREATE TABLE booking_application
(
    id                         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    room_number_of_places_type INTEGER NOT NULL REFERENCES room_number_of_places_type (id),
    room_age_type              INTEGER NOT NULL REFERENCES room_age_type (id),
    room_interior_type         INTEGER NOT NULL REFERENCES room_interior_type (id),
    room_bed_type              INTEGER REFERENCES room_bed_type (id),
    application_status         INTEGER NOT NULL REFERENCES application_status (id) DEFAULT 1
);

INSERT INTO booking_application(room_number_of_places_type, room_age_type, room_interior_type, room_bed_type,
                                application_status)
VALUES (1, 1, 1, 1, 2),
       (2, 1, 1, 2, 2);

CREATE TABLE payment_status
(
    id     INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    status varchar(128) NOT NULL
);

INSERT INTO payment_status(status)
VALUES ('NOT PAYED'),
       ('PAYED');

CREATE TABLE invoice
(
    id             BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    guest_id       BIGINT  NOT NULL REFERENCES guest (id),
    booking_id BIGINT NOT NULL REFERENCES booking_application(id),
    payment_status INTEGER NOT NULL REFERENCES payment_status (id) DEFAULT 1
);

INSERT INTO invoice(guest_id, booking_id, payment_status)
VALUES (4, 1, 1),
       (5, 2, 1);

CREATE TABLE room_allocation
(
    guest_id BIGINT  NOT NULL REFERENCES guest (id),
    room     CHAR(5) NOT NULL REFERENCES room (id),
    PRIMARY KEY (guest_id, room)
);

INSERT INTO room_allocation(guest_id, room)
VALUES (4, 'STD01'),
       (5, 'DBL02');

CREATE TABLE service
(
    id           INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    service_name varchar(256)  NOT NULL,
    cost         numeric(8, 2) NOT NULL
);

INSERT INTO service(service_name, cost)
VALUES ('Дополнительная специальная уборка номера для удаления запаха', 5000),
       ('Дополнительная уборка сан.узла  в номере со сменой комплекта полотенец', 500),
       ('Дополн. уборка номера со сменой комплекта полотенец и пост. белья', 1000),
       ('Дополнительный комплект полотенец', 200),
       ('Дополнительный комплект постел. белья', 500),
       ('Доп. комплект полотенец и постел.белья', 600),
       ('Дополнительный завтрак', 800),
       ('Тапочки', 100),
       ('Аренда халата', 200),
       ('Набор зубная щётка, паста', 80),
       ('Набор бритвенный', 80);

CREATE TABLE position
(
    id            INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    position_name varchar(128)  NOT NULL,
    salary        numeric(8, 2) NOT NULL
);

INSERT INTO position(position_name, salary)
VALUES ('управляющий отелем', 139000),
       ('менеджер по размещению', 40000),
       ('горничная', 30000),
       ('ресторатор', 46000),
       ('официант', 71000),
       ('бармен', 64000),
       ('повар', 45000),
       ('швейцар', 41000);

CREATE TABLE employee
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    last_name    VARCHAR(128) NOT NULL,
    first_name   VARCHAR(128) NOT NULL,
    surname      varchar(128),
    address      VARCHAR(256) NOT NULL,
    phone_number VARCHAR(16)  NOT NULL,
    position_id  INTEGER REFERENCES position (id)
);

INSERT INTO employee(last_name, first_name, surname, address, phone_number, position_id)
VALUES ('Николаева', 'Лунара', 'Станиславовна', '', '', 1),
       ('Беляева', 'Аврора', 'Валерьевна', '', '', 2),
       ('Кириллова', 'Милиса', 'Леонидовна', '', '', 2),
       ('Кононова', 'Дарья', 'Лаврентьевна', '', '', 3),
       ('Чернова', 'Николь', 'Ефимовна', '', '', 3),
       ('Лыткина', 'Сима', 'Валентиновна', '', '', 3),
       ('Соколова', 'Илена', 'Данииловна', '', '', 3),
       ('Дорофеева', 'Фелиция', 'Лаврентьевна', '', '', 3),
       ('Суханова', 'Магда', 'Филипповна', '', '', 4),
       ('Ширяева', 'Лидия', 'Владимировна', '', '', 4),
       ('Крылов', 'Любомир', 'Вячеславович', '', '', 5),
       ('Сысоев', 'Варлам', 'Оскарович', '', '', 5),
       ('Антонов', 'Людвиг', 'Георгиевич', '', '', 5),
       ('Фёдоров', 'Евдоким', 'Ильяович', '', '', 5),
       ('Титов', 'Парамон', 'Витальевич', '', '', 6),
       ('Пестов', 'Мирослав', 'Тарасович', '', '', 6),
       ('Ситников', 'Иван', 'Матвеевич', '', '', 7),
       ('Зайцев', 'Валерий', 'Альбертович', '', '', 7),
       ('Вишняков', 'Май', 'Иванович', '', '', 7),
       ('Пономарёв', 'Кондрат', 'Михайлович', '', '', 8),
       ('Михайлов', 'Исаак', 'Альбертович', '', '', 8),
       ('Колобов', 'Георгий', 'Онисимович', '', '', 8);

CREATE TABLE service_provided
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    guest_id    BIGINT        NOT NULL REFERENCES guest (id),
    service_id  INTEGER       NOT NULL REFERENCES service (id),
    cost        numeric(8, 2) NOT NULL,
    employee_id BIGINT        NOT NULL REFERENCES employee (id)
);

CREATE TABLE room_service
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    employee_id BIGINT  NOT NULL REFERENCES employee (id),
    room_id     CHAR(5) NOT NULL REFERENCES room (id)
);

CREATE TABLE weekdays
(
    id      INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    weekday CHAR(3)
);

INSERT INTO weekdays(weekday)
VALUES ('MON'),
       ('TUE'),
       ('WED'),
       ('THU'),
       ('FRI'),
       ('SAT'),
       ('SUN');

CREATE TABLE duty_list
(
    id          BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    employee_id BIGINT    NOT NULL REFERENCES employee (id),
    weekday_id  INTEGER   NOT NULL REFERENCES weekdays (id),
    work_start  timestamp NOT NULL,
    work_end    timestamp NOT NULL
);