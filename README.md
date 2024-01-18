# Marketplace
## AnotaAi Backend Challenge

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)

## Introduction

This project is a marketplace API built using Java, Java Spring, AWS Simple Queue Service, MongoDB, and AWS Simple Storage Service. It was originally based on the AnotaAi Backend Challenge but has been adapted to use Java as the backend language. The main functionalities of the project include managing products and categories through various API endpoints.

## Installation

1. Install dependencies with Maven

2. Create a configuration with your runtime environment variables with your AWS Credentials that are used in `application.properties`

```yaml
aws.region=us-east-1
aws.accessKeyId=${AWS_KEY_ID}
aws.secretKey=${AWS_SECRET}
```

**Config Values**

```yaml
AWS_KEY_ID=VALUE;AWS_SECRET=VALUE2
```
## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**API PRODUCT**
```markdown
POST /api/product - Create a new product
GET /api/product - Retrieve all products
PUT /api/product/{id} - Updates a product
DELETE /api/product/{id} - Delete a product
```

**BODY**
```json
{
  "title": "Product Title",
  "description": "Product Description",
  "ownerId": "12345",
  "categoryId": "659d558b0304df732ddd4587",
  "price": 10000
}
```

**API CATEGORY**
```markdown
POST /api/category - Create a new category
GET /api/category - Retrieve all categories
PUT /api/category/{id} - Updates a category
DELETE /api/category/{id} - Delete a category
```

```json
{
  "title": "Category Title",
  "description": "Category Description",
  "ownerId": "1234"
}
```

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request to the repository.

When contributing to this project, please follow the existing code style, [commit conventions](https://www.conventionalcommits.org/en/v1.0.0/), and submit your changes in a separate branch.

## Requisites

### User Stories:

- ~As a user, I want to register a product with its owner, so that I can access its data in the future (title, description, price, category, owner ID).~

- ~As a user, I want to register a category with its owner, so that I can access its data in the future (title, description, owner ID).~

- ~As a user, I want to associate a product with a category.~

- ~As a user, I want to update the data of a product or category.~

- ~As a user, I want to delete a product or category from my catalog.~

- ~A product can only be associated with one category at a time.~

- ~Assume that products and categories belong only to one owner.~

- Keep in mind that this is an online product catalog, which means there will be multiple requests for editing items/categories per second, as well as accessing the catalog search endpoint.

- Consider the product catalog as a JSON compilation of all available categories and items owned by a user. This way, the catalog search endpoint does not need to fetch information from the database.

- Whenever there is a change in the product catalog, publish this change to the "catalog-emit" topic in the AWS SQS service.

- Implement a consumer that listens to catalog changes for a specific owner.

- When the consumer receives a message, search the database for that owner's catalog, generate the catalog JSON, and publish it to an AWS S3 service bucket.

You need to develop this test using the following technologies:

MongoDB for the database.
AWS SQS for the catalog change notifications.
AWS S3 for storing the catalog JSON.
Java for the backend.
Spring as the web framework.