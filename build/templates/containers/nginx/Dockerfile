ARG public_folder

FROM nginx:alpine
EXPOSE 80
COPY --from=builder ${BUILD_DIRECTORY}  /usr/share/nginx/html
