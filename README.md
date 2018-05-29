# movie
基于协同过滤算法的电影推荐系统
## 运行方式
### 1.创建一个application.properties文件，配置相关数据库信息，主要内容如下：
hibernate.dialect=org.hibernate.dialect.MySQLDialect
driverClassName=com.mysql.jdbc.Driver
validationQuery=SELECT 1
jdbc_url=jdbc:mysql://your_ip/movie?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
jdbc_username=username      
jdbc_password=password

hibernate.hbm2ddl.auto=update
hibernate.show_sql=true
hibernate.format_sql=false
hibernate.use_sql_comments=true

sessionInfoName=sessionInfo

image=gif,jpg,jpeg,png,bmp
flash=swf,flv
media=swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb
file=doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2
maxFileSize=102400
### 2.git clone https://github.com/staringlin/movie.git 获取内容
### 3.作为maven项目导入到eclipse

## 功能模块
![](https://github.com/staringlin/movie/raw/master/modules.png)
