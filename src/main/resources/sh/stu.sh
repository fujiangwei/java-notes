
echo $1


if [ ! -d $1 ];then
    echo "$1 is not dir"
    exit
fi

count=`ls -l $1 | grep "^-" | wc -l`

if [ -f "allname.txt" ];then
    rm -rf allname.txt
    echo "rm allname.txt suc"
fi

if [ $count -ge 1 ];then
    find $1 -maxdepth 1 -type f | awk -F "/" '{print $NF}' > allname.txt
else 
    echo "$1 dir's file is null"
    exit
fi

unique="unique.txt"
dup="dup.txt"
extra="extra.txt"

if [ -d "chkResult" ];then
   rm -rf chkResult
   mkdir chkResult
   echo "rm&mkdir dir chkResult suc"
else
   mkdir chkResult
   echo "dir chkResult mkdir suc"
fi

if [ -f "$unique" ];then
    echo -n "" > $unique
    echo "clear $unique suc"
else 
    touch $unique
    echo "touch $unique suc"    
fi

if [ -f "$dup" ];then
    rm -rf $dup
    touch $dup
    echo "rm&touch $dup suc"
else 
    touch $dup
    echo "touch $dup suc" 
fi

if [ -f "$extra" ];then
    rm -rf $extra
    touch $extra
    echo "rm&touch $extra suc"
else 
    touch $extra
    echo "touch $extra suc" 
fi
# 用于标识结束，解决后面grep该字符串时匹配多个问题
prefix="//"
suffix="//"
while read name 
do
    if echo "$name"|grep "[0-9]" >/dev/null
    then
	rname=${name%-*}
        version=`echo $name | awk -F "-" '{print $NF}'`
    else 
	echo "$name don't contains num"
	rname=$name
    fi
    
    num=`grep -w $prefix$rname$suffix unique.txt | wc -l`   
    if [ $num == 1 ];then
    	echo "                  "
        echo $name
	echo $rname $num
	existedall=`grep -w $rname uall.txt`
        echo "$rname is existed $existedall"
	ecount=`grep $existedall $extra | wc -l`
	if [ $ecount -eq 0 ];then
	   #echo "unique.txt version is "$existedall
	   echo $existedall >> $extra 
	fi 
	echo $name >> $dup
    else 
	echo $prefix$rname$suffix >> $unique
        echo $prefix$rname$suffix$version >> uall.txt
    fi 
done < allname.txt

echo "total: "$count
echo "ucount: " `wc -l $unique | awk '{print $1}'`
echo "dcount: " `wc -l $dup | awk '{print $1}'`
echo "ecount: " `wc -l $extra | awk '{print $1}'`

cp -rf $unique chkResult/
cp -rf $dup chkResult/
cp -rf $extra chkResult/
cp -rf allname.txt chkResult/

rm -rf $unique $dup $extra allname.txt
