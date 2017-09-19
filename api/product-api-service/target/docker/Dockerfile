FROM anapsix/alpine-java:8_jdk
MAINTAINER https://github.com/junneyang
ADD /opt/java/workspace/xproject/config/1.0.0/auth-server-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/auth-server.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/eureka-server-eureka01-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/eureka-server-eureka01.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/eureka-server-eureka02-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/eureka-server-eureka02.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/hystrix-dashboard-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/hystrix-dashboard.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-api-service-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-api-service.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-composite-service-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-composite-service.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-service-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/product-service.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/review-service-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/review-service.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/turbine-server-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/turbine-server.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/zipkin-server-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/zipkin-server.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/zuul-server-docker.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/1.0.0/zuul-server.properties /opt/java/workspace/xproject/config/1.0.0/
ADD /opt/java/workspace/xproject/config/banner.txt /opt/java/workspace/xproject/config/
ADD /opt/java/workspace/xproject/config/elk-config/logstash.config /opt/java/workspace/xproject/config/elk-config/
ADD /opt/java/workspace/xproject/config/key/client.cer /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/key/client.p12 /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/key/client.truststore /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/key/jwtkeystore.jks /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/key/server.cer /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/key/server.keystore /opt/java/workspace/xproject/config/key/
ADD /opt/java/workspace/xproject/config/logback-base.xml /opt/java/workspace/xproject/config/
ADD /product-api-service-1.3.0-RELEASE.jar //
EXPOSE 8080
CMD ["java", "-jar", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote=true", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.port=8888", "/product-api-service-1.3.0-RELEASE.jar"]
VOLUME /opt/java/workspace/xproject/config
