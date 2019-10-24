PID=`ps axu | grep java | grep common_service |grep -v grep| awk '{printf $2}'`
if [ -n "$PID" ]
then
   echo "common_service is running."
   echo "If you want to restart web ,please try stop."
else

        nohup java -Xms512m -Xmx1024m -jar tcommon_service.jar jfile=config/application.properties >tomcat.log 2>&1  &
fi
