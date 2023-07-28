-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3307
-- Thời gian đã tạo: Th7 17, 2023 lúc 07:33 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danh_gia_san_pham`
--

CREATE TABLE `danh_gia_san_pham` (
  `id_danh_gia` int(11) NOT NULL,
  `noi_dung` text NOT NULL,
  `diem_danh_gia` decimal(2,1) NOT NULL,
  `ngay_danh_gia` date NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `danh_muc_san_pham`
--

CREATE TABLE `danh_muc_san_pham` (
  `id_danh_muc` int(11) NOT NULL,
  `ten_danh_muc` varchar(100) NOT NULL,
  `gioi_tinh` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `danh_muc_san_pham`
--

INSERT INTO `danh_muc_san_pham` (`id_danh_muc`, `ten_danh_muc`, `gioi_tinh`) VALUES
(1, 'Quần', 'Nam'),
(2, 'Áo', 'Nam'),
(3, 'Giày', 'Nam'),
(4, 'Dép', 'Nam'),
(5, 'Áo', 'Nữ'),
(6, 'Quần', 'Nữ'),
(7, 'Giày', 'Nữ'),
(8, 'Dép', 'Nữ'),
(9, 'Váy', 'Nữ');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `don_hang`
--

CREATE TABLE `don_hang` (
  `id_don_hang` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `ngay_dat_hang` date NOT NULL,
  `gia` double(10,2) NOT NULL,
  `ngay_giao_hang` date DEFAULT NULL,
  `trang_thai_don_hang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `don_hang`
--

INSERT INTO `don_hang` (`id_don_hang`, `id_nguoi_dung`, `ngay_dat_hang`, `gia`, `ngay_giao_hang`, `trang_thai_don_hang`) VALUES
(12, 16, '2023-06-29', 3018000.00, '2023-06-29', 'Đã hủy hàng'),
(13, 16, '2023-06-29', 2418000.00, '2023-06-29', 'Đã hủy hàng'),
(14, 16, '2023-06-29', 3218000.00, '2023-06-29', 'Đã hủy hàng'),
(15, 16, '2023-06-29', 5268000.00, '2023-06-29', 'Đã hủy hàng'),
(16, 16, '2023-06-29', 2018000.00, '2023-06-29', 'Đã hủy hàng'),
(17, 16, '2023-06-29', 3018000.00, '2023-06-29', 'Đã hủy hàng'),
(18, 16, '2023-06-29', 3768000.00, '2023-06-29', 'Đã hủy hàng'),
(19, 16, '2023-06-29', 518000.00, '2023-06-29', 'Đã hủy hàng'),
(20, 16, '2023-06-29', 2018000.00, '2023-06-29', 'Đã hủy hàng'),
(21, 16, '2023-06-29', 2918000.00, '2023-06-29', 'Đã hủy hàng'),
(22, 16, '2023-06-29', 818000.00, '2023-06-29', 'Đã hủy hàng'),
(23, 16, '2023-06-29', 518000.00, '2023-06-29', 'Đã hủy hàng'),
(24, 16, '2023-07-11', 63018000.00, '2023-07-11', 'Đã hủy hàng'),
(25, 16, '2023-07-12', 48768000.00, '2023-07-12', 'Đã hủy hàng'),
(26, 16, '2023-07-12', 48768000.00, '2023-07-12', 'Đã hủy hàng'),
(27, 16, '2023-07-12', 24018000.00, '2023-07-12', 'Đã hủy hàng'),
(28, 16, '2023-07-12', 50018000.00, '2023-07-12', 'Đã hủy hàng'),
(29, 16, '2023-07-12', 50018000.00, '2023-07-12', 'Đã hủy hàng'),
(30, 16, '2023-07-12', 98018000.00, '2023-07-12', 'Đã hủy hàng'),
(31, 16, '2023-07-12', 30018000.00, '2023-07-12', 'Đã hủy hàng'),
(32, 16, '2023-07-12', 518000.00, '2023-07-12', 'Đã hủy hàng');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item_don_hang`
--

CREATE TABLE `item_don_hang` (
  `id_item_don_hang` int(11) NOT NULL,
  `id_don_hang` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `id_thong_so` int(11) NOT NULL,
  `so_luong_san_pham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `item_don_hang`
--

