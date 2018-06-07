#!/bin/bash

# 当前脚本路径
current_file_path=$(cd `dirname $0`; pwd)
cd "$current_file_path"
cd ../
PROJECT_HOME=`pwd`
JAR_NAME=''
for loop in `ls $PROJECT_HOME/*.jar`;do
   JAR_NAME=${loop}
   break
done
SERVER_NAME=${JAR_NAME%%-*} #  %% 号截取，删除右边字符，保留左边字符
PID=$(ps aux | grep ${JAR_NAME} | grep -v grep | awk '{print $2}' )
SUF=".pid"

start(){
  if [ ! -n "$PID" ]; then
    echo "Usage JAVA_HOME: $JAVA_HOME"
    echo "正在启动 $SERVER_NAME"
    nohup $JAVA_HOME/bin/java -jar ${JAR_NAME} > /dev/null 2>&1 &
    echo $! > ${SERVER_NAME}${SUF}
    echo "启动完成 $SERVER_NAME"
    sleep 1s
    tail -f $PROJECT_HOME/logs/$SERVER_NAME.log
  else
      echo "$SERVER_NAME (pid  $PID) 正在运行..."
  fi
}


status(){
   if [ ! -n "$PID" ]; then
     echo "$SERVER_NAME 已停止"
   else
     echo "$SERVER_NAME (pid  $PID) 正在运行..."
   fi
}


stop(){
    if [ ! -n "$PID" ]; then
     echo "$SERVER_NAME 已停止"
    else
      echo "$SERVER_NAME 停止"
      kill -9 $PID
      rm ${SERVER_NAME}${SUF}
    fi
}

restart(){
    stop
    sleep 1s
    start
}

case $1 in
  start)
    start
    ;;
  stop)
    stop
    ;;
  status)
    status
    ;;
  restart)
    restart
    ;;
  *)
    echo $"Usage: {start|stop|status|restart}"
esac

exit 0;
