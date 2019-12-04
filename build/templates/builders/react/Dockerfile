FROM node:lts as builder
WORKDIR /app
ARG BUILD_COMMAND="npm run build"
ARG BUILD_DEST="build"
COPY ./package.json ./
RUN npm install
COPY src ./src
COPY public ./public
RUN ${BUILD_COMMAND}
RUN mkdir -p ${BUILD_DIRECTORY} && mv ${BUILD_DEST} ${BUILD_DIRECTORY}