INSERT INTO `item_don_hang` (`id_item_don_hang`, `id_don_hang`, `id_san_pham`, `id_thong_so`, `so_luong_san_pham`) VALUES
(23, 12, 7, 7, 4),
(24, 13, 3, 3, 3),
(25, 14, 3, 3, 4),
(26, 15, 7, 7, 7),
(27, 16, 1, 14, 4),
(28, 17, 7, 7, 4),
(29, 18, 7, 7, 5),
(30, 19, 1, 12, 1),
(31, 20, 1, 13, 4),
(32, 21, 3, 3, 3),
(33, 21, 1, 1, 1),
(34, 22, 5, 5, 2),
(35, 23, 6, 6, 2),
(36, 24, 2, 2, 200),
(37, 24, 1, 14, 6),
(38, 25, 7, 7, 65),
(39, 26, 7, 7, 65),
(40, 27, 1, 1, 45),
(41, 27, 2, 2, 5),
(42, 28, 1, 1, 100),
(43, 29, 1, 1, 100),
(44, 30, 1, 1, 100),
(45, 30, 1, 14, 96),
(46, 31, 1, 1, 60),
(47, 32, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `item_gio_hang`
--

CREATE TABLE `item_gio_hang` (
  `id_item_gio_hang` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `id_thong_so` int(11) NOT NULL,
  `so_luong_san_pham` int(11) NOT NULL,
  `trang_thai` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `item_gio_hang`
--

INSERT INTO `item_gio_hang` (`id_item_gio_hang`, `id_nguoi_dung`, `id_san_pham`, `id_thong_so`, `so_luong_san_pham`, `trang_thai`) VALUES
(74, 16, 1, 1, 40, 1);

--
-- Bẫy `item_gio_hang`
--
DELIMITER $$
CREATE TRIGGER `check_so_luong_san_pham_trigger` BEFORE UPDATE ON `item_gio_hang` FOR EACH ROW BEGIN
    DECLARE con_lai_value INT;
    
    -- Retrieve the con_lai value
    SELECT con_lai INTO con_lai_value
    FROM thong_so
    WHERE thong_so.id_san_pham = NEW.id_san_pham
    AND thong_so.id_thong_so = NEW.id_thong_so;

    -- Check the constraint
    IF NEW.so_luong_san_pham > con_lai_value THEN
        -- Raise an error
        SET @error = 'so_luong_san_pham must be less than or equal to con_lai';
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = @error;
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lich_su_mua_hang`
--

CREATE TABLE `lich_su_mua_hang` (
  `id_lich_su_mua_hang` int(11) NOT NULL,
  `id_nguoi_dung` int(11) NOT NULL,
  `id_don_hang` int(11) NOT NULL,
  `ngay_mua_hang` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `id_nguoi_dung` int(11) NOT NULL,
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(255) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `dia_chi` varchar(255) NOT NULL,
  `so_dien_thoai` varchar(20) NOT NULL,
  `loai_nguoi_dung` enum('Chu cua hang','Khach hang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`id_nguoi_dung`, `ten_dang_nhap`, `mat_khau`, `ho_ten`, `email`, `dia_chi`, `so_dien_thoai`, `loai_nguoi_dung`) VALUES
(16, 'admin', '$2y$10$NguKS72uVChzNRPWCGuX7Od3bn11aVGDhVVIu.p4OGexSTgt5EiuK', 'Cao Van Thien', 'caovanthien@gmail.com', '10000', '0395797020', 'Khach hang');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `san_pham`
--

CREATE TABLE `san_pham` (
  `id_san_pham` int(11) NOT NULL,
  `ten_san_pham` varchar(255) NOT NULL,
  `mo_ta` text NOT NULL,
  `gia_san_pham` decimal(10,2) NOT NULL,
  `thuong_hieu` varchar(100) NOT NULL,
  `id_danh_muc` int(11) NOT NULL,
  `hinh_anh` varchar(200) NOT NULL,
  `danh_gia` decimal(10,1) DEFAULT NULL,
  `da_ban` int(11) DEFAULT NULL,
  `giam_gia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `san_pham`
--

INSERT INTO `san_pham` (`id_san_pham`, `ten_san_pham`, `mo_ta`, `gia_san_pham`, `thuong_hieu`, `id_danh_muc`, `hinh_anh`, `danh_gia`, `da_ban`, `giam_gia`) VALUES
(1, 'Quần jeans nam', 'Form quần Skinny ôm vừa phải, đem đến người dùng cảm giác thoải mái, dễ chịu. Đây là form quần luôn rất được ưa chuộng, vì nó không chỉ mang đến sự tự tin, mà còn dẫn lối cho các chàng đến với vẻ ngoài ấn tượng\n\nQuần Jean là một trong những lựa chọn phù hợp trong mọi hoàn cảnh (như đi làm, đi chơi, đi tiệc,...) nhưng vẫn trang trọng và lịch lãm.', 500000.00, 'Levi\'s', 1, 'https://quanjeandep.com/images/thumbs/quan-jean-nam-co-dien-dang-dung-501-505s-10348.jpeg', 4.5, 100, 20),
(2, 'Áo polo nam', 'Áo polo nam trơn màu xanh', 300000.00, 'Lacoste', 2, 'https://lzd-img-global.slatic.net/g/p/d62ed3ee88c31c3be463e907c98bf4f1.jpg_720x720q80.jpg', 4.2, 50, NULL),
(3, 'Giày thể thao nam', 'Giày thể thao nam màu đen', 800000.00, 'Nike', 3, 'https://salt.tikicdn.com/ts/product/ff/df/1c/e4eeb64eac1d45fdbaf541af1b04baca.jpg', 4.8, 20, 50),
(4, 'Dép xỏ ngón', 'Dép xỏ ngón nữ màu hồng', 200000.00, 'Havaianas', 8, 'https://bizweb.dktcdn.net/100/463/547/products/14c15aa6-0cfc-4729-a4ac-85388a7e574c.jpg?v=1661426248837', 4.0, 499, NULL),
(5, 'Quần kaki', 'Quần kaki nam dáng regular fit', 400000.00, 'Dockers', 1, 'https://catsa.vn/wp-content/uploads/2022/11/Quan-kaki-kem-theu-2.jpg', 4.3, 22, 50),
(6, 'Áo sơ mi', 'Áo sơ mi nam trắng', 250000.00, 'Uniqlo', 2, 'https://savani.vn/images/products/2022/07/29/large/ao-so-mi-nam-mls003-2w0011-1_1659090152.jpg', 4.1, 111, NULL),
(7, 'Giày thể thao nữ', 'Giày thể thao nữ màu trắng', 750000.00, 'Adidas', 7, 'https://product.hstatic.net/200000245907/product/1993880_l_20568d2e703f4c8481fa64a73f910014_grande.jpg', 4.7, 1111, 60),
(8, 'Dép bít mũi', 'Dép bít mũi nam màu đen', 180000.00, 'Bata', 4, 'https://prices.vn/storage/photo/product/dep-bit-mui-nam-da-bo-that-cao-cap-mau-den-dna05.png', 3.9, 223, NULL),
(9, 'Quần jogger', 'Quần jogger nam màu xám', 350000.00, 'Puma', 1, 'https://vicsport.vn/wp-content/uploads/2018/07/quan-jogger-nam-adidas-3-soc-mau-xam-cuc-chat-e1529232782611.jpg', 4.4, 55, NULL),
(10, 'Áo phông', 'Áo phông nam trơn màu đen', 200000.00, 'H&M', 2, 'https://product.hstatic.net/1000096703/product/1_ab76fd59c647470391390786d03092c2_master.jpg', 4.0, 11, 40),
(11, 'Áo khoác', 'Áo khoác nữ màu đen', 600000.00, 'Zara', 5, 'https://lzd-img-global.slatic.net/g/p/d4d93d4346d99eda89d29c86d9093ea6.jpg_720x720q80.jpg', 4.6, 30, NULL),
(13, 'Giày cao gót', 'Giày cao gót nữ màu đỏ', 800000.00, 'Christian Louboutin', 7, 'https://cdn.shopify.com/s/files/1/1404/4249/files/giay-cao-got-_nu-dong-hai-G81A3_grande.jpg?v=1527570550', 4.5, 10, NULL),
(14, 'Sandal', 'Váy ngắn', 350000.00, 'Birkenstock', 9, 'https://hotgirlshop.com/uploads/picture/05042023/News/2045132042-mua-vay-trang-xep-ly-mlb-o-dau.jpg', 4.2, 20, 50),
(15, 'Quần shorts', 'Quần shorts nữ màu xanh', 200000.00, 'Hollister', 6, 'https://lzd-img-global.slatic.net/g/p/5de10478d012faf139655a44fb4a562f.jpg_720x720q80.jpg', 4.0, 5, NULL),
(16, 'Áo len', 'Áo len nữ trơn màu xám', 300000.00, 'Gap', 5, 'https://product.hstatic.net/200000142885/product/a2615ef2aa8743e78e9d8769a18c429a_a3bab55ac5d647d6a6e8b5ce111b628f_master.jpg', 4.3, 12, 50),
(17, 'Giày búp bê', 'Giày búp bê nữ màu hồng', 450000.00, 'Clarks', 7, 'https://www.zanado.com/media/catalog/product/cache/all/thumbnail/700x817/7b8fef0172c2eb72dd8fd366c999954c/g/i/giay_bup_be_phoi_no_mau_hong_336e.jpg', 4.7, 8, NULL),
(18, 'Áo nỉ', 'Áo nỉ nữ trơn màu đen', 150000.00, 'H&M', 5, 'https://dosi-in.com/images/detailed/445/dosiin-mvr-maverick-ao-khoac-hoodie-zip-unisex-the-thao-ao-gio-tron-co-day-keo-zip-vai-mong-mat-445618.jpg', 4.1, 25, 10),
(19, 'Quần legging', 'Quần legging nữ màu đen', 250000.00, 'Nike', 6, 'https://lzd-img-global.slatic.net/g/p/941f34458b3e7e569bee2aa1453b1ffe.jpg_720x720q80.jpg', 4.4, 18, 30);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thong_bao`
--

CREATE TABLE `thong_bao` (
  `id` int(11) NOT NULL,
  `tieu_de` varchar(255) DEFAULT NULL,
  `noi_dung` text DEFAULT NULL,
  `ngay_tao` datetime DEFAULT NULL,
  `id_nguoi_dung` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thong_so`
--

CREATE TABLE `thong_so` (
  `so_luong` int(11) NOT NULL,
  `con_lai` int(11) NOT NULL,
  `kich_thuoc` varchar(50) NOT NULL,
  `id_san_pham` int(11) NOT NULL,
  `id_thong_so` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `thong_so`
--

INSERT INTO `thong_so` (`so_luong`, `con_lai`, `kich_thuoc`, `id_san_pham`, `id_thong_so`) VALUES
(100, 100, 'S', 1, 1),
(200, 200, 'M', 2, 2),
(150, 150, 'L', 3, 3),
(50, 50, 'XL', 4, 4),
(120, 120, 'S', 5, 5),
(80, 80, 'M', 6, 6),
(90, 90, 'L', 7, 7),
(70, 70, 'XL', 8, 8),
(180, 180, 'S', 9, 9),
(60, 60, 'M', 10, 10),
(60, 60, 'XXL', 1, 11),
(100, 100, 'XL', 1, 12),
(100, 100, 'XS', 1, 13),
(100, 100, 'M', 1, 14);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  ADD PRIMARY KEY (`id_danh_gia`),
  ADD KEY `fk_danh_gia_nguoi_dung` (`id_nguoi_dung`),
  ADD KEY `fk_danh_gia_san_pham` (`id_san_pham`);

--
-- Chỉ mục cho bảng `danh_muc_san_pham`
--
ALTER TABLE `danh_muc_san_pham`
  ADD PRIMARY KEY (`id_danh_muc`);

--
-- Chỉ mục cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD PRIMARY KEY (`id_don_hang`),
  ADD KEY `fk_don_hang_nguoi_dung` (`id_nguoi_dung`);

--
-- Chỉ mục cho bảng `item_don_hang`
--
ALTER TABLE `item_don_hang`
  ADD PRIMARY KEY (`id_item_don_hang`),
  ADD KEY `fk_item_don_hang_san_pham` (`id_san_pham`),
  ADD KEY `fk_item_don_hang_thong_so` (`id_thong_so`),
  ADD KEY `fk_item_don_hang_don_hang` (`id_don_hang`);

--
-- Chỉ mục cho bảng `item_gio_hang`
--
ALTER TABLE `item_gio_hang`
  ADD PRIMARY KEY (`id_item_gio_hang`),
  ADD KEY `fk_item_gio_hang_san_pham` (`id_san_pham`),
  ADD KEY `fk_item_gio_hang_thong_so` (`id_thong_so`),
  ADD KEY `fk_item_gio_hang_nguoi_dung` (`id_nguoi_dung`);

--
-- Chỉ mục cho bảng `lich_su_mua_hang`
--
ALTER TABLE `lich_su_mua_hang`
  ADD PRIMARY KEY (`id_lich_su_mua_hang`),
  ADD KEY `fk_lich_su_mua_hang_nguoi_dung` (`id_nguoi_dung`),
  ADD KEY `fk_lich_su_mua_hang_don_hang` (`id_don_hang`);

--
-- Chỉ mục cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`id_nguoi_dung`);

--
-- Chỉ mục cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`id_san_pham`),
  ADD KEY `fk_san_pham_danh_muc` (`id_danh_muc`);

--
-- Chỉ mục cho bảng `thong_bao`
--
ALTER TABLE `thong_bao`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_thong_bao_nguoi_dung` (`id_nguoi_dung`);

--
-- Chỉ mục cho bảng `thong_so`
--
ALTER TABLE `thong_so`
  ADD PRIMARY KEY (`id_thong_so`),
  ADD KEY `fk_thong_so_san_pham` (`id_san_pham`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  MODIFY `id_danh_gia` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `danh_muc_san_pham`
--
ALTER TABLE `danh_muc_san_pham`
  MODIFY `id_danh_muc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  MODIFY `id_don_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `item_don_hang`
--
ALTER TABLE `item_don_hang`
  MODIFY `id_item_don_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT cho bảng `item_gio_hang`
--
ALTER TABLE `item_gio_hang`
  MODIFY `id_item_gio_hang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT cho bảng `lich_su_mua_hang`
--
ALTER TABLE `lich_su_mua_hang`
  MODIFY `id_lich_su_mua_hang` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  MODIFY `id_nguoi_dung` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  MODIFY `id_san_pham` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT cho bảng `thong_bao`
--
ALTER TABLE `thong_bao`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `thong_so`
--
ALTER TABLE `thong_so`
  MODIFY `id_thong_so` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `danh_gia_san_pham`
--
ALTER TABLE `danh_gia_san_pham`
  ADD CONSTRAINT `fk_danh_gia_nguoi_dung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoi_dung` (`id_nguoi_dung`),
  ADD CONSTRAINT `fk_danh_gia_san_pham` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id_san_pham`);

