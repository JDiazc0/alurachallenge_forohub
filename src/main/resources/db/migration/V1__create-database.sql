-- Tabla users
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

-- Tabla courses
CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    course_name VARCHAR(255) UNIQUE
);

-- Tabla intermedia user_courses
CREATE TABLE user_courses (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    course_id BIGINT REFERENCES courses(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, course_id)
);

-- Tabla topics
CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    message VARCHAR(255),
    creation_date DATE,
    status VARCHAR(255),
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    course_id BIGINT REFERENCES courses(id) ON DELETE CASCADE
);

-- Tabla comments
CREATE TABLE comments (
    id BIGSERIAL PRIMARY KEY,
    text VARCHAR(255),
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    topic_id BIGINT REFERENCES topics(id) ON DELETE CASCADE
);
