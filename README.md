# metrics-analysis
一个进行接口访问参数统计通知的DEMO
* 基于spring AOP 进行参数统计
* 支持主动（定时任务） 和主动（接口调用）方式获取统计信息
* 支持redis和文件两种信息持久化方式

start:
* 配置本地redis地址
* 修改配置项reportS调整消息触达方式

拓展设计：
* 对外提供接口MetricsStorage支持多种访问日志的持久化方式接入；
* 提供接口Report支持其他消息触达方式接入

