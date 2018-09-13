#replace.sh 构建完成后备份旧版本和拷贝新版本
#!/bin/bash
if [ ! -f "/usr/soft/xxx/history/" ]
then
    mkdir /usr/soft/xxx/history/
    echo "make directory 'history' successfully"
else
    echo ""
fi
if [ -f "/usr/soft/xxx/xxx.jar" ]
then
   mv /usr/soft/xxx/xxx.jar /usr/soft/xxx/history/xxx.jar.`date +%Y%m%d%H%M%S`
fi
mv /var/lib/jenkins/workspace/xxx/xxx/target/xxx.jar /usr/soft/xxx/xxx.jar


#tail -fn 0 --pid=`ps uxh|grep 'sed[[:space:]]\/Started'|awk '{print $2}'|sort -nr|head -1` xxx.log|sed /Started[[:space:]][[:alpha:]][[:space:]]in/Q
#解释：
#1、sed /Started[[:space:]]in/Q，执行命令，查找字符串'Started xxx in'，也可以换成其他正则表达式来匹配，Q代表查到后退出sed
#2、--pid参数，检测某一个pid，当检测到pid停止的时候，停止tail
#3、ps uxh查询当前用户的进程
#4、grep 'sed[[:space:]]\/Started'查找1里面执行的sed的进程，如果关键字不是Started的话，这里也需要进行更换
#5、awk '{print $2}'获取每行结果的第二个词，也就是pid
#6、sort -nr将结果进行倒序排序
#7、head -1取第一个结果，取最大的pid
#8、从3到7执行过后，取到的pid大致可以认定为1里面sed的pid
#9、tail -fn 0 --pid=`...` xxx.log，pid等号后的 ` 不是引号，是ESC下面那个按键，其中-n 0的作用是防止上一次日志的结尾对本次打印产生影响