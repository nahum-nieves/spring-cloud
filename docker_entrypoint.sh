#!/bin/bash
if env | grep -q ^URL_DATASOURCE=
then
  echo  "URL_DATASOURCE CORRECTLY CONFIGURED";
else
  echo  "URL_DATASOURCE NOT SPECIFIED";
  exit;
fi
if env | grep -q ^SERVER_PORT=
then
  echo  "SERVER_PORT CORRECTLY CONFIGURED";
else
  echo  "SERVER_PORT NOT SPECIFIED";
  exit;
fi
if env | grep -q ^DATABASE_PWD=
then
  echo  "DATABASE_PWD CORRECTLY CONFIGURED";
else
  echo  "DATABASE_PWD NOT SPECIFIED";
  exit;
fi
if env | grep -q ^DATABASE_USER=
then
  echo  "DATABASE_USER CORRECTLY CONFIGURED";
else
  echo  "DATABASE_USER NOT SPECIFIED";
  exit;
fi
java -jar /usr/local/lib/demo.jar