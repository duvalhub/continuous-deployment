FROM node:8 as builder
WORKDIR /builder
ARG build_directory
ARG build_destination
COPY package.json ./
RUN npm install
COPY src ./src
RUN npm run build \
&& mv node_modules ${build_destination}/node_modules \
&& mkdir -p ${build_directory} \
&& rm -r ${build_directory} \
&& mv ${build_destination} ${build_directory}