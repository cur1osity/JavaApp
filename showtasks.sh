#!/usr/bin/env bash

export CATALINA_HOME=/etc/tomcat8

stop_tomcat()
{
  $CATALINA_HOME/bin/catalina.sh stop -security
}

run_server() {
bash runcrud.sh
}

start_firefox() {
firefox http://localhost:8080/crud/v1/tasks
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if run_server; then
   start_firefox
else
   stop_tomcat
   fail
fi