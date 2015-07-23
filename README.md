[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DatePicker-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1864)

*You can jump to EN README after CN if you don't read chinese.*

***

**Here is begin of CN document.**

# 日历选择器
灰常灰常简单的Android日历选择器~~（注：暂不支持横屏操作，农历节气有一到两天的误差）~~

##Android API 版本要求
~~API 1~~

~~API 11~~

**API 1**

##版本迭代
###1.0.0 beta
* ~~横屏暂未考虑，竖屏暂未发现问题，项目使用中~~

###1.1.2 release
* 增加日期选择时弹性动画
* 修复设置主色调后农历颜色不改变BUG

###1.1.3 release
* 不管是横屏还是竖屏，你必须总为该控件指定一个确切宽度比如320dp或者填充父布局，大多数情况下该日期选择器都是在dialog中使用，默认dialog宽度是填充屏幕宽度的，你可以参照Demo来更改diaolog的宽度。

###2.0.0 stable LTS
* API版本支持到1，对于大于等于API11的版本支持动画显示
* 增加呼声很高的单选模式支持 目前支持两种模式 单选和多选
* 增加周次标题的显示
* 支持上下滑动切换年份 左右滑动切换月份
* 节日文本显示从单行调整为两行
* 调整UI色调符合material design
* 对于天朝月历，增加假期、补休的标识
* 增加呼声很高的对今天日期的标识
* 支持默认年月设定
* 在现有月视图的基础上分割出五个区域提供自定义装饰物的绘制
* 在现有月视图的基础上分割出一个背景层提供背景装饰物的绘制
* 内置中文和英语两种语言，根据当前系统语言环境自动切换
* 支持自定义国家语言扩展
* 内置中国和美国两国节假日显示
* 支持多国假期节日显示扩展
* 增强主题色调扩展定制更易于操作
* 优化天朝农历和节气算法 农历显示范围从1900-2100
* 优化代码逻辑提升执行效率
* 该版本后不再作重大更新

