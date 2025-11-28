# AWS Deployment Guide

## Prerequisites

- AWS Account
- AWS CLI installed and configured
- Docker installed (for containerized deployment)
- PostgreSQL RDS instance created

## Deployment Options

### Option 1: AWS Elastic Beanstalk (Recommended for quick deployment)

1. **Create Elastic Beanstalk Application**
   ```bash
   eb init -p java-17 companies-crud --region us-east-1
   ```

2. **Create Environment**
   ```bash
   eb create companies-crud-env
   ```

3. **Set Environment Variables**
   ```bash
   eb setenv DATABASE_URL=jdbc:postgresql://your-rds-endpoint:5432/companies \
              DATABASE_USERNAME=your_username \
              DATABASE_PASSWORD=your_password \
              SPRING_PROFILES_ACTIVE=aws
   ```

4. **Deploy**
   ```bash
   eb deploy
   ```

### Option 2: AWS ECS (Elastic Container Service)

1. **Build and Push Docker Image to ECR**
   ```bash
   # Create ECR repository
   aws ecr create-repository --repository-name companies-crud

   # Login to ECR
   aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin <account-id>.dkr.ecr.us-east-1.amazonaws.com

   # Build image
   docker build -t companies-crud .

   # Tag image
   docker tag companies-crud:latest <account-id>.dkr.ecr.us-east-1.amazonaws.com/companies-crud:latest

   # Push image
   docker push <account-id>.dkr.ecr.us-east-1.amazonaws.com/companies-crud:latest
   ```

2. **Create ECS Task Definition** (using AWS Console or CLI)

3. **Create ECS Service** with desired task count

### Option 3: AWS EC2 (Manual deployment)

1. **Launch EC2 Instance** (Amazon Linux 2 or Ubuntu)

2. **Install Java 17**
   ```bash
   sudo yum install java-17-amazon-corretto -y  # For Amazon Linux
   # OR
   sudo apt-get install openjdk-17-jdk -y       # For Ubuntu
   ```

3. **Upload JAR file** to EC2 instance

4. **Set environment variables** in `/etc/environment` or systemd service file

5. **Run application**
   ```bash
   java -jar companies-crud-0.0.1-SNAPSHOT.jar --spring.profiles.active=aws
   ```

## Database Setup (AWS RDS)

1. **Create PostgreSQL RDS Instance**
   - Engine: PostgreSQL 16
   - Instance class: db.t3.micro (for development)
   - Storage: 20 GB SSD
   - Enable automatic backups

2. **Configure Security Group**
   - Allow inbound traffic on port 5432 from your application security group

3. **Create Database**
   ```sql
   CREATE DATABASE companies;
   ```

## Environment Variables

Use the `.env.aws.template` file as a reference and configure:

- `DATABASE_URL`: Your RDS endpoint
- `DATABASE_USERNAME`: RDS master username
- `DATABASE_PASSWORD`: RDS master password
- `SPRING_PROFILES_ACTIVE`: Set to `aws`

## Security Considerations

- Use AWS Secrets Manager or Parameter Store for sensitive data
- Enable SSL for database connections
- Configure proper security groups
- Use IAM roles instead of access keys when possible
- Enable CloudWatch logging

## Monitoring

- Use AWS CloudWatch for application logs
- Set up alarms for health checks
- Monitor RDS metrics (CPU, connections, storage)

## Scaling

- Elastic Beanstalk: Configure auto-scaling based on CPU/memory
- ECS: Adjust task count and configure auto-scaling
- EC2: Use Auto Scaling Groups

## Cost Optimization

- Use Reserved Instances for production
- Enable RDS auto-scaling for storage
- Use appropriate instance types
- Implement CloudWatch alarms for cost monitoring
