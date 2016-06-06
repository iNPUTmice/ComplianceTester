#!/bin/bash
for account in `grep entry accounts.xml | awk -F '"' '{print $2}'`; do
  server=$(echo $account | awk -F '@' '{print $2}')
  echo "testing $server"
  java -jar target/ComplianceTester-0.1.jar $account > reports/$server.txt
done;
