#!/bin/bash
reports=(*.txt)
pos=$(( ${#reports[*]} - 1 ))
last=${reports[$pos]}
echo -n '{'
for report in "${reports[@]}"; do
  server=$(echo $report | sed 's/.txt//')
  echo -n "\"$server\":"
  lines=`grep -B 16 'Conversations Compliance Suite: ' $report | grep running | wc -l`
  context=`echo $lines + 2 | bc`
  echo -n '{' ; grep -B $context 'Conversations Compliance Suite: ' $report | head -n $lines | sed 's/running //' | sed 's/…//' | sed -e 's/ \{3,\}/\t\t/g' | awk -v lines="$lines" -F '\t\t' '{ printf "\"" $1 "\":\""  $2 "\""; if (NR != lines) printf "," }'; echo -n '}'
  if [[ $report != $last ]]; then echo -n ','; fi;
done;
echo '}'
