ARG public_folder

FROM nginx:alpine
ARG shared_directory
EXPOSE 80
COPY --from=builder ${shared_directory}  /usr/share/nginx/html
