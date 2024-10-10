# 项目遇到的错误

## 简介
此过程包含不同类型的问题

### 构建过程中
1.【问题】IDEA Maven pom.xml 变灰 出现删除线
  ![](projectImgs/pom-xml文件灰色线.png)
  原因:项目中的pom.xml文件被设置在maven忽略清单中
  解决:Settings-->Build,Execution,Deployment-->Maven-->Ignored Files 
        将对应的选项框选对号

