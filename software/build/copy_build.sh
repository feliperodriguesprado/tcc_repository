#!/bin/bash

echo 'Copiando arquivos...'

rm br.com.smom*


# Customer:
cp ../source/smom/customer/api/v1/br.com.smom.customer.api/target/br.com.smom.customer.api-1.0.0.jar .
cp ../source/smom/customer/core/v1/br.com.smom.customer.core/target/br.com.smom.customer.core-1.0.0.jar .
cp ../source/smom/customer/view/br.com.smom.customer.register.view/target/br.com.smom.customer.register.view-1.0.0.war .


# Home
cp ../source/smom/home/view/br.com.smom.home.view/target/br.com.smom.home.view-1.0.0.war .


# Log
cp ../source/smom/log/api/v1/br.com.smom.log.api/target/br.com.smom.log.api-1.0.0.jar .
cp ../source/smom/log/core/v1/br.com.smom.log.core/target/br.com.smom.log.core-1.0.0.jar .


# Main Data Source
cp ../source/smom/main/datasource/api/v1/br.com.smom.main.datasource.api/target/br.com.smom.main.datasource.api-1.0.0.jar .
cp ../source/smom/main/datasource/core/v1/br.com.smom.main.datasource.core/target/br.com.smom.main.datasource.core-1.0.0.jar .


# Main Util
cp ../source/smom/main/util/view/br.com.smom.main.util.view/target/br.com.smom.main.util.view-1.0.0.war .
cp ../source/smom/main/util/api/v1/br.com.smom.main.util.api/target/br.com.smom.main.util.api-1.0.0.jar .
cp ../source/smom/main/util/core/v1/br.com.smom.main.util.core/target/br.com.smom.main.util.core-1.0.0.jar .


# User
cp ../source/smom/user/view/br.com.smom.user.login.view/target/br.com.smom.user.login.view-1.0.0.war .

echo 'Arquivos copiados...'
