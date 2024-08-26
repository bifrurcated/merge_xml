insert into validation_process
values
(
	gen_random_uuid(),
	'c:/Users/Ivanov/test/',
	true,
	'с:/Users/Ivanov/test/total',
	now()
),
(
	gen_random_uuid(),
	'c:/Users/Sidorov/test/',
	true,
	'с:/Users/Sidorov/test/total',
	now() + interval '1 day'
),
(
	gen_random_uuid(),
	'c:/Users/Vasechkin/test/',
	true, 'с:/Users/Vasechkin/test/total',
	now() + interval '2 day'
);

insert into validation_file_history
values
(
	gen_random_uuid(),
	'Ivanov1.txt',
	'c:/Users/Ivanov/test/Ivanov1.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date limit 1),
	(select id from validation_process order by validation_process_date limit 1)
),
(
	gen_random_uuid(),
	'Ivanov2.txt',
	'c:/Users/Ivanov/test/Ivanov2.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date limit 1),
	(select id from validation_process order by validation_process_date limit 1)
),
(
	gen_random_uuid(),
	'Ivanov3.txt',
	'c:/Users/Ivanov/test/Ivanov3.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date limit 1),
	(select id from validation_process order by validation_process_date limit 1)
),

(
	gen_random_uuid(),
	'Sidorov1.txt',
	'c:/Users/Sidorov/test/Sidorov1.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 1 limit 1),
	(select id from validation_process order by validation_process_date offset 1 limit 1)
),
(
	gen_random_uuid(),
	'Sidorov2.txt',
	'c:/Users/Sidorov/test/Sidorov2.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 1 limit 1),
	(select id from validation_process order by validation_process_date offset 1 limit 1)
),
(
	gen_random_uuid(),
	'Sidorov3.txt',
	'c:/Users/Sidorov/test/Sidorov3.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 1 limit 1),
	(select id from validation_process order by validation_process_date offset 1 limit 1)
),

(
	gen_random_uuid(),
	'Vasechkin1.txt',
	'c:/Users/Vasechkin/test/Vasechkin1.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 2 limit 1),
	(select id from validation_process order by validation_process_date offset 2 limit 1)
),
(
	gen_random_uuid(),
	'Vasechkin2.txt',
	'c:/Users/Vasechkin/test/Vasechkin2.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 2 limit 1),
	(select id from validation_process order by validation_process_date offset 2 limit 1)
),
(
	gen_random_uuid(),
	'Vasechkin3.txt',
	'c:/Users/Vasechkin/test/Vasechkin3.txt',
	true,
	null,
	(select validation_process_date from validation_process order by validation_process_date offset 2 limit 1),
	(select id from validation_process order by validation_process_date offset 2 limit 1)
);