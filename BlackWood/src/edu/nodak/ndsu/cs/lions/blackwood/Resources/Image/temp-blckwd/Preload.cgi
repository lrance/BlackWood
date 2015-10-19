#!/bin/sh
IFS="
"
echo "content-type: text/html"
echo
echo "<html>"
echo "<body bgcolor=ff0000>"
for i in `ls -1 *.[jgJG][piPI][gfGF]`
do
    echo "<IMG SRC=\""$i"\">"$i"</IMG>"
done
echo "</body>"
echo "</html>"