--
-- Các ràng buộc cho bảng `don_hang`
--
ALTER TABLE `don_hang`
  ADD CONSTRAINT `fk_don_hang_nguoi_dung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoi_dung` (`id_nguoi_dung`);

--
-- Các ràng buộc cho bảng `item_don_hang`
--
ALTER TABLE `item_don_hang`
  ADD CONSTRAINT `fk_item_don_hang_don_hang` FOREIGN KEY (`id_don_hang`) REFERENCES `don_hang` (`id_don_hang`),
  ADD CONSTRAINT `fk_item_don_hang_san_pham` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id_san_pham`),
  ADD CONSTRAINT `fk_item_don_hang_thong_so` FOREIGN KEY (`id_thong_so`) REFERENCES `thong_so` (`id_thong_so`);

--
-- Các ràng buộc cho bảng `item_gio_hang`
--
ALTER TABLE `item_gio_hang`
  ADD CONSTRAINT `fk_item_gio_hang_nguoi_dung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoi_dung` (`id_nguoi_dung`),
  ADD CONSTRAINT `fk_item_gio_hang_san_pham` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id_san_pham`),
  ADD CONSTRAINT `fk_item_gio_hang_thong_so` FOREIGN KEY (`id_thong_so`) REFERENCES `thong_so` (`id_thong_so`);

--
-- Các ràng buộc cho bảng `lich_su_mua_hang`
--
ALTER TABLE `lich_su_mua_hang`
  ADD CONSTRAINT `fk_lich_su_mua_hang_don_hang` FOREIGN KEY (`id_don_hang`) REFERENCES `don_hang` (`id_don_hang`),
  ADD CONSTRAINT `fk_lich_su_mua_hang_nguoi_dung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoi_dung` (`id_nguoi_dung`);

--
-- Các ràng buộc cho bảng `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `fk_san_pham_danh_muc` FOREIGN KEY (`id_danh_muc`) REFERENCES `danh_muc_san_pham` (`id_danh_muc`);

--
-- Các ràng buộc cho bảng `thong_bao`
--
ALTER TABLE `thong_bao`
  ADD CONSTRAINT `fk_thong_bao_nguoi_dung` FOREIGN KEY (`id_nguoi_dung`) REFERENCES `nguoi_dung` (`id_nguoi_dung`);

--
-- Các ràng buộc cho bảng `thong_so`
--
ALTER TABLE `thong_so`
  ADD CONSTRAINT `fk_thong_so_san_pham` FOREIGN KEY (`id_san_pham`) REFERENCES `san_pham` (`id_san_pham`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
