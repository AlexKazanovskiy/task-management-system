At first make the similar request:
```sh
curl -X POST -vu atms:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=YOUR_PASSWORD&username=YOUR_USERNAME&grant_type=password&scope=read%20write&client_secret=secret&client_id=atms"
```
This request will return something like this:
```json
{
  "access_token": "ff16372e-38a7-4e29-88c2-1fb92897f558",
  "token_type": "bearer",
  "refresh_token": "f554d386-0b0a-461b-bdb2-292831cecd57",
  "expires_in": 43199,
  "scope": "read write"
}
```

Use the `access_token` returned in the previous request to make the authorized request to the protected endpoint:

```sh
curl http://localhost:8080/api/developer -H "Authorization: Bearer ff16372e-38a7-4e29-88c2-1fb92897f558"
```

After the specified time period, the `access_token` will expire. Use the `refresh_token` that was returned in the original OAuth authorization to retrieve a new `access_token`:

```sh
curl -X POST -vu atms:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "grant_type=refresh_token&refresh_token=f554d386-0b0a-461b-bdb2-292831cecd57&client_secret=secret&client_id=atms"
```


