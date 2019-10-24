echo "copy jar begin >>>>>>>>>>"
starttime=`date +'%Y-%m-%d %H:%M:%S'`
start_seconds=$(date --date="$starttime" +%s);

#是否存在jar包文件，需要配管做，将所有微服务依赖jar放大一个目录下
if [ ! -d "$1" ];
then 
    echo "$1 not existed"
    exit
fi

#是否存在依赖清单文件,需要在服务构建好之后生成
if [ ! -f "$2" ];then
    echo "$2 not existed"
    exit
fi

#删除原先jar目录
if [ -d "sharedlib" ];then
    rm -rf sharedlib/*
    echo "sharedlib dir's jar rm suc"
else 
    mkdir sharedlib
    echo "create sharedlib suc"
fi

#依赖jar清单文件数量
fnlistRow=`wc -l $2 | awk '{print $1}'`
echo "共有$fnlistRow个jar"
if [ $fnlistRow -ge 1 ];then
    while read name
    do
	rname=`echo $name | awk -F "/" '{print $NF}'`
	cp -rf $1/$rname sharedlib/
    done < $2
fi

endtime=`date +'%Y-%m-%d %H:%M:%S'`
end_seconds=$(date --date="$endtime" +%s);
sumTime=$((end_seconds-start_seconds));
echo ">>>>>> copy jar end " "$starttime ---> $endtime" "Total耗时:$sumTime s"

