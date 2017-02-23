
# MultiSelectPopWindow

## [英文文档](https://github.com/Jay-Goo/MultiSelectPopWindow/edit/master/README.md)


![image](https://github.com/Jay-Goo/MultiSelectPopWindow/blob/master/preview/2017-02-23_10_46_15.gif)



##Usage
###Step1：
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


###Step2：
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

##其它
希望你喜欢我的作品。`Star`是对我的最大支持. 谢谢




