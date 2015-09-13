#!/bin/bash

echo 'Copiando arquivos...'

rm br.com.smom*

cp ../source/smom/home/view/br.com.smom.home.view/target/br.com.smom.home.view-1.0.0.war .

cp ../source/smom/log/api/v1/br.com.smom.log.api/target/br.com.smom.log.api-1.0.0.jar .

cp ../source/smom/log/core/v1/br.com.smom.log.core/target/br.com.smom.log.core-1.0.0.jar .

cp ../source/smom/main/datasource/api/v1/br.com.smom.main.datasource.api/target/br.com.smom.main.datasource.api-1.0.0.jar .

cp ../source/smom/main/datasource/core/v1/br.com.smom.main.datasource.core/target/br.com.smom.main.datasource.core-1.0.0.jar .

cp ../source/smom/main/util/api/v1/br.com.smom.main.util.api/target/br.com.smom.main.util.api-1.0.0.jar .

cp ../source/smom/main/web_resources/view/br.com.smom.main.webresources.view/target/br.com.smom.main.webresources.view-1.0.0.war .

cp ../source/smom/user/view/br.com.smom.user.login.view/target/br.com.smom.user.view-1.0.0.war .

echo 'Arquivos copiados...'
