CREATE TABLE universities (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    domain VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE departments (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    university_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_dept_university FOREIGN KEY (university_id) REFERENCES universities(id)
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    university_id BIGINT,
    department_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_university FOREIGN KEY (university_id) REFERENCES universities(id),
    CONSTRAINT fk_user_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_topic_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE user_interests (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ui_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_ui_topic FOREIGN KEY (topic_id) REFERENCES topics(id)
);

CREATE TABLE sources (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    css_selector VARCHAR(255),
    schedule_cron VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    department_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_source_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE feeds (
    id BIGSERIAL PRIMARY KEY,
    source_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    summary TEXT,
    content_url VARCHAR(255) NOT NULL,
    image_url VARCHAR(255),
    published_at TIMESTAMP,
    search_vector TSVECTOR,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_feed_source FOREIGN KEY (source_id) REFERENCES sources(id)
);

CREATE TABLE feed_topics (
    id BIGSERIAL PRIMARY KEY,
    feed_id BIGINT NOT NULL,
    topic_id BIGINT NOT NULL,
    CONSTRAINT fk_ft_feed FOREIGN KEY (feed_id) REFERENCES feeds(id),
    CONSTRAINT fk_ft_topic FOREIGN KEY (topic_id) REFERENCES topics(id)
);

CREATE TABLE bookmarks (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    feed_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_bookmark_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_bookmark_feed FOREIGN KEY (feed_id) REFERENCES feeds(id)
);

-- 인덱스 생성 (최신 조회순 및 검색 성능 튜닝)
CREATE INDEX idx_feeds_published_at ON feeds(published_at);
CREATE INDEX idx_feeds_search_vector ON feeds USING GIN(search_vector);
CREATE INDEX idx_feeds_source_id ON feeds(source_id);
