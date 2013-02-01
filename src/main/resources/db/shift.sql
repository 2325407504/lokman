TRUNCATE TABLE `shift`;
insert into `shift` 
(`id`, `breakCount`, `breakTime`, `electrictyConsumptionEnd`, `electrictyConsumptionStart`, `productionInTonne`, `startedAt`, `account_id`) values
(null, 12, 12, 12, 12, 12, DATE_ADD('2009-01-01', INTERVAL 60*rand() HOUR), 1),
(null, 12, 12, 12, 12, 12, DATE_ADD('2009-01-01', INTERVAL 60*rand() HOUR), 1),
(null, 12, 12, 12, 12, 12, DATE_ADD('2009-01-01', INTERVAL 60*rand() HOUR), 1),
(null, 12, 12, 12, 12, 12, DATE_ADD('2009-01-01', INTERVAL 60*rand() HOUR), 1),
(null, 12, 12, 12, 12, 12, DATE_ADD('2009-01-01', INTERVAL 60*rand() HOUR), 1);