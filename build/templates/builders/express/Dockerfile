FROM node as builder
WORKDIR /builder
ARG build_directory
COPY package.json ./
RUN npm install
COPY src ./src
RUN npm run build \
&& mv node_modules build/node_modules \
&& mkdir -p ${build_directory} \
&& rm -r ${build_directory} \
&& mv build ${build_directory}