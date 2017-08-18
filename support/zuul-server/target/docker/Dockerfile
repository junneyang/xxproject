FROM anapsix/alpine-java:8_jdk
MAINTAINER https://github.com/junneyang
ADD /zuul-server-1.0.0-RELEASE.jar //
EXPOSE 5555
CMD ["java", "-jar", "-Dcom.sun.management.jmxremote.local.only=false", "-Dcom.sun.management.jmxremote=true", "-Dcom.sun.management.jmxremote.authenticate=false", "-Dcom.sun.management.jmxremote.ssl=false", "-Dcom.sun.management.jmxremote.port=8888", "/zuul-server-1.0.0-RELEASE.jar"]
