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
    echo "$2 dir's jar rm suc"
else 
    mkdir $2
    echo "create $2 suc"
fi

filename=`echo $1 | awk -F "/" '{print $NF}'`

count=`ls -l $1 | grep "^-" | wc -l`
if [ $count -ge 1 ];then
    #将lib目录的所有文件名写入
    find $1/ -maxdepth 1 -type f > $filename.txt
fi

mkdir $2/filename
mkdir $2/lib

cp $filename.txt $2/filename/
rm -rf $filename.txt
cp $1/* $2/lib/
