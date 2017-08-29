# xxproject
SpringCloud 微服务综合实例。分布式配置中心，服务发现&负载均衡，链路断路器，API网关，OAuth2认证授权，分布式追踪，ELK日志中心，Docker持续交付等最佳实践。

## Features:    
- [x] 分布式配置中心, 通过消息总线更新配置
- [x] Eureka实现服务端服务注册/服务发现/负载均衡
- [x] Feign实现客户端负载均衡
- [x] Hystrix实现链路断路器
- [x] Zuul实现API网关
- [x] Zipkin&Sleuth实现分布式追踪
- [ ] 消息驱动
- [ ] 分布式锁&信号量
- [ ] 统一异常处理
- [ ] 统一HTTP处理
- [ ] 国际化
- [x] OAuth2.0与安全
- [ ] API文档与测试
- [ ] 批处理
- [ ] 分布式任务调度
- [ ] 分布式ID中心
- [x] 日志收集
- [ ] 同步与异步调用
- [ ] RPC调用
- [ ] 其他最佳实践

## Overview:    
- 系统全景图:    
  ![image](screenshots/microservices-operations-reference-model.png)
- 技术实现:    

  | 服务 | 实现方案 | 项目 |   
  | ------------------- | ------------------- | ------------------- |      
  分布式配置中心 | SpringCloud Config Server | support/config-server     
  服务注册/负载均衡 | Netflix Eureka | support/eureka-server     
  客户端负载均衡 | NetFlix Ribbon | --      
  链路保护与监控 | NetFlix Hystrix | support/turbine-server & support/hystrix-dashboard    
  API网关 | NetFlix Zuul | support/zuul-server    
  分布式追踪 | SpringCloud Sleuth | support/zipkin-server    
  OAuth2认证授权 | SpringCloud Security OAuth | support/auth-server    
  CORE SERVICE | -- | core/product-service & core/review-service    
  COMPOSITE SERVICE | -- | composite/product-composite-service   
  API SERVICE | -- | api/product-api-service          
  日志处理 | ELK | --                 
  容器化部署 | Docker Compose | --       
         
             
	
	  
## Compile:
- 普通Jar包: mvn clean package
- Docker镜像: mvn clean package docker:build,  镜像列表:    
  ![image](screenshots/docker_images.png)
  
## Deploy:
- 普通Jar方式启动: 参考READM
- Docker一键部署: source bootstrap.rc && docker-compose up -d, 共17个容器运行:    
  ![image](screenshots/deploy_docker.png) 
- 部署完成, 访问Eureka, 注册的服务实例如下:  
  ![image](screenshots/eureka.png) 
  
## Enjoy:
- 访问OAuth Server: http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://example.com&scope=webshop&state=97536, 
  输入用户名密码(admin/passw0rd)
- 同意OAuth授权, 如下:    
  ![image](screenshots/approval.png) 
- 页面跳转到: http://example.com/?code=5J4vJ8&state=97536, 获取CODE, 如下:
  ![image](screenshots/auth.png) 
- CODE=5J4vJ8
- 根据CODE获取TOKEN: 
  ```
  curl acme:acmesecret@localhost:9999/uaa/oauth/token \
	-d grant_type=authorization_code \
	-d client_id=acme \
	-d redirect_uri=http://example.com \
	-d code=$CODE -s | jq .
  ```
- TOKEN=bd34faf8-dcd0-4aed-a903-c8a90cb7a731, 以上步骤如下:
  ![image](screenshots/token.png) 
- 访问API服务: http://localhost:5555/api/product-api-service/product-api-composite, 返回消息头带X-RequestId, 如下: 
  ![image](screenshots/api.png) 
- 访问追踪系统: http://localhost:7777/zipkin/, 如下: 
  ![image](screenshots/trace.png) 
  ![image](screenshots/dependency.png) 
- 访问链路保护系统: http://localhost:8088/hystrix/monitor?stream=http%3A%2F%2Fturbine-server%3A8989%2Fturbine.stream, 如下: 
  ![image](screenshots/hystrix.png) 
- 访问ELK日志系统: http://localhost:5601, 如下: 
  ![image](screenshots/elk.png) 
- 使用jvisualvm监控JVM实时性能指标, 如下: 
  ![image](screenshots/jvisualvm.png) 
  ![image](screenshots/threads.png) 
  
