
# MultiSelectPopWindow

## [中文文档](https://github.com/Jay-Goo/MultiSelectPopWindow/blob/master/README_ZH.md)


![image](https://github.com/Jay-Goo/MultiSelectPopWindow/blob/master/preview/2017-02-23_10_46_15.gif)



## Usage
### Step1：
```xml
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        compile 'com.github.Jay-Goo:MultiSelectPopWindow:v1.0.1'
	}
   
```


### Step2：
```xml
   new MultiSelectPopWindow.Builder(this)
                           .setNameArray(names)
                           .setConfirmListener(new MultiSelectPopWindow.OnConfirmClickListener() {
                               @Override
                               public void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList) {
                               //do something
                               }
                           })
                           .setCancel("取消")
                           .setConfirm("完成")
                           .setTitle("班级列表")
                           .build()
                           .show(findViewById(R.id.mBottom));
```

# Feature
more attributes you could set !

## 联系我

- Email： 1015121748@qq.com
- QQ Group: 573830030 有时候工作很忙没空看邮件和Issue,大家可以通过QQ群联系我
<div style="text-align: center;">
<img src="https://github.com/Jay-Goo/RangeSeekBar/blob/master/Gif/qq.png" style="margin: 0 auto;" height="250px"/>
</div>

## 一杯咖啡

大家都知道开源是件很辛苦的事情，这个项目也是我工作之余完成的，平时工作很忙，但大家提的需求基本上我都尽量满足，如果这个项目帮助你节省了大量时间，你很喜欢，你可以给我一杯咖啡的鼓励，不在于钱多钱少，关键是你的这份鼓励所带给我的力量~
<div style="text-align: center;">
<img src="https://github.com/Jay-Goo/RangeSeekBar/blob/master/Gif/pay.png" height="200px"/>
</div>

## Others 

I hope you like this MultiSelectPopWindow. `Star` is the greatest support for me！ Thank U




