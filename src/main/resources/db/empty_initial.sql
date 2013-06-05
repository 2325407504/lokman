INSERT INTO `role` (`id`, `createdAt`, `updatedAt`, `code`, `name`) VALUES
(1, NOW(), NOW(), 'ROLE_SUPERADMIN', 'Süper Yönetici'),
(2, NOW(), NOW(), 'ROLE_ADMIN', 'Yönetici'),
(3, NOW(), NOW(), 'ROLE_USER', 'Kullanıcı');

INSERT INTO `region` (`id`, `createdAt`, `updatedAt`, `name`) VALUES
(1, NOW(), NOW(), 'İstanbul/Trakya'),
(2, NOW(), NOW(), 'Marmara'),
(3, NOW(), NOW(), 'Kuzey/Orta Anadolu'),
(4, NOW(), NOW(), 'Ege'),
(5, NOW(), NOW(), 'Doğu Akdeniz'),
(6, NOW(), NOW(), 'Güney/Orta Anadolu');

INSERT INTO `customer` (`id`, `createdAt`, `updatedAt`, `firstName`, `lastName`) VALUES
(1, NOW(), NOW(), 'Cem', 'ARIPD'),
(2, NOW(), NOW(), 'Admin', 'ARIPD'),
(3, NOW(), NOW(), 'User', 'ARIPD'),
(4, NOW(), NOW(), 'Fatih', 'Gemalmaz'),
(5, NOW(), NOW(), 'Yusuf', 'Memnun'),
(6, NOW(), NOW(), 'Murat', 'Doğan'),
(7, NOW(), NOW(), 'Esma', 'Akkaya'),
(8, NOW(), NOW(), 'Ömer', 'Hayır'),
(9, NOW(), NOW(), 'Arif', 'Hayır'),
(10, NOW(), NOW(), 'Banu', 'Çevikel'),
(11, NOW(), NOW(), 'Hasan', 'Öztürk'),
(12, NOW(), NOW(), 'Ergin', 'Oral'),
(13, NOW(), NOW(), 'Armağan', 'Yılmazer'),
(14, NOW(), NOW(), 'Oktay', 'Özsüer');

INSERT INTO `account` (`id`, `createdAt`, `updatedAt`, `active`, `email`, `password`, `username`, `customer_id`, `region_id`) VALUES
(1, NOW(), NOW(), b'1', 'cem@aripd.com', MD5('cem'), 'cem', 1, 1),
(2, NOW(), NOW(), b'1', 'admin@aripd.com', MD5('admin'), 'admin', 2, 1),
(3, NOW(), NOW(), b'1', 'user@aripd.com', MD5('user'), 'user', 3, 1),
(4, NOW(), NOW(), b'1', 'fatih.gemalmaz@lgk.com.tr', MD5('gem12345'), 'fgemalmaz', 4, 1),
(5, NOW(), NOW(), b'1', 'yusuf.memnun@lgk.com.tr', MD5('1610mm'), 'ymemnun', 5, 1),
(6, NOW(), NOW(), b'1', 'murat.dogan@lgk.com.tr', MD5('6254991'), 'mdogan', 6, 1),
(7, NOW(), NOW(), b'1', 'esma.akkaya@lgk.com.tr', MD5('05112008'), 'eakkaya', 7, 1),
(8, NOW(), NOW(), b'1', 'omer.hayir@lgk.com.tr', MD5('12345'), 'ohayir', 8, 1),
(9, NOW(), NOW(), b'1', 'arif.hayir@lgk.com.tr', MD5('12345'), 'ahayir', 9, 2),
(10, NOW(), NOW(), b'1', 'banu.cevikel@lgk.com.tr', MD5('12345'), 'bcevikel', 10, 2),
(11, NOW(), NOW(), b'1', 'hasan.ozturk@lgk.com.tr', MD5('12345'), 'hozturk', 11, 2),
(12, NOW(), NOW(), b'1', 'ergin.oral@lgk.com.tr', MD5('12345'), 'eoral', 12, 2),
(13, NOW(), NOW(), b'1', 'armagan.yilmazer@lgk.com.tr', MD5('12345'), 'ayilmazer', 13, 2),
(14, NOW(), NOW(), b'1', 'oktay.ozsuer@lgk.com.tr', MD5('12345'), 'oozsuer', 14, 2);

