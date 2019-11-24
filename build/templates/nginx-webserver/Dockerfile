FROM node:lts as builder
WORKDIR /tmp/dockerbuild
COPY ./package.json ./
RUN npm install
COPY src ./src
COPY public ./public
RUN npm run build

FROM nginx
EXPOSE 8080
ARG TEMPLATE_PATH=.
WORKDIR /etc/nginx
COPY --from=builder /tmp/dockerbuild/build /usr/share/frontend/html
COPY ${TEMPLATE_PATH}/nginx.conf /etc/nginx/conf.d/default.conf
COPY ${TEMPLATE_PATH}/init_container.sh ./
ENTRYPOINT [ "/bin/bash", "init_container.sh", "/etc/nginx/conf.d/default.conf" ]
CMD ["nginx", "-g", "daemon off;"]