-- This is an optional file that can be used for initial database setup

CREATE DATABASE IF NOT EXISTS pillpoint_db;
USE pillpoint_db;

-- Create tables based on the entity model
-- The tables will be automatically created by Hibernate through JPA
-- But you can use this file for initial data setup if needed

-- Initial data for testing
INSERT INTO users
(username, email, password, role, created_at, updated_at)
VALUES
('admin', 'admin@health.gov', '$2a$10$EqjJ7hVESOb6UDGjg6pPZ.L94SxncliZf3DUrxjV1fjZC0K6XqSFG', 'ADMIN', NOW(), NOW()),
('hospital', 'pharmacy@hospital.com', '$2a$10$EqjJ7hVESOb6UDGjg6pPZ.L94SxncliZf3DUrxjV1fjZC0K6XqSFG', 'HOSPITAL', NOW(), NOW()),
('supplier', 'supplier@medcorp.com', '$2a$10$EqjJ7hVESOb6UDGjg6pPZ.L94SxncliZf3DUrxjV1fjZC0K6XqSFG', 'SUPPLIER', NOW(), NOW());

-- The passwords are 'password' encrypted with BCrypt
-- You should change these in a production environment
