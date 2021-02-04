# url-scrapper

scraps the given url which can be set as an arg and prints the site-map. 
at the moment there are two different solution attempt, first one implemented with Java8+Jsoup and second one with Python3+BeautifulSoup (*Python solution does not work as expected.*)

Both solution can be run with Docker:

``  
docker run 4fath/url-scrapper-java:0.0.1 <optional_url>  
``  
``  
docker run 4fath/url-scrapper-python:0.0.2 <optional_url>  
``  
