#!/bin/bash
reports=(*.txt)
pos=$(( ${#reports[*]} - 1 ))
last=${reports[$pos]}
echo -n '{'
for report in "${reports[@]}"; do
  server=$(echo $report | sed 's/.txt//')
  echo -n "\"$server\":"
  echo -n '{' ; tail -n 17 $report | head -n 12 | sed 's/running //' | sed 's/…//' | awk -F '\t\t' '{ printf "\"" $1 "\":\""  $2 "\""; if (NR != 12) printf "," }'; echo -n '}'
  if [[ $report != $last ]]; then echo -n ','; fi;
done;
echo '}'
