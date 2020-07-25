1. 功能描述

   * **学生**
     * 登录，注册
     * 查看个人信息
     * 选择试卷答题
     * 查看答题记录

   * **老师**
     * 登录
     * 查看个人信息
     * 新建试卷
     * 查看所有试卷

2. 数据库解释

   ·    **学生表**

   * stu_id integer

   * stu_username varchar(30)
   * stu_name varchar(30)
   * stu_password varchar(30)
   * stu_aver  integer
   * stu_writePaperNum  integer

   ·    **老师表**

   * tea_id integer
   * tea_username varchar(30)
   * tea_name varchar(30)
   * tea_password varchar(30)
   * tea_makePaperNum  integer

   ·    **题目表**

   * item_id integer
   * item_content varchar(500)
   * item_answer varchar(30)

   ·    **试卷表**

   * paper_id integer
   * paper_time  date
   * tea_id  integer

   ·    **试卷****_****题目中间表**（因为是多对多关系）

   * paper_id integer
   * item_id integer

   ·    **考试记录表**

   * paper_id integer
   * stu_id integer
   * paper_grade  integer
   * paper_write_time  date

3. android studio环境统一（Android 10 , X86_64, pixel 3 API ,SDK 29）

4. MVP模式：方便分工开发，升级功能，维护系统

5. 项目具有功能的界面

   **StudentLoginActivity      学生登录界面**

   提交登录信息到后台，判断是否登陆成功，成功就跳转到学生后台，否则就提示用户名或者密码错误（登录成功的同时将id保存到sharePreference中）

   **StudentRegisterActivity     学生注册界面**

   提交注册信息到后台，注册成功就跳转到登录界面，失败就提示用户名重复

   **TeacherLoginActivity      老师登录界面**

   提交登录信息到后台，判断是否登陆成功，成功就跳转到学生后台，否则就提示用户名或者密码错误（登录成功的同时将id保存到sharePreference中）

   **AboutStudentActivity      学生信息界面**

   根据sharePreference中的id查找学生表

   退出登录时轻触sharePreference中的id

   **StudentExamRecordActivity     学生考试记录界面**

   根据sharePreference中的id查找考试记录表

   **StudentShowPaperActivity      学生试卷选择界面**

   查找老师表和试卷表得到试卷信息和老师姓名，显示到前台

   **StudentWritePaperActivity      学生答卷界面**

   根据试卷id查找试卷表，题目表，试卷_题目中间表得到试卷信息和试卷中的题目集合

   点击提交，将填写的答案和其他的信息提交到考试记录表，并更新学生表答卷数

   **AboutTeacherActivity      老师信息界面**

   根据sharePreference中的id查找老师表

   退出登录时轻触sharePreference中的id

   **TeacherMakePaperActivity      老师新建试卷界面**

   查找题目表中的所有题目

   点击提交，将选中的5道题提交到试卷表，试卷_题目中间表。并更新老师表的出卷数

   **TeacherMakeItemActivity       老师新建题目界面**

   点击提交，将老师写的题目内容和答案都提交到题目表中

   **TeacherShowPaperActivity      老师查看所有试卷界面**

   查找老师表和试卷表得到试卷信息和老师姓名，显示到前台

6. 代码文件夹（一个有功能的界面对应一个M，V，C）

   **Adapters**：RecyclerView的适配器类

   **Bean**：实体类

   **Db**：数据库DataBaseHelper类

   **Model**：MVP中的M层

   **Presenter**：MVP中的P层

   **View**：MVP中的V层

   **Layout**：前台布局文件

   **Menu**：学生和老师后台操作页面的右上角菜单