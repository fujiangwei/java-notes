

read -p "please enter a string" str
if echo "$str"|grep "[a-zA-Z]" >/dev/null &&echo "$str"|grep "[0-9]" >/dev/null
then
    echo "yes"
else
    echo "no"
fi
