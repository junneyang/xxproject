# xxproject
SpringCloud 微服务综合实例。

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

## Compile:
- 普通Jar包: mvn clean package
- Docker镜像: mvn clean package docker:build

## Deploy:
- 普通Jar包方式启动: 
```
参考READM
```
- Docker方式启动: 
```
source bootstrap.rc
docker-compose up -d
```
共17个容器运行: 
