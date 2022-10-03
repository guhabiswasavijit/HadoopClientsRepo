FROM node:17-alpine3.14
LABEL maintainer="Avijit GuhaBiswas <guhabiswas.avijit@gmail.com>"
LABEL name="admin/openjdk-alpine-hfds"

RUN apk add bash curl sudo\
    && apk add openjdk8\
    && addgroup hadoop\
    && adduser --ingroup hadoop --disabled-password admin \
	&& echo 'admin ALL=(ALL) NOPASSWD:ALL' >>/etc/sudoers
	

USER admin
RUN mkdir -p /home/admin/JavaBinaries/logs
WORKDIR /home/admin/JavaBinaries
COPY newFile.txt /home/admin/JavaBinaries/newFile.txt

ENV SHELL=/bin/bash \
	USER=admin
COPY target/webhdfs-0.0.1-SNAPSHOT.jar webhdfs-0.0.1-SNAPSHOT.jar


CMD ["java", "-jar", "webhdfs-0.0.1-SNAPSHOT.jar"]