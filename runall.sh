#!/bin/bash
function pwait() {
    while [ $(jobs -p | wc -l) -ge $1 ]; do
        sleep 1
    done
}
for account in `grep entry accounts.xml | awk -F '"' '{print $2}'`; do
  server=$(echo $account | awk -F '@' '{print $2}')
  echo "testing $server"
  java -jar target/ComplianceTester-0.2.jar $account > reports/$server.txt&
  pwait 5
done;

