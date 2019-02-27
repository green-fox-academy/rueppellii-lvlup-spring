#!/bin/bash

sudo docker run \
--name lvlup \
--restart=always \
-d \
-e PROD_DB_URL=${prod_db_url} \
-e PROD_DB_USER=${prod_db_user} \
-e PROD_DB_PASSWORD=${prod_db_password} \
-e DEV_DB_USERNAME=sa \
-e DEV_DB_PASSWORD="" \
-e DEV_DB_URL=jdbc:h2:mem:testdb \
-e ACTIVE_PROFILE=${profile} \
foxyfox/lvlup-spring:${image}
