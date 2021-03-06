
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

initDir()
{
    if [ -d "$1" ];then
       rm -rf $1
       mkdir $1
       echo "rm&mkdir dir $1 suc"
    else
       mkdir $1
       echo "dir $1 mkdir suc"
    fi
}

initFile()
{
    if [ -f "$1" ];then
        rm -rf $1
        touch $1
        echo "rm&touch $1 suc"
    else
        touch $1
        echo "touch $1 suc"    
    fi
}

chkDupResult="chkDupResult"
unique="unique.txt"
dup="dup.txt"
extra="extra.txt"
uniquetmp="uniquetmp.txt"
extratmp="extratmp.txt"

initDir chkDupResult

initFile $unique
initFile $dup
initFile $extra
initFile $extratmp
initFile $uniquetmp
uniqueResult="uniqueResult.txt"
uniqueResultTmp="uniqueResultTmp.txt"
initFile $uniqueResult
initFile $uniqueResultTmp

# 用于标识结束，解决后面grep该字符串时匹配多个问题
prefix="//"
suffix="//"
while read name 
do
    # 是否包含SNAPSHOT字符串
    result=`echo $name | grep "SNAPSHOT"`
    if [[ $result != "" ]];then
	echo "SNAPSHOT name >>>>> "$name
    fi 
    if echo "$name"|grep "[0-9]" >/dev/null
    then
	rname=${name%-*}
	if [[ $result != "" ]];then
	    #echo $rname
            #版本号
	    version=`echo $rname | awk -F "-" '{print $NF}'`"-SNAPSHOT.jar"
	    rname=${rname%-*}
	    echo "SNAPSHOT rname >>>>> "$rname
        else
            version=`echo $name | awk -F "-" '{print $NF}'`
	fi
    else 
	echo "$name don't contains num"
	rname=$name
    fi
    
    num=`grep -w $prefix$rname$suffix unique.txt | wc -l`   
    if [ $num == 1 ];then
    	#echo "                  "
        #echo $name
	#echo $rname $num
	existedall=`grep -w $prefix$rname$suffix $uniquetmp`
	#echo "$rname is existed $existedall"
	ecount=`grep $existedall $extra | wc -l`
	if [ $ecount -eq 0 ];then
	   echo $existedall >> $extra
	   # 替换// 为空写到extratmp.txt
	   echo ${existedall////} >> $extratmp 
	fi 
	echo $name >> $dup 
    else 
	echo $prefix$rname$suffix >> $unique
        #保存第一个写入的版本号，便于grep -w $prefix$rname$suffix $uniquetmp做唯一处理
	echo $prefix$rname$suffix"-"$version >> $uniquetmp
        #保存完整的依赖信息
        echo $name >> $uniqueResultTmp
    fi 
done < allname.txt

echo "total: "$count
echo "ucount: " `wc -l $unique | awk '{print $1}'`
echo "dcount: " `wc -l $dup | awk '{print $1}'`
echo "ecount: " `wc -l $extratmp | awk '{print $1}'`
echo "uniqueresultTmpcount: " `wc -l $uniqueResultTmp | awk '{print $1}'`

#将extratmp.txt里的内容追加到$dup
#echo "==========分割线==========" >> $dup
cat $extratmp >> $dup
#将uinqueResultTmp中有多版本的去掉,输出uniqueResultTmp中不包含extratmp中的内容２⒍ㄏ虻�$uniqueResult中
grep -v -f $extratmp $uniqueResultTmp >> $uniqueResult
echo "$dup merge dcount: " `wc -l $dup | awk '{print $1}'`
echo "uniqueresultcount: " `wc -l $uniqueResult | awk '{print $1}'`

#cp -rf $unique $chkDupResult/
cp -rf $uniqueResult $chkDupResult/
cp -rf $dup $chkDupResult/
cp -rf $extratmp $chkDupResult/
#cp -rf allname.txt $chkDupResult/
#cp -rf $uniqueResultTmp $chkDupResult/

rm -rf $unique $dup $extra allname.txt $uniquetmp $extratmp $uniqueResult $uniqueResultTmp
