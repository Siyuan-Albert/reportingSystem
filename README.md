My improvements for reporting project:

Added a new feature that user can delete the report
Added a login page
Improved sync API performance:
Using mutithreading and sending request concurrently to both services.
Added a cache to avoid repeated generation. Once the report has been generated, you will get it from the cache.
Changed MongoDB to DynamoDB(PDF service)
Converted sync API into microservices by adding Eureka/Ribbon support.
Added Cloud config support.
Improved code coverage by adding more tests and generated a Jacobo report.
Configed the email service.
