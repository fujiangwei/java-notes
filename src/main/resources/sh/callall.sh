

echo "===========begin============"


echo "===========单个微服务jar名清单及拷贝到同一个目录begin============"
# 处理每个微服务下的jar依赖名字和jar
source ./handlejar.sh $1 shared
echo "===========单个微服务jar名清单及拷贝到同一个目录over============"

echo "                                                                "

echo "===========检查哪些jar存在版本冲突begin============"
# 检查哪些jar存在版本冲突
sh ./chkdup.sh shared/lib
echo "===========检查哪些jar存在版本冲突over============"

echo "                                                                "

echo "===========哪些服务用到多版本jar begin============"
# 哪些服务用到多版本jar
. ./finddupuse.sh shared/filename/ chkDupResult/dup.txt
echo "===========哪些服务用到多版本jar over============"

echo "                                                                "

echo "===========end============"