##预览图
![](https://github.com/AigeStudio/DatePicker/blob/master/PreviewGif.gif)

因为Gif压缩问题导致预览图看起来有点卡，运行没问题

##如何集成到项目
###方式一 直接从maven center compile
```gradle
compile 'cn.aigestudio.datepicker:DatePicker:2.0.0'
```

###方式二 手动导入
####步骤一
将DatePicker这个Module导入你的Project中

####步骤二
在你Project的settings.gradle文件中增加如下内容：

```gradle
include ':DatePicker'
```

这里要注意的是在一些gradle版本中需要以英文逗号的方式追加Module：

```gradle
include ':YourMoudle',':DatePicker'
```

添加后当出现“sycn now”提示时点击同步即可

####步骤三
在你项目的build.gradle文件的dependencies区域中添加如下内容：

```gradle
compile project(':DatePicker')
```

##如何使用
###简单使用
一旦将DatePicker集成到项目后你便可以像普通控件那样使用它。

如果你需要获取DatePicker日期选择后返回的数据你需要为DatePicker设置一个OnDateSelectedListener监听器：

```Java
......
DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
picker.setDate(2015, 7);
picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
    @Override
    public void onDateSelected(List<String> date) {
        String result = "";
        Iterator iterator = date.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
            if (iterator.hasNext()) {
                result += "\n";
            }
        }
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
});
......
```

setDate方法允许你设置当前月历显示的年月。***注意该方法必须调用，也就是说你必须为DatePicker指定一个确切年月***

默认情况下DatePicker 2.0的选择模式为多选模式，你可以通过setMode方法来设置DatePicker的选择模式，该方法接受一个DPMode类型的枚举值，DatePicker 2.0支持两种选择模式：单选DPMode.SINGLE和多选DPMode.MULTIPLE，设置方式如下：

```Java
......
DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
picker.setDate(2015, 7);
picker.setMode(DPMode.SINGLE);
......
```

在单选模式下，如果你想要获取DatePicker日期选择后返回的数据，你就不能再将DatePicker的监听设置为OnDateSelectedListener而应该设置为OnDatePickedListener：

```Java
......
DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
picker.setDate(2015, 7);
picker.setMode(DPMode.SINGLE);
picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
    @Override
    public void onDatePicked(String date) {
        Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
    }
});
......
```

***这里需要注意的是，你不能将DatePicker设置为多选或单选模式的情况下又设置为单选或多选，在一个实例中只能接受一种选择模式***

选择后的日期将会以列表（多选模式下）或字符串（单选模式下）的形式返回，日期字符串的格式为：

>yyyy-M-d

比如：2015-3-28

###高级定制
DatePicker 2.0默认了一套显示机制，对于天朝月历而言，2015年的假期与补休都会被不同的背景圆颜色所标识，对于其他国家月历而言只有假期会被标识，当然，在某些情况下你还想在某些特定的日期有自己的显示标识，DatePicker 2.0在原有绘制层的基础上分割出了一个背景层，提供给用户绘制自己想要的标识物。比如你想在2015-7-1，2015-7-8，2015-7-16这三个日期上绘制一个不同的背景圆，首先你要通过DPCManager的setDecorBG方法设置一个日期列表，该列表包含了需要绘制背景标识的日期（在没有特殊说明的情况下，DatePicker 2.0中所使用到的日期格式均与上述一致）：

```Java
......
List<String> tmp = new ArrayList<>();
tmp.add("2015-7-1");
tmp.add("2015-7-8");
tmp.add("2015-7-16");
DPCManager.getInstance().setDecorBG(tmp);
......
```

然后你就可以调用DatePicker的setDPDecor方法为DatePicker绘制装饰物背景：

```Java
......
List<String> tmp = new ArrayList<>();
tmp.add("2015-7-1");
tmp.add("2015-7-8");
tmp.add("2015-7-16");
DPCManager.getInstance().setDecorBG(tmp);

DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
picker.setDate(2015, 7);
picker.setDPDecor(new DPDecor() {
    @Override
    public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
        paint.setColor(Color.RED);
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2F, paint);
    }
});
......
```

这里我们在2015-7-1，2015-7-8，2015-7-16这三个日期上绘制一个红色的背景圆：

![](https://github.com/AigeStudio/DatePicker/blob/master/BG.jpg)

DatePicker 2.0中所提供的背景层位于日期文本的下方所有其他背景的上方，也就是说在所有的背景层中，你所自定义的背景拥有最高展示的优先级

当然，有些情况下光是绘制一个背景层往往不是不够的，DatePicker 2.0充分考虑到这一点，开放了位于前景层的五个装饰区域给用户绘制不同的装饰物，这五个装饰区域的位置大致如下：

![](https://github.com/AigeStudio/DatePicker/blob/master/Decor.jpg)

如上图所示，DatePicker 2.0开放了位于前景层的A、B、C、D、E五个区域让用于有机会在这些前景层的这些区域绘制一些装饰物，使用方法也非常简单，与绘制背景装饰物的逻辑类似，这里以分别在2015-7-5，2015-7-10这两个日期的左上和又上角绘制不同颜色形状的图形为例，首先你需要做的第一步与绘制背景装饰物一样，先调用DPCManager的相关方法初始化日期数据：

```Java
......
List<String> tmpTL = new ArrayList<>();
tmpTL.add("2015-7-5");
DPCManager.getInstance().setDecorTL(tmpTL);

List<String> tmpTR = new ArrayList<>();
tmpTR.add("2015-7-10");
DPCManager.getInstance().setDecorTR(tmpTR);
......
```

这里补充一点，从上面装饰区域的示例图中其实可以看到A、B、C、D、E五个区域分别表示的是左上角（Top left）、顶部（Top）、右上角（Top right）、左边（Left）、右边（Right），与之对应的设置日期数据与绘制的方法也采用不同的命名来区分，比如上面的代码中我们为左上角TL和右上角TR设置标识日期数据就分别用到了setDecorTL方法和setDecorTR方法，当然，在绘制的时候我们也需要在相应的方法中绘制：

```Java
......
List<String> tmpTL = new ArrayList<>();
tmpTL.add("2015-7-5");
DPCManager.getInstance().setDecorTL(tmpTL);

List<String> tmpTR = new ArrayList<>();
tmpTR.add("2015-7-10");
DPCManager.getInstance().setDecorTR(tmpTR);

DatePicker picker = (DatePicker) findViewById(R.id.main_dp);
picker.setDate(2015, 7);
picker.setDPDecor(new DPDecor() {
    @Override
    public void drawDecorTL(Canvas canvas, Rect rect, Paint paint) {
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void drawDecorTR(Canvas canvas, Rect rect, Paint paint) {
        paint.setColor(Color.BLUE);
        canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 2, paint);
    }
});
picker.setOnDateSelectedListener(new DatePicker.OnDateSelectedListener() {
    @Override
    public void onDateSelected(List<String> date) {
        String result = "";
        Iterator iterator = date.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
            if (iterator.hasNext()) {
                result += "\n";
            }
        }
        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }
});
......
```

绘制效果如下：

![](https://github.com/AigeStudio/DatePicker/blob/master/DecorExample.jpg)

***注意！！！这里非常重要的一点是你必须在DatePicker显示前设置DPCManager中的相关数据！！！***

###功能扩展
DatePicker 2.0提供了三个Manager来管理控件的各项功能，其中DPCManager用于对日期数据的加载，这在上面的介绍中有所提及这里不再多说；DPLManager用于对语言环境的控制，DatePicker 2.0内置中文和英语两种语言并会根据当前系统的语言环境进行自动切换，如果你想扩展更多的语言，只需继承DPLManager并重写相关方法即可，同时你还必须在DPLManager的单例方法中增加相关逻辑以便DatePicker能识别你的语言：

```Java
    /**
     * 获取日历语言管理器
     * 
     * Get DatePicker language manager
     *
     * @return 日历语言管理器 DatePicker language manager
     */
    public static DPLManager getInstance() {
        if (null == sLanguage) {
            String locale = Locale.getDefault().getLanguage().toLowerCase();
            if (locale.equals("zh")) {
                sLanguage = new CN();
            } else {
                sLanguage = new EN();
            }
        }
        return sLanguage;
    }
```

最后我们还提供了一个DPTManager用于对DatePicker整体色调的控制，如果你想实现自己的主题色调，只需继承DPTheme并实现相关方法，然后再调用DPTManager的initCalendar方法传入你自定义的DPTheme对象即可。***这里与DPCManager不同的是主题的初始化（也就是说DPTManager的initCalendar方法的调用）必须在DatePicker构造前完成！！！这点与DPCManager在DatePicker显示前调用不同！！！***

***

**这里开始是英文文档**

#DatePicker
A simple date picker for android~~(note:it doesn't work with horizontal view yet,and it has one or two days deviation of solar term)~~

##Android API Needs
~~API 1~~

**API 11**

##Version
###1.0.0 beta
* ~~Doesn't work with horizontal view yet,but vertical right.~~

###1.1.2 release
* Add animation when date picked.
* Bugfix:color does not change when main color set.

###1.1.3 release
* You must specify a exactly width like 320dp or match_parent whether portrait or landscape, In most cases the datepicker is use with dialog, and it will match screen in default mode, you can refer to demo to change dialog width.

##Preview
![](https://github.com/AigeStudio/DatePicker/blob/master/PreviewGif.gif)

##Function
* multiple-pick for date
* specify primary colour
* hide lunar display

You can see [IPick.java](https://github.com/AigeStudio/DatePicker/blob/master/DatePicker/src/main/java/cn/aigestudio/datepicker/interfaces/IPick.java) for more help.

##How to add to your project
###Method A:compile from maven center
```gradle
compile 'cn.aigestudio.datepicker:DatePicker:1.1.3'
```

###Method B:Help yourself
####step 1
import DatePicker lib to your project

####step 2
Add something like below in your settings.gradle file of project:

```gradle
include ':DatePicker'
```

Note that in some other gradle version you many add module like below:

```gradle
include ':YourMoudle',':DatePicker'
```

Click 'sycn now' when it appear after module add.

####step 3
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

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.

You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.

See the License for the specific language governing permissions and limitations under the License.
