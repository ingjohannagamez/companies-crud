-- Sample data for Companies CRUD application
-- This script populates initial data for testing

-- Insert sample companies
INSERT INTO company (name, founder, logo, foundation_date) VALUES
('TechCorp', 'John Smith', 'techcorp-logo.png', '2010-05-15'),
('InnovateSoft', 'Jane Doe', 'innovatesoft-logo.png', '2015-08-22'),
('DataSystems Inc', 'Robert Johnson', 'datasystems-logo.png', '2008-03-10'),
('CloudNet Solutions', 'Maria Garcia', 'cloudnet-logo.png', '2018-11-30'),
('AI Dynamics', 'David Chen', 'aidynamics-logo.png', '2020-01-05');

-- Insert sample websites
INSERT INTO web_site (name, category, description, id_company) VALUES
('TechCorp Main Site', 'CORPORATE', 'Main corporate website for TechCorp', 1),
('TechCorp Blog', 'BLOG', 'Technical blog and news', 1),
('InnovateSoft Portal', 'CORPORATE', 'Customer portal for InnovateSoft', 2),
('InnovateSoft Docs', 'DOCUMENTATION', 'Product documentation site', 2),
('DataSystems Enterprise', 'CORPORATE', 'Enterprise solutions website', 3),
('CloudNet Platform', 'PRODUCT', 'Cloud platform landing page', 4),
('CloudNet Support', 'SUPPORT', 'Customer support portal', 4),
('AI Dynamics Research', 'RESEARCH', 'AI research and publications', 5);
