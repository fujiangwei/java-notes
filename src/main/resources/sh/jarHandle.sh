    #第一个传参为0或者空时不进行处理
if [[ $1 -eq 0 || ! $1 ]];then
	echo "nothing to do."
    exit
fi

starttime=`date +'%Y-%m-%d %H:%M:%S'`
start_seconds=$(date --date="$starttime" +%s);

#shared目录下的jar包和单个微服务依赖的jar清单列表
libPath="/shared/lib"
fileNamePath="/shared/filename"

if [ ! -d $libPath ];then
    echo "$libPath is not dir"
    exit
fi

#删除文件
rmFile()
{
    if [ -f "$1" ];then
        rm -rf $1
        echo "rm $1 suc"
    fi
}
#初始化目录
initDir()
{
    if [ -d "$1" ];then
       rm -rf $1
       mkdir $1
    else
       mkdir $1
    fi
}
#初始化文件
initFile()
{
    if [ -f "$1" ];then
        rm -rf $1
        touch $1
    else
        touch $1
    fi
}

#shared/lib目录下的jar文件数
count=`ls -l $libPath | grep "^-" | wc -l`
#shared/filename目录下的文件数
if [ -d $fileNamePath ];then
    fcount=`ls -l $fileNamePath | grep "^-" | wc -l`
fi

# allname.txt用于存放所有的jar名，fileName.txt用于存放微服务清单列表名称
rmFile allname.txt
rmFile fileName.txt 

#将shared/lib下的所有jar名写入allname.txt
if [ $count -ge 1 ];then
    find $libPath -maxdepth 1 -type f | awk -F "/" '{print $NF}' > allname.txt
else 
    echo "$libPath dir's file is null"
    exit
fi

#定义变量
#处理完之后的目录名
jarResult="/jar"
#记录经过处理成唯一名称的jar依赖名称
unique="unique.txt"
#记录存在多版本的jar依赖名称
dup="dup.txt"
#存在多版本的jar依赖第一次记录在unique.txt中的名称，用于后面将其归并到dup文件中
extra="extra.txt"
#记录只有一个版本号的jar依赖的名称
uniqueResult="uniqueResult.txt"
#临时文件
uniquetmp="uniquetmp.txt"
extratmp="extratmp.txt"
uniqueResultTmp="uniqueResultTmp.txt"

#初始化jarResult目录
initDir $jarResult

#将shared/filename目录下的服务依赖清单列表名创建对应的目录
if [ $fcount -ge 1 ];then
    find $fileNamePath -maxdepth 1 -type f | awk -F "/" '{print $NF}' > fileName.txt
else
    echo "$fileNamePath dir's file is null"
fi
#创建各个微服务对应的jar目录
while read fname
do 
    fileName=`echo $fname | awk -F "/" '{print $NF}' | awk -F "." '{print $1}'`"_service"
    #创建单个微服务jar依赖目录，处理如果存在多版本的jar依赖时，将该服务用到的复制到该目录下
    initDir $fileName
    mv $fileName $jarResult/
done < fileName.txt
#只有一个版本依赖目录，即存放的是uniqueResult中记录的jar依赖
initDir common_all
mv common_all $jarResult/

#初始化文件
initFile $unique
initFile $dup
initFile $extra
initFile $extratmp
initFile $uniquetmp
initFile $uniqueResult
initFile $uniqueResultTmp

# 用于标识结束，加在jar不带版本的名称前后，解决后面grep该字符串时匹配多个问题
prefix="//"
suffix="//"
#读取allname.txt
while read name 
do
    # 是否包含SNAPSHOT字符串，eg；IS2WEB-cloud-api-0.0.1-SNAPSHOT.jar
    result=`echo $name | grep "SNAPSHOT"`
    if [[ $result != "" ]];then
	echo "SNAPSHOT name >>>>> "$name
    fi 
    #是否包含数字
    if echo "$name"|grep "[0-9]" >/dev/null
    then
        #去除版本号
	rname=${name%-*}
	if [[ $result != "" ]];then
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
    #从unique.txt查找是否存在jar依赖
    num=`grep -w $prefix$rname$suffix $unique | wc -l`   
    if [ $num == 1 ];then
        #$uniquetmp查找是否存在，用于将unique中存在多版本的jar去除
	existedall=`grep -w $prefix$rname$suffix $uniquetmp`
        #从$extra中查找，如果不存在就将其写入
	ecount=`grep $existedall $extra | wc -l`
	if [ $ecount -eq 0 ];then
	   echo $existedall >> $extra
	   # 替换//为空写到extratmp.txt
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

echo "all jar total: $count"
#将extratmp.txt里的内容追加到$dup
cat $extratmp >> $dup
#将uinqueResultTmp中有多版本的去掉,输出uniqueResultTmp中不包含extratmp中的内容到niqueResult中
grep -v -f $extratmp $uniqueResultTmp >> $uniqueResult
echo "multiple version jar total: " `wc -l $dup | awk '{print $1}'`
echo "single version jar total: " `wc -l $uniqueResult | awk '{print $1}'`

#查找有多版本的依赖的具体对应的微服务
#记录多版本的jar依赖被哪些服务使用，便于将其应用到具体服务的指定依赖目录下
dupusedtxt="dupused.txt"
rmFile $dupusedtxt
#单版本依赖放到common_all目录
while read uniqueJar
do 
    cp -rf $libPath/$uniqueJar $jarResult/common_all/
done < $uniqueResult

#多版本依赖jar信息
dupcount=`wc -l $dup | awk '{print $1}'`
if [ $dupcount -eq 0 ];then
    echo "============================================================="
    echo "|||||| Congratuations, there is no dup jar in your pro ||||||" 
    echo "============================================================="
else
    while read dupname
    do
        arr=()
        i=0
        while read service
        do
            sname=`echo $service | awk -F "/" '{print $NF}'`
            count=`grep $dupname $fileNamePath/$service | wc -l`
            if [ $count -ge 1 ];then
                singleLib=`echo $sname|awk -F "." '{print $1}'`"_service"
                # 将该依赖加入当前的微服务依赖目录
                cp -rf $libPath/$dupname $jarResult/$singleLib/
                arr[i++]=$singleLib
            fi
        done < fileName.txt
        echo $dupname"---->"${arr[@]} >> $dupusedtxt
    done < $dup
    mv $dupusedtxt $jarResult/
fi

#移除操作
mv $uniqueResult $dup $extratmp $jarResult/
rm -rf $unique $extra allname.txt $uniquetmp $uniqueResultTmp $dupusedtxt fileName.txt

endtime=`date +'%Y-%m-%d %H:%M:%S'`
end_seconds=$(date --date="$endtime" +%s);
sumTime=$((end_seconds-start_seconds));
echo "end ==========> " "$starttime ---> $endtime" "TotalTime:$sumTime s"
