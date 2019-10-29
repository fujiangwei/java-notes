echo $1
echo $2

if [ ! -d "$1" ];
then 
    echo "$1 not existed"
    exit
fi

if [ -d "$2" ];
then  
    rm -rf $2/*
    echo "$2 dir rm suc"
else 
    mkdir $2
    echo "create $2 suc"
fi

dircount=`ls -l $1 | grep "^d" | wc -l`
if [ $dircount -ge 1 ];then
    #将lib目录的所有文件名写入
    find $1/ -maxdepth 1 -type d > snlist.txt
fi

mkdir $2/filename
mkdir $2/lib

while read sn
do 
    echo ">>>>>>>sn is $sn"
    if [ ! -d "$sn" ];then
	echo $sn "not existed"
    else 
	filename=`echo $sn | awk -F "/" '{print $NF}'`
	echo ">>>>>>> filename is $filename"
	if [ ! $filename ];then
	    echo "cur filename is empty"
	    continue
	fi
	count=`ls -l $sn | grep "^-" | wc -l`
	if [ $count -ge 1 ];then
            find $sn/ -maxdepth 1 -type f > $filename.txt
	fi

	cp $filename.txt $2/filename/
	rm -rf $filename.txt
	cp $sn/* $2/lib/
    fi
done < snlist.txt

rm -rf snlist.txt
