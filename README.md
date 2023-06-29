#Java SSE Client

The Java SSE Client is a simple Java library that allows you to easily implement a Server-Sent Events client in your Java applications. 


## Features
* Lightweight and easy-to-use SSE client library for Java.
* Dependency free - implemented in pure Java SE

#Example
```java
        new SseClient(new URL("http://10.21.37.56:8080/events")).start(event->{
            System.out.println(event);
        });
```
