#!/bin/sh
PID=`ps axu | grep java | grep -w common_service |grep -v grep| awk '{printf $2}'`
# 不为空
if [ -n "$PID" ]
then
    kill -9 $PID
fi
