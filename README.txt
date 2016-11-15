#search system info
http://localhost:8080/assist/sys/search?timeStr=2016-11-11&page.pageSize=2&page.currentPage=1
timeStr
page.currentPage(the first page is 1)
page.pageSize

[{"id":501,"createTime":1478850360000,"topInfo":"top - 15:46:01 up  1:48,  3 users,  load average: 0.30, 0.60, 0.67\nTasks: 240 total,   1 running, 239 sleeping,   0 stopped,   0 zombie\n%Cpu(s):  9.7 us,  1.2 sy,  0.0 ni, 86.7 id,  2.3 wa,  0.0 hi,  0.1 si,  0.0 st\nKiB Mem:   8068296 total,  7491668 used,   576628 free,      896 buffers\nKiB Swap:  2109436 total,        0 used,  2109436 free.  4124552 cached Mem\n\n  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n 2056 liangyh   20   0 4826184 1.183g  51072 S 125.0 15.38  32:58.75 java\n 2175 liangyh   20   0 1372168 394112  96408 S 6.250 4.885   4:42.98 firefox\n    1 root      20   0   33620   5772   3440 S 0.000 0.072   0:02.45 systemd\n    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n    3 root      20   0       0      0      0 S 0.000 0.000   0:00.11 ksoftirqd+\n    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n    7 root      20   0       0      0      0 S 0.000 0.000   0:08.24 rcu_preem+\n    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched\n    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n   10 root      20   0       0      0      0 S 0.000 0.000   0:02.23 rcuop/0\n   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0\n   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 migration+\n   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.02 watchdog/0\n   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.02 watchdog/1\n   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 migration+\n   17 root      20   0       0      0      0 S 0.000 0.000   0:00.06 ksoftirqd+\n   19 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/1+\n   20 root      20   0       0      0      0 S 0.000 0.000   0:00.97 rcuop/1\n"},{"id":502,"createTime":1478850363000,"topInfo":"top - 15:46:04 up  1:48,  3 users,  load average: 0.30, 0.60, 0.67\nTasks: 240 total,   1 running, 239 sleeping,   0 stopped,   0 zombie\n%Cpu(s):  9.7 us,  1.2 sy,  0.0 ni, 86.7 id,  2.3 wa,  0.0 hi,  0.1 si,  0.0 st\nKiB Mem:   8068296 total,  7493324 used,   574972 free,      896 buffers\nKiB Swap:  2109436 total,        0 used,  2109436 free.  4124780 cached Mem\n\n  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n 2056 liangyh   20   0 4826184 1.185g  51072 S 118.8 15.40  33:02.47 java\n 6427 liangyh   20   0 4380140 750576  15408 S 6.250 9.303   0:31.14 java\n    1 root      20   0   33620   5772   3440 S 0.000 0.072   0:02.45 systemd\n    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n    3 root      20   0       0      0      0 S 0.000 0.000   0:00.11 ksoftirqd+\n    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n    7 root      20   0       0      0      0 S 0.000 0.000   0:08.24 rcu_preem+\n    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched\n    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n   10 root      20   0       0      0      0 S 0.000 0.000   0:02.23 rcuop/0\n   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0\n   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 migration+\n   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.02 watchdog/0\n   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.02 watchdog/1\n   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.00 migration+\n   17 root      20   0       0      0      0 S 0.000 0.000   0:00.06 ksoftirqd+\n   19 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/1+\n   20 root      20   0       0      0      0 S 0.000 0.000   0:00.97 rcuop/1\n"}]

#get system top info
http://localhost:8080/assist/sys/info
{"id":0,"createTime":1478939443070,"topInfo":"top - 16:30:43 up  4:23,  3 users,  load average: 0.07, 0.32, 0.36\nTasks: 246 total,   1 running, 245 sleeping,   0 stopped,   0 zombie\n%Cpu(s):  3.9 us,  0.7 sy,  0.0 ni, 94.5 id,  0.8 wa,  0.0 hi,  0.1 si,  0.0 st\nKiB Mem:   8068296 total,  7436352 used,   631944 free,      896 buffers\nKiB Swap:  2109436 total,        0 used,  2109436 free.  3681996 cached Mem\n\n  PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND\n 1232 root      20   0  330128  68232  50236 S 6.250 0.846   1:48.32 Xorg\n 1788 liangyh   20   0 1707476 159476  65344 S 6.250 1.977   1:21.37 gnome-she+\n 2160 liangyh   20   0 4874292 1.506g  49872 S 6.250 19.57  39:04.02 java\n15366 liangyh   20   0 2099584 112132  20180 S 6.250 1.390   0:03.90 java\n    1 root      20   0   33652   5716   3428 S 0.000 0.071   0:04.09 systemd\n    2 root      20   0       0      0      0 S 0.000 0.000   0:00.00 kthreadd\n    3 root      20   0       0      0      0 S 0.000 0.000   0:00.09 ksoftirqd+\n    5 root       0 -20       0      0      0 S 0.000 0.000   0:00.00 kworker/0+\n    7 root      20   0       0      0      0 S 0.000 0.000   0:51.43 rcu_preem+\n    8 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_sched\n    9 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcu_bh\n   10 root      20   0       0      0      0 S 0.000 0.000   0:08.16 rcuop/0\n   11 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuos/0\n   12 root      20   0       0      0      0 S 0.000 0.000   0:00.00 rcuob/0\n   13 root      rt   0       0      0      0 S 0.000 0.000   0:00.01 migration+\n   14 root      rt   0       0      0      0 S 0.000 0.000   0:00.04 watchdog/0\n   15 root      rt   0       0      0      0 S 0.000 0.000   0:00.04 watchdog/1\n   16 root      rt   0       0      0      0 S 0.000 0.000   0:00.07 migration+\n   17 root      20   0       0      0      0 S 0.000 0.000   0:00.08 ksoftirqd+\n"}


#get surveyed count
http://localhost:8080/assist/visiting/totalCount

#get online count
http://localhost:8080/assist/visiting/onlineCount



http://localhost:8080/assist/visiting/statistic

{"广东省":2,"上海市":1,"北京市":1}


*****
http://localhost:8080/assist/respondent/search?school=u&nationality=han
school
education
nationality
major
page.currentPage (the first page is 1)
page.pageSize

[{"id":1,"name":"liangyihuai","gender":null,"school":"cqupt","education":"benke","major":null,"nationality":"hanzu","residence":null,"surveyFinish":0},
{"id":2,"name":null,"gender":null,"school":"cqupt","education":null,"major":null,"nationality":"hanzu","residence":null,"surveyFinish":0}]
