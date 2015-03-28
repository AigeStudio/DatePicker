*You can jump to EN README after CN if you don't read chinese.*
***
**Here is begin of CN document.**
# 日历选择器
灰常灰常简单的Android日历选择器（注：暂不支持横屏操作，农历节气有一到两天的误差）
##版本迭代
###1.0.0 beta
横屏暂未考虑，竖屏暂未发现问题，项目使用中
##预览图
![](https://github.com/AigeStudio/DatePicker/blob/master/PreviewGif.gif)
##功能简介
* 支持多选返回结果
* 支持主色调定制
* 农历隐藏

更多的功能方法可查看[IPick.java](https://github.com/AigeStudio/DatePicker/blob/master/DatePicker/src/main/java/cn/aigestudio/datepicker/interfaces/IPick.java)
##如何集成到项目
###步骤一
将DatePicker这个Module导入你的Project中
###步骤二
在你Project的settings.gradle文件中增加如下内容：
```gradle
include ':DatePicker'
```
这里要注意的是在一些gradle版本中需要以英文逗号的方式追加Module：
```gradle
include ':YourMoudle',':DatePicker'
```
添加后当出现“sycn now”提示时点击同步即可
###步骤三
在你项目的build.gradle文件的dependencies区域中添加如下内容：
```gradle
compile project(':DatePicker')
```
##如何使用
一旦将DatePicker集成到项目后你便可以像普通控件那样使用它。

如果你需要获取DatePicker日期选择后返回的数据你需要为DatePicker设置一个OnDateSelected回调接口：
```Java
mDatePicker = (DatePicker) findViewById(R.id.main_dp);
mDatePicker.setOnDateSelected(new OnDateSelected() {
    @Override
    public void selected(List<String> date) {
        // date为选择的日期字符串列表
    }
});
```
选择后的日期将会以列表的形式返回，日期字符串的格式为：
>yyyy-MM-dd

比如：2015-3-28

***
**这里开始是英文文档**
#DatePicker
A simple date picker for android（note:it doesn't work with horizontal view yet,and it has one or two days deviation of solar term）
##Version
###1.0.0 beta
Doesn't work with horizontal view yet,but vertical right.
##Preview
![](https://github.com/AigeStudio/DatePicker/blob/master/PreviewGif.gif)
##Function
* multiple-pick for date
* specify primary colour
* hide lunar display

You can see [IPick.java](https://github.com/AigeStudio/DatePicker/blob/master/DatePicker/src/main/java/cn/aigestudio/datepicker/interfaces/IPick.java) for more help.
##How to add to your project
###step 1
import DatePicker lib to your project
###step 2
Add something like below in your settings.gradle file of project:
```gradle
include ':DatePicker'
```
Note that in some other gradle version you many add module like below:
```gradle
include ':YourMoudle',':DatePicker'
```
Click 'sycn now' when it appear after module add.
###step 3
Add something like below in your build.gradle file of project:
```gradle
compile project(':DatePicker')
```
##Usage
Once you add DatePicker to your project you can use it like other views which android define.

You need to set a OnDateSelected callback if you want to obtain dates when dates picked.
```Java
mDatePicker = (DatePicker) findViewById(R.id.main_dp);
mDatePicker.setOnDateSelected(new OnDateSelected() {
    @Override
    public void selected(List<String> date) {
        // Get dates here
    }
});
```
The dates you picker will return in the form of string list,format of string like below:
>yyyy-MM-dd

For example:2015-3-28
***
#LICENSE
 Copyright 2014-2015 AigeStudio(https://github.com/AigeStudio)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
