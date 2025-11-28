-- Create schema for Companies CRUD application
-- This script creates the necessary tables for the application

-- Company table
CREATE TABLE IF NOT EXISTS company (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    founder VARCHAR(255),
    logo VARCHAR(255),
    foundation_date DATE
);

-- WebSite table
CREATE TABLE IF NOT EXISTS web_site (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    description TEXT,
    id_company BIGINT,
    CONSTRAINT fk_company FOREIGN KEY (id_company) REFERENCES company(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_company_name ON company(name);
CREATE INDEX IF NOT EXISTS idx_company_foundation_date ON company(foundation_date);
CREATE INDEX IF NOT EXISTS idx_website_name ON web_site(name);
CREATE INDEX IF NOT EXISTS idx_website_company ON web_site(id_company);

-- Grant permissions to application user
GRANT ALL PRIVILEGES ON TABLE company TO debuggeandoideas;
GRANT ALL PRIVILEGES ON TABLE web_site TO debuggeandoideas;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO debuggeandoideas;
