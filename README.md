# Retail Store Management System

Enlace del Swagger

http://localhost:8096/swagger-ui/index.html

A continuación tiene el JSON de la API RESTful 

__POST__ /api/v1/salesorders

```json
{
  "customerId": "3fa85f64-5717-4562-b3fc-2c963f66af23",
  "items": [
    {
      "productId": "3fa85f64-5717-4562-b3fc-2c963f66af41",
      "quantity": 2,
      "unitAmount": 136,
      "currency": "USD"
    },
    {
      "productId": "3fa85f64-5717-4562-b3fc-2c963f66af57",
      "quantity": 3,
      "unitAmount": 25,
      "currency": "USD"
    },
    {
      "productId": "3fa85f64-5717-4562-b3fc-2c963f66af12",
      "quantity": 2,
      "unitAmount": 50,
      "currency": "USD"
    }
  ]
}
```