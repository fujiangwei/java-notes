
if [ ! -d $1 ];then
    exit
fi

if [ ! -f "$2" ];then
    exit
fi

dupusedtxt="dupused.txt"
servicetxt="service.txt"

if [ -f "$servicetxt" ];then
    rm -rf $servicetxt
fi

if [ -f "$dupusedtxt" ];then
    rm -rf $dupusedtxt
fi

find $1 -maxdepth 1 -type f >> $servicetxt
scount=`wc -l $servicetxt | awk '{print $1}'`
if [ $scount -eq 0 ];then
    exit
fi

dupcount=`wc -l $2 | awk '{print $1}'`
if [ $dupcount -eq 0 ];then
    echo "congratuation, there is no dup jar"
    
    exit
fi

while read dupname
do
    arr=()
    i=0
    #echo "dupname >>>>> "$dupname
    while read service
    do
 	#echo "service >>>>> "$service
	sname=`echo $service | awk -F "/" '{print $NF}'`
	#echo "sname >>>>> "$sname
        count=`grep $dupname $service | wc -l`
	#echo "count >>>>> "$count
	if [ $count -ge 1 ];then
	    arr[i++]=`echo $sname|awk -F "." '{print $1}'`"_service"
	fi
    done < $servicetxt
    echo $dupname"---->"${arr[@]} >> $dupusedtxt
done < $2

path=`echo $2`
cp -rf $dupusedtxt ${path%/*}/

rm -rf $servicetxt
rm -rf $dupusedtxt
