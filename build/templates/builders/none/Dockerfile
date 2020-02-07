FROM alpine as builder
WORKDIR /builder
ARG build_directory
ARG build_destination
COPY src ${build_destination}
RUN mkdir -p ${build_directory} \
&& rm -r ${build_directory} \
&& mv ${build_destination} ${build_directory}