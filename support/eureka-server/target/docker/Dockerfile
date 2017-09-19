FROM anapsix/alpine-java:8_jdk
MAINTAINER https://github.com/junneyang
ADD /eureka-server-1.3.0-RELEASE.jar //
EXPOSE 8001 8002
CMD ["java", "-jar", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote=true", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.port=8888", "/eureka-server-1.3.0-RELEASE.jar"]
