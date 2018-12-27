# url-shortener

An API to short URLs.

##Arquitectural decisions

The api was implemented with three REST endpoints: 

* POST '/short': is the endpoint responsible for generate the short url;
* GET '/{shortUrlId}' is the endpoint responsible for redirecting the shortUrl to the long original one; 
* GET '/statistics' is the endpoint that provides some statistics for the api usage (number of access - total, by endpoint)

The short url's are created randomly with Strings of a pre-defined size. 

This size is configured as an ambient variable on application.yml file which can be easily changed, demanding to only restart the application and not redeploy it.

## Requirements

The following technologies are required in order to get this project running:

* Docker
    * Java 1.8
    * Mysql

## Run the project

1. Make sure you have docker installed and running


2. Clone the code from github
```
git clone https://github.com/lauradebella/url-shortener.git
cd url-shortener
```

3. Run the project
```
docker-compose up
```

## Try it

```bash
curl -XPOST localhost:8080/short -H 'Content-Type: application/json' -d '{
  "longUrl": "https://www.google.com/"
}'
```

[No curl?](https://onlinecurl.com/)

You can also use the postman collection on /postman to reach the endpoints using postman.

For the enlarge endpoint, you can try it directly from your browser and you'll be redirected to the original url.