INSERT INTO `account_role` (`account_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(3, 3),
(4, 2),
(5, 2),
(6, 2),
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3);

INSERT INTO `driver` (`id`, `createdAt`, `updatedAt`, `active`, `code`, `name`, `phonenumber`, `region_id`) VALUES
(1, NOW(), NOW(), b'1', 'UID1', 'Sürücü1', 'Telefon1', 1),
(2, NOW(), NOW(), b'1', 'UID2', 'Sürücü2', 'Telefon2', 1),
(3, NOW(), NOW(), b'1', 'UID3', 'Sürücü3', 'Telefon3', 1),
(4, NOW(), NOW(), b'1', 'UID4', 'Sürücü4', 'Telefon4', 2),
(5, NOW(), NOW(), b'1', 'UID5', 'Sürücü5', 'Telefon5', 2),
(6, NOW(), NOW(), b'1', 'UID6', 'Sürücü6', 'Telefon6', 2),
(7, NOW(), NOW(), b'1', 'UID7', 'Sürücü7', 'Telefon7', 3),
(8, NOW(), NOW(), b'1', 'UID8', 'Sürücü8', 'Telefon8', 3),
(9, NOW(), NOW(), b'1', 'UID9', 'Sürücü9', 'Telefon9', 3),
(10, NOW(), NOW(), b'1', 'UID10', 'Sürücü10', 'Telefon10', 4),
(11, NOW(), NOW(), b'1', 'UID11', 'Sürücü11', 'Telefon11', 4),
(12, NOW(), NOW(), b'1', 'UID12', 'Sürücü12', 'Telefon12', 5),
(13, NOW(), NOW(), b'1', 'UID13', 'Sürücü13', 'Telefon13', 5),
(14, NOW(), NOW(), b'1', 'UID14', 'Sürücü14', 'Telefon14', 6),
(15, NOW(), NOW(), b'1', 'UID15', 'Sürücü15', 'Telefon15', 6);

INSERT INTO `truck` (`id`, `createdAt`, `updatedAt`, `active`, `plate`, `region_id`) VALUES
(1, NOW(), NOW(), b'1', '34 YK 1063', 1),
(2, NOW(), NOW(), b'1', '35 SEC 10', 1),
(3, NOW(), NOW(), b'1', '35 ZH 318', 1),
(4, NOW(), NOW(), b'1', '35 U 1884', 1),
(5, NOW(), NOW(), b'1', '35 HA 6914', 2),
(6, NOW(), NOW(), b'1', '34 AT 2912', 2),
(7, NOW(), NOW(), b'1', '34 TE 6123', 2),
(8, NOW(), NOW(), b'1', '35 CEV 47', 3),
(9, NOW(), NOW(), b'1', '35 HMH 47', 3),
(10, NOW(), NOW(), b'1', '35 SAU 44', 3),
(11, NOW(), NOW(), b'1', '35 AC 7870', 3),
(12, NOW(), NOW(), b'1', '35 VH 793', 4),
(13, NOW(), NOW(), b'1', '35 HNU 60', 4),
(14, NOW(), NOW(), b'1', '09 D 9585', 4),
(15, NOW(), NOW(), b'1', '35 HVU 92', 4);

INSERT INTO `quota` (`id`, `createdAt`, `updatedAt`, `active`, `code`, `name`) VALUES
(1, NOW(), NOW(), b'1', 'UID1', 'LASDER'),
(2, NOW(), NOW(), b'1', 'UID2', 'Çimento Fabrikası');

INSERT INTO `subcontractor` (`id`, `createdAt`, `updatedAt`, `active`, `code`, `name`, `region_id`) VALUES
(1, NOW(), NOW(), b'1', 'UID1', 'Fethi Topaktaş', 1),
(2, NOW(), NOW(), b'1', 'UID2', 'Niyazi Özdemir', 2),
(3, NOW(), NOW(), b'1', 'UID3', 'Newnak', 2),
(4, NOW(), NOW(), b'1', 'UID4', 'Ali Doğan', 2),
(5, NOW(), NOW(), b'1', 'UID5', 'Yavuz Narman', 2),
(6, NOW(), NOW(), b'1', 'UID6', 'Diğer', 2),
(7, NOW(), NOW(), b'1', 'UID7', 'Çubukçular', 2),
(8, NOW(), NOW(), b'1', 'UID8', 'Veysel Kutucu', 2),
(9, NOW(), NOW(), b'1', 'UID9', 'Erdal Yalım Bursa', 2),
(10, NOW(), NOW(), b'1', 'UID10', 'Erdal Yalım Eskişehir', 2),
(11, NOW(), NOW(), b'1', 'UID11', 'Ali Caner', 2),
(12, NOW(), NOW(), b'1', 'UID12', 'İsmail Demirkol', 2),
(13, NOW(), NOW(), b'1', 'UID13', 'İbrahim Gedikli', 3),
(14, NOW(), NOW(), b'1', 'UID14', 'Diğer', 3),
(15, NOW(), NOW(), b'1', 'UID15', 'Yaşar Bayrakal', 4),
(16, NOW(), NOW(), b'1', 'UID16', 'Ömer Örücü', 4),
(17, NOW(), NOW(), b'1', 'UID17', 'Ramazan Kafa', 4),
(18, NOW(), NOW(), b'1', 'UID18', 'Diğer', 4),
(19, NOW(), NOW(), b'1', 'UID19', 'Veysel Kızıldağ', 5),
(20, NOW(), NOW(), b'1', 'UID20', 'Ayhan Aydın', 5),
(21, NOW(), NOW(), b'1', 'UID21', 'Murat Teke', 5),
(22, NOW(), NOW(), b'1', 'UID22', 'Ünsal Alemdar', 5),
(23, NOW(), NOW(), b'1', 'UID23', 'Senayi Öztürk', 5),
(24, NOW(), NOW(), b'1', 'UID24', 'Diğer', 5),
(25, NOW(), NOW(), b'1', 'UID25', 'Diğer', 6);