FROM node:lts as builder
ARG shared_directory
WORKDIR /app
ARG BUILD_COMMAND="npm run build"
ARG BUILD_DEST="build"
COPY ./package.json ./
RUN npm install
COPY src ./src
COPY public ./public
RUN ${BUILD_COMMAND}
RUN echo ${BUILD_DIRECTORY}
RUN echo ${BUILD_DEST}
RUN mkdir -p ${shared_directory} && mv ${BUILD_DEST} ${shared_directory}
