# GIT

# 1 下载安装Git工具

- 访问git官网:[https://git-scm.com/](https://git-scm.com/)下载符合当前系统的安装包，下载后进行安装。

![image-20210304155931261](https://gitee.com/paida-spitting-star/image/raw/a56773a1009cd11de6393fb7e2421db31cf5fe87/git01.png)

# 2 IDEA中配置Git

①菜单栏顺序选择: File→Settings→Version Control→Git,在弹出框中选择下图中的Git

![image-20210302150158767](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302150158767.png)

②然后点击右侧上方输入框 Path to Git executable:中右面的浏览图标(小文件夹)，并定位git的可执行程序(git.exe文件在git安装目录下的cmd文件夹中)。 

![image-20210302150709841](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302150709841.png)

③点击Test按钮。如果点击后可以显示出版本号，说明配置成功，此时点击窗口下侧的OK按钮即可。

![image-20210302150557927](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302150557927-1614669427881.png)

# 3 IDEA中使用Git推送项目

## 3.1 去gitee网站注册用户

gitee网站地址:[https://gitee.com/](https://gitee.com/)

github网站地址:[https://github.com/](https://github.com/)

## 3.2 创建仓库

- 以下以gitee为例进行介绍，github操作雷同。

①创建仓库

- 点击页面右上方的"+"并选择"创建仓库"

![img](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/gitee1.png)

②设置仓库相关信息

- 首先输入仓库名，通常可以和IDEA中项目名相同。是否开源则可结合需求选择，这里选择的私有(该项目仅自己可见)

![img](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/gitee2.png)

③然后点击下方的"创建"按钮.

![img](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/gitee3.png)

- 此时gitee上完成了仓库的创建工作。

④复制当前仓库路径，这个路径需要在后面IDEA上传项目时使用.

![img](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/gitee4.png)

## 3.3 在IDEA中将项目导入版本控制

①在IDEA菜单栏顺序选择:VCS→Import into Version Controlp→Create Git Repository

- 目的是先在本地创建一个git仓库

![image-20210302162314147](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302162314147.png)

②弹出框中字节点击"OK"按钮即可

![image-20210302162533543](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302162533543.png)

③鼠标右键项目名，在弹出的菜单栏中顺序选择:Git→Add

- 目的是将当前项目中本地仓库没有的内容添加到本地仓库中（实际上并没有真是添加，只有在下一步操作中才会真实添加，但是所有仓库中没有的文件都需要先add后才可以通过下面的步骤添加到仓库!）

![image-20210302162712851](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302162712851.png)

④提交更改

- 鼠标右键当前项目名，在弹出的菜单栏中顺序选择:Git→Commit Diectory

![image-20210302163107773](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302163107773.png)

⑤之后输入本次提交的备注，例如:第一次提交(注:这里必须输入内容，否则无法提交)。然后点击Commit按钮进行本地提交。

- 目的:提交的目的是将当前项目保存到本地仓库中

![image-20210302163501291](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302163501291.png)

⑥之后，点击IDEA左侧边条的1:Project回到项目结构视图

![image-20210302164121176](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164121176.png)

⑦鼠标右键项目名，在弹出的菜单栏中顺序选择:Git→Repository→Push

- 目的就是将本地仓库保存的内容同步到服务器(上传到服务器，并使服务器中的仓库与本地仓库内容保持一致)。

![image-20210302164151224](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164151224.png)

⑧第一次操作时需要配置服务器仓库地址，点击弹出框中的Define remote

![image-20210302164420023](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164420023.png)

⑨在弹出框中的URL输入框中添加gitee上创建的仓库路径(第一大步最后一小步中在页面上复制的仓库路径)，并点击OK按钮

![image-20210302164520885](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164520885.png)

⑩之后IDEA会自动检查链接，没有问题则点击下方的Push按钮

![image-20210302164928861](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164928861.png)

⑪第一次提交时要求输入gitee上的账号和密码，输入后可选中Remember记住密码，后续就不需要输入了。

![image-20210302164943901](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302164943901.png)

⑫此时完成提交，右下方会提示。

![image-20210302165039796](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210302165039796.png)

# 4 IDEA利用Git拉取项目

## 4.1 复制仓库地址

- 访问gitee或github，找到项目对应的仓库，并复制仓库地址。

![image-20210304090848972](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210304090848972.png)

## 4.2 克隆项目

①打开IDEA，依次选择菜单:File→New→Project from Version Control

![image-20210304091054913](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210304091054913.png)

②在弹出框中输入仓库路径(从第一步中gitee或github复制的路径)并点击Clone

![image-20210304091242492](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210304091242492.png)

③在弹出框中输入gitee或github的账号和密码并点击Log in

![image-20210304091334362](https://gitee.com/paida-spitting-star/image/raw/f0681915baf04612e1d6e6981a9d10966f3425e9/image-20210304091334362.png)

④等待下载完毕即可

![image-20210304091438485](https://gitee.com/paida-spitting-star/image/raw/master/git.png)
