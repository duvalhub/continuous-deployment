FROM nginx:alpine
ARG shared_directory
EXPOSE 80
RUN rm -r /usr/share/nginx/html
COPY --from=builder ${shared_directory}  /usr/share/nginx/html
