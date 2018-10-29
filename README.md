# Java自动化测试、业务检查
基于Selenium的二次封装  

# 目前支持
1.支持通过xml书写测试/检查流程  
2.支持业务短信告警(通过配置接口)  
3.通过groovy脚本支持短信验证码登录  
4.通过js标签支持直接运行js代码

# 后续功能
1.部分BUG优化  
2.可自定义业务场景测试成功失败条件  
3.……

# 使用方法
1.使用git clone到本地  
2.使用maven构建导出jar包及配置文件  
3.按照需要修改配置文件config.property  
4.按照业务系统需要编写xml流程文件  
5.运行 java -jar automation.jar  

# 注意
如果使用chrome浏览器测试 需要下载并配置chromedriver.exe  
