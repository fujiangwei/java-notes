echo "copy jar begin >>>>>>>>>>"
starttime=`date +'%Y-%m-%d %H:%M:%S'`
start_seconds=$(date --date="$starttime" +%s);

#是否存在jar包文件，需要配管做，将所有微服务依赖jar放大一个目录下
if [ ! -d "$1" ];
then 
    echo "$1 not existed"
    exit
fi

#是否存在依赖清单文件,需要配管出包更新
if [ ! -f "$3" ];then
    echo "$3 not existed"
    exit
fi

#删除原先jar目录
if [ -d "$2" ];then
    rm -rf $2/*
    echo "$2 dir's jar rm suc"
else 
    mkdir $2
    echo "create $2 suc"
fi

#fnlist.txt文件行数
fnlistRow=`wc -l fnlist.txt | awk '{print $1}'`
echo "共有$fnlistRow个jar"
if [ $fnlistRow -ge 1 ];then
    while read name
    do
	#将名字按'/'格式切分区最后一个
	rname=`echo $name | awk -F "/" '{print $NF}'`
	#echo $rname
	cp -rf $1/$rname $2/
    done < fnlist.txt
fi

endtime=`date +'%Y-%m-%d %H:%M:%S'`
end_seconds=$(date --date="$endtime" +%s);
sumTime=$((end_seconds-start_seconds));
echo ">>>>>> copy jar end " "$starttime ---> $endtime" "Total耗时:$sumTime s"

