My improvements for reporting project:

1. Added a new feature that user can delete the report
2. Added a login page
3. Improved sync API performance:
   1. Using mutithreading and sending request concurrently to both services.
   2. Added a cache to avoid repeated generation. Once the report has been generated, you will get it from the cache.
4. Changed MongoDB to DynamoDB(PDF service)
5. Converted sync API into microservices by adding Eureka/Ribbon support.
6. Added Cloud config support.
7. Improved code coverage by adding more tests and generated a Jacobo report.
8. Configed the email service.
