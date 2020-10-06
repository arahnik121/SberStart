DROP table if exists card cascade;
DROP table if exists account cascade;
DROP table if exists client cascade;

CREATE table client (
id UUID,
name text,
surname text
);

CREATE table account (
id UUID,
number int,
client_id UUID,
foreign key (client_id) references client(id)
);

CREATE table card (
id UUID,
number int,
account_id UUID,
foreign key (account_id) references account(id)
);


INSERT INTO client(id, name, surname) VALUES('7ae53c7b-0002-483e-897d-3e21f8ca16eb', 'vitalii', 'eremin');
INSERT INTO account(id, client_id, number) VALUES('14aa7075-7077-49d6-a621-9d91fa65ef79', '7ae53c7b-0002-483e-897d-3e21f8ca16eb', 321);
INSERT INTO card(id, number, account_id) VALUES('4c0f8a96-e193-4a92-bd34-9dcfb02c276a', 123, '14aa7075-7077-49d6-a621-9d91fa65ef79');