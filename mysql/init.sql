-- Xóa database nếu đã tồn tại
DROP DATABASE IF EXISTS web_doc_truyen;
-- Tạo database mới
CREATE DATABASE IF NOT EXISTS web_doc_truyen;
USE web_doc_truyen;

-- Bảng người dùng
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    hashed_password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    role ENUM('admin', 'user') NOT NULL,
    cover_image VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng ví xu
CREATE TABLE wallets (
    wallet_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    balance INT DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng danh mục truyện
CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng quốc gia
CREATE TABLE countries (
    country_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- Bảng truyện
CREATE TABLE comics (
    comic_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    author VARCHAR(255),
    status ENUM('ONGOING', 'COMPLETED') NOT NULL,
    cover_image VARCHAR(255),
    country_id INT,
    views INT DEFAULT 0,
    likes INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (country_id) REFERENCES countries(country_id) ON DELETE SET NULL
);

-- Bảng mối quan hệ giữa truyện và danh mục
CREATE TABLE comic_categories (
    comic_id INT NOT NULL,
    category_id INT NOT NULL,
    PRIMARY KEY (comic_id, category_id),
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);

-- Bảng chương truyện
CREATE TABLE chapters (
    chapter_id INT AUTO_INCREMENT PRIMARY KEY,
    comic_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    is_locked BOOLEAN DEFAULT TRUE,
    price_xu INT DEFAULT 0,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE
);

-- Bảng ảnh chương truyện
CREATE TABLE chapter_images (
    image_id INT AUTO_INCREMENT PRIMARY KEY,
    chapter_id INT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chapter_id) REFERENCES chapters(chapter_id) ON DELETE CASCADE
);

-- Bảng bình luận
CREATE TABLE comments (
    comment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    comic_id INT NOT NULL,
    chapter_id INT,
    content TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE,
    FOREIGN KEY (chapter_id) REFERENCES chapters(chapter_id) ON DELETE SET NULL
);

-- Bảng lịch sử đọc truyện
CREATE TABLE comic_read_history (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    comic_id INT NOT NULL,
    chapter_id INT NOT NULL,
    read_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE,
    FOREIGN KEY (chapter_id) REFERENCES chapters(chapter_id) ON DELETE CASCADE
);

-- Bảng danh sách theo dõi truyện
CREATE TABLE comic_followers (
    user_id INT NOT NULL,
    comic_id INT NOT NULL,
    PRIMARY KEY (user_id, comic_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE
);

-- Bảng đánh giá truyện
CREATE TABLE ratings (
    rating_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    comic_id INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (comic_id) REFERENCES comics(comic_id) ON DELETE CASCADE
);

-- Bảng thông báo
CREATE TABLE notifications (
    notification_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Bảng giao dịch xu
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    amount INT NOT NULL,
    xu_received INT NOT NULL,
    transaction_type ENUM('recharge', 'purchase') NOT NULL,
    status ENUM('pending', 'completed', 'failed') NOT NULL DEFAULT 'pending',
    transaction_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Thêm dữ liệu mẫu

INSERT INTO users (username, hashed_password, email, role, cover_image) VALUES
('admin1', 'hashed_password_1', 'admin1@example.com', 'admin', 'admin1.jpg'),
('user1', 'hashed_password_2', 'user1@example.com', 'user', 'user1.jpg'),
('user2', 'hashed_password_3', 'user2@example.com', 'user', 'user2.jpg');

INSERT INTO wallets (user_id, balance) VALUES
(1, 1000),
(2, 500),
(3, 200);

INSERT INTO categories (category_name, description) VALUES
('Action', 'Truyện có nhiều pha hành động kịch tính'),
('Romance', 'Truyện tình cảm lãng mạn'),
('Fantasy', 'Truyện viễn tưởng, phép thuật');

INSERT INTO countries (name) VALUES
('Nhật Bản'),
('Hàn Quốc'),
('Trung Quốc');

INSERT INTO comics (title, description, author, status, cover_image, country_id, views, likes) VALUES
('One Piece', 'Hành trình tìm kiếm kho báu của Luffy', 'Eiichiro Oda', 'ONGOING', 'onepiece.jpg', 1, 50000, 1000),
('Naruto', 'Hành trình trở thành Hokage của Naruto', 'Masashi Kishimoto', 'COMPLETED', 'naruto.jpg', 1, 70000, 2000),
('Solo Leveling', 'Thợ săn mạnh nhất thế giới', 'Chugong', 'COMPLETED', 'sololeveling.jpg', 2, 90000, 3000);

INSERT INTO comic_categories (comic_id, category_id) VALUES
(1, 1), (2, 1), (3, 3);

INSERT INTO chapters (comic_id, title, is_locked, price_xu) VALUES
(1, 'Chương 1: Luffy ra khơi', FALSE, 0),
(1, 'Chương 2: Gặp Zoro', TRUE, 10),
(2, 'Chương 1: Naruto tốt nghiệp', FALSE, 0),
(3, 'Chương 1: Thợ săn yếu nhất', FALSE, 0);

INSERT INTO comments (user_id, comic_id, chapter_id, content) VALUES
(2, 1, 1, 'Truyện hay quá!'),
(3, 2, 1, 'Naruto tuổi thơ của tôi'),
(1, 3, 1, 'Solo Leveling quá đỉnh!');

INSERT INTO transactions (user_id, amount, xu_received, transaction_type, status) VALUES
(2, 50000, 500, 'recharge', 'completed'),
(3, 10000, 100, 'purchase', 'completed'),
(1, 20000, 200, 'recharge', 'pending');

