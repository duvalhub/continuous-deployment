ARG TOTO=minou

FROM node:lts as builder
WORKDIR /tmp/dockerbuild
ARG TOTO=pitou
RUN echo ${TOTO}

FROM nginx
EXPOSE 80
ARG TOTO
RUN echo ${TOTO}