INSERT INTO public.users (email, name) VALUES ('teste@teste.com','teste'), ('teste2@teste.com','teste 2');
INSERT INTO public.item(name, quantity) VALUES ('ITEM 1', 10),('ITEM 2',20),('ITEM 3',30);
INSERT INTO public.orders (id, creation_date, quantity, fulfilled, pending, item_id, user_id) VALUES (1, '2022-07-04 08:09:48.000000', 1, 0, 0.0, 1, 1);
INSERT INTO public.stock_movements (id, creation_date, quantity, item_id) VALUES (1, '2022-07-04 11:46:38.000000', 10, 1);

alter sequence orders_id_seq restart with 2;
alter sequence stock_movements_id_seq restart with 2;